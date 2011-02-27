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


/**
 * @author danny
 * 
 */
public class SparqlHttp implements Runnable {

	private boolean success = false;
	private String responseString = null;
	
	private HttpClient httpclient;
	private HttpGet httpget;
	private SparqlContainer sparqlContainer;

	public void init(SparqlContainer sparqlContainer) {
		
		this.sparqlContainer = sparqlContainer;
		String endpointURI = sparqlContainer.getEndpoint().getUri();
		String query = sparqlContainer.getQueryString();
		
		System.out.println("query="+query);
		
		String uri = "";
		
		try {
			// might be better to use HttpClient's formatting?
			uri = endpointURI + "?query="
					+ URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			// TODO popup exception
			exception.printStackTrace();
		}
		httpclient = new DefaultHttpClient();
		httpget = new HttpGet(uri);
	}
	
	public String getResponseString(){
	return responseString ;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		success = false;
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException exception) {
			// TODO log error
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO log error
			exception.printStackTrace();
		}

		HttpEntity entity = response.getEntity();

		// long len = entity.getContentLength();
		String responseString = "";
		try {
			responseString = EntityUtils.toString(entity);
		} catch (ParseException exception) {
			// TODO log error
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO log error
			exception.printStackTrace();
		}
		success = true;
		sparqlContainer.setResultsText(responseString);
		sparqlContainer.fireSparqlEvent();
	}
}
