/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
	private HttpGet httpget;
	private SparqlContainer sparqlContainer;
private String httpText = "";

	public void init(SparqlContainer sparqlContainer) {

		this.sparqlContainer = sparqlContainer;
		String endpointURI = sparqlContainer.getEndpoint().getUri();
		String query = sparqlContainer.getQueryString();

		// System.out.println("query=" + query);

		String uri = "";

		try {
			// might be better to use HttpClient's formatting?
			uri = endpointURI + "?query=" + URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			// TODO popup exception
			Log.exception(exception);
		}
		httpclient = new DefaultHttpClient();
		httpget = new HttpGet(uri);
		
		httpText = "Request:\n\n"+httpget.getRequestLine();
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
		
		stateChanged(new StatusEvent(StatusMonitor.AMBER, "Running..."));
		running = true;
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (Exception exception) {
			stateChanged(new StatusEvent(StatusMonitor.RED, exception.getMessage()));
			Log.exception(exception);
		}

		HttpEntity entity = response.getEntity();
		
		httpText += "\n\nResponse:\n\n"+response.toString();
		sparqlContainer.setHTTPText(httpText);
		
		// long len = entity.getContentLength();
		// String results = "";
		
		try {
			resultString = EntityUtils.toString(entity);
		} catch (Exception exception) {
			stateChanged(new StatusEvent(StatusMonitor.RED, exception.getMessage()));
			Log.exception(exception);
		}
		running = false;
		stateChanged(new StatusEvent(StatusMonitor.GREEN));
		sparqlContainer.setResultsText(resultString);
		sparqlContainer.fireSparqlEvent();
	}
	
	public boolean isRunning(){
		return running;
	}
}
