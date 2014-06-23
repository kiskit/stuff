package models.tmdb;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


import com.fasterxml.jackson.databind.ObjectMapper;

public class TmdbApi {
	private String key;
	private static final String baseURL = "";
	private static ObjectMapper mapper = new ObjectMapper();
	
	public String getContent() throws TmdbException {
		
		String content = null;
		
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			content = new String(responseBody);

		} catch (HttpException e) {
			throw new TmdbException("Fatal protocol violation: " + e.getMessage());
		} catch (IOException e) {
			throw new TmdbException("Fatal transport error: " + e.getMessage());
		} finally {
			// Release the connection.
			method.releaseConnection();
		}  
		return content;
	}
	
	


}
