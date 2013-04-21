package com.polly.dalekcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
	
	/**
	 * Ping a GET request to a URL.
	 * @param url
	 * @return the HTTP response code
	 * @throws IOException
	 */
	public int pingUrl(String urlString) throws IOException {
	    InputStream is = null;
	        
	    try {
	        URL url = new URL(urlString);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        conn.connect();
	        return conn.getResponseCode();
	        
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
	}
}
