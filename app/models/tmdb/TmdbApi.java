package models.tmdb;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
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
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;


import com.fasterxml.jackson.databind.ObjectMapper;

public class TmdbApi {
	private String key;
	private static final String baseURL = "";
	private static ObjectMapper mapper = new ObjectMapper();

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




}
