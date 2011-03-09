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

import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusMonitor;
import org.hyperdata.scute.swing.status.StatusTask;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SparqlHttp extends StatusTask {

	private boolean running = false;
	private String responseString = null;

	private HttpClient httpclient;
	private HttpGet httpget;
	private SparqlContainer sparqlContainer;

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
	}

	public String getResponseString() {
		return responseString;
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

		// long len = entity.getContentLength();
		String responseString = "";
		try {
			responseString = EntityUtils.toString(entity);
		} catch (Exception exception) {
			stateChanged(new StatusEvent(StatusMonitor.RED, exception.getMessage()));
			Log.exception(exception);
		}
		running = false;
		stateChanged(new StatusEvent(StatusMonitor.GREEN));
		sparqlContainer.setResultsText(responseString);
		sparqlContainer.fireSparqlEvent();
	}
	
	public boolean isRunning(){
		return running;
	}
}
