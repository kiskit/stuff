package models.tmdb;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;




import com.fasterxml.jackson.databind.ObjectMapper;

public class TmdbApi {
	private static String key = "c589965ca14962d100212f66a6a2b1c5";

	private static final String baseURL = "";
	private static ObjectMapper mapper = new ObjectMapper();


	public static String getContent(String url) throws TmdbException {
		URL obj;
		String responseStr = null;
		try {
			obj = new URL(url);

			System.out.println("URL:" + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			responseStr = response.toString();
			System.out.println(responseStr);
		} catch (Exception e) {
			throw (new TmdbException(e));
		}
		//System.out.println("Returning : " + responseCode);

		return responseStr;
	}

	public static BasicMovieInfoSearch searchByTitle(String title) {
		String response = null;
		BasicMovieInfoSearch search = null;
		try {
			String url = "http://api.themoviedb.org/3/search/movie?api_key="+key+"&query=" + URLEncoder.encode(title, "UTF-8");
			response = getContent(url);
			search = mapper.readValue(response, BasicMovieInfoSearch.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (search);
	}
	
/*	public static List<BasicMovieInfo> searchByTitle(String key, String title) {
		String url = "https://api.themoviedb.org/3/search/movie?api_key="+key+"&query=" + title;
		List<BasicMovieInfo> movies = null;
		System.out.println("Mapping objects");                  
		BasicMovieInfoSearch search = null;

		try {
			String response = getContent(url);
			search = mapper.readValue(response.toString(), BasicMovieInfoSearch.class);
			movies = search.getResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (movies);
	}
*/


}
/*
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public String getContent() throws TmdbException {

	String content = null;

	HttpProcessor httpproc = HttpProcessorBuilder.create()
			.add(new RequestContent())
			.add(new RequestTargetHost())
			.add(new RequestConnControl())
			.add(new RequestUserAgent())
			.add(new RequestExpectContinue()).build();

	HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

	HttpCoreContext coreContext = HttpCoreContext.create();
	HttpHost host = new HttpHost("localhost", 8080);
	coreContext.setTargetHost(host);

	DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);

	try {

		String[] targets = {
				"/",
				"/servlets-examples/servlet/RequestInfoExample",
		"/somewhere%20in%20pampa"};

		for (int i = 0; i < targets.length; i++) {
			if (!conn.isOpen()) {
				Socket socket = null;
				try {
					socket = new Socket(host.getHostName(), host.getPort());
					conn.bind(socket);
				} catch (UnknownHostException e) {
					throw (new TmdbException("Unknown host" ,e));
				} catch (IOException e) {
					throw (new TmdbException("IO Exception" ,e));
				}
			}
			BasicHttpRequest request = new BasicHttpRequest("GET", targets[i]);
			System.out.println(">> Request URI: " + request.getRequestLine().getUri());

			try {
				httpexecutor.preProcess(request, httpproc, coreContext);
			} catch (HttpException e) {
				throw (new TmdbException("Http Exception" ,e));
			} catch (IOException e) {
				throw (new TmdbException("IO Exception" ,e));
			}
			HttpResponse response;
			try {
				response = httpexecutor.execute(request, conn, coreContext);
				httpexecutor.postProcess(response, httpproc, coreContext);
				//System.out.println("<< Response: " + response.getStatusLine());
				content = EntityUtils.toString(response.getEntity());

			} catch (IOException e) {
				throw (new TmdbException("IO Exception" ,e));
			} catch (HttpException e) {
				throw (new TmdbException("Http Exception" ,e));
			}
		}
	} finally {
		try {
			conn.close();
		} catch (IOException e) {
			throw (new TmdbException("IO Exception" ,e));
		}
	}		
	return content;
}
 */
