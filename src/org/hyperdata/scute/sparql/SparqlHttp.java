/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.status.StatusTask;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SparqlHttp extends StatusTask {

	private boolean running = false;
	private String resultString = null;

	private HttpClient httpclient;
	private HttpGet httpGet;
	private SparqlContainer sparqlContainer;
	private String httpText = "";

	public void init(SparqlContainer sparqlContainer) {

		this.sparqlContainer = sparqlContainer;
		String endpointURI = sparqlContainer.getEndpoint().getUri();
		String query = sparqlContainer.getQueryString();
		String uri = "";

		try {
			// might be better to use HttpClient's formatting?
			uri = endpointURI + "?query=" + URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			Log.exception(exception);
		}
		httpclient = new DefaultHttpClient();
		httpGet = new HttpGet(uri);

		httpText = "Request:\n\n" + httpGet.getRequestLine();
		sparqlContainer.setHTTPText(httpText);
	}

	public String getResponseString() {
		return resultString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		running = true;
		StatusEvent event = new StatusEvent(StatusMonitor.AMBER, "Running...");
		System.out.println("SparqlHttp Running...");
		event.setProgress(0);
		stateChanged(event);

		HttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (InterruptedIOException iioException){
			// user interrupted
		} catch (Exception exception) {
			stateChanged(new StatusEvent(StatusMonitor.RED,
					exception.getMessage()));
			Log.exception(exception);
		}
		if(!running){
			return;
		}
		event = new StatusEvent(StatusMonitor.AMBER, "Running...");
		event.setProgress(25);
		stateChanged(event);

		HttpEntity entity = response.getEntity();

		httpText += "\n\nResponse:\n\n" + response.toString();
		sparqlContainer.setHTTPText(httpText);

		long length = entity.getContentLength(); // doesn't appear to work - examine later
		// System.out.println("LENGTH=" + length);

		try {
			resultString = readEntity(entity); // has status monitoring inside
			// EntityUtils.toString(entity);
		} catch (Exception exception) {
			stateChanged(new StatusEvent(StatusMonitor.RED,
					exception.getMessage()));
			Log.exception(exception);
		}
		running = false;
		sparqlContainer.setHTTPText(httpText);
		event = new StatusEvent(StatusMonitor.GREEN);
		event.setProgress(100);
		stateChanged(event);
		if(resultString.equals("Interrupted")){ // was stopped (better handling via exception...)
			return;
		}
		sparqlContainer.setResultsText(resultString);
		sparqlContainer.fireSparqlEvent();
	}

	/**
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private String readEntity(HttpEntity entity) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();

		// StatusEvent event = new StatusEvent(StatusMonitor.AMBER,
		// "Running...");
		// event.setProgress(0);
		// stateChanged(event);
		long counter = 0;
		if (entity != null) {
			InputStream instream = entity.getContent();
			long length = entity.getContentLength();
			long chunk = length / 10;
			if (length == -1) {
				StatusEvent event = new StatusEvent(StatusMonitor.AMBER);
				event.setProgress(StatusMonitor.INDETERMINATE_PROGRESS);
				stateChanged(event);
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(instream));
				int c;
				while (running && (c = reader.read()) != -1) {
					stringBuffer.append((char) c);
					counter++;
					if (length != -1 && counter % chunk == 0) {
						StatusEvent event = new StatusEvent(StatusMonitor.AMBER);
						event.setProgress((int) (100 * counter / length));
						stateChanged(event);
					}
				}
			} catch (Exception exception) {
				System.out.println("interrupted "+exception);
				stop();
				return "Interrupted";

			} finally {
				// Closing the input stream will trigger connection release
				instream.close();
			}
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return stringBuffer.toString();
	}

	public boolean isRunning() {
		return running;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.status.Stoppable#stop()
	 */
	@Override
	public void stop() {
		System.out.println("stopping");
		running = false;
		httpGet.abort();
		// httpGet.
		httpclient.getConnectionManager().shutdown();
		
		System.out.println("nulling");
		httpclient = null;
	}
}
