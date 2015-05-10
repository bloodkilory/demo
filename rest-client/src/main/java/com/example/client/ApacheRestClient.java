package com.example.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

/**
 * Apache Rest Client
 *
 * @param <R> Request Type
 * @param <E> Response Type
 * @author yangkun
 */
public class ApacheRestClient<R, E> {

	private static Logger logger = Logger.getLogger(ApacheRestClient.class);

	private static final String SCHEME = "http";
	public static final int GET = 1;
	public static final int POST = 2;
	public static final int PUT = 3;
	public static final int DELETE = 4;
	public static final String JSON = MediaType.APPLICATION_JSON_VALUE;
	public static final String XML = MediaType.APPLICATION_XML_VALUE;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public ApacheRestClient() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	/**
	 * @param method
	 * @param host
	 * @param port
	 * @param uri
	 * @return json string
	 */
	public E rest(int method, String host, String port, String uri, String accept) {
		return rest(method, host, port, uri, accept, null, null, null, null, null, null);
	}

	public E rest(int method, String host, String port, String uri, String accept, Class<E> returnType) {
		return rest(method, host, port, uri, accept, null, null, returnType, null, null, null);
	}

	public E rest(int method, String host, String port, String uri, String accept, Class<E> returnType, R request) {
		return rest(method, host, port, uri, accept, null, null, returnType, request, null, null);
	}

	public E restWithSecurity(int method, String host, String port, String uri
			, String accept, String username, String password) {
		return rest(method, host, port, uri, accept, null, null, null, null, username, password);
	}

	/**
	 * @param method      REST REQUEST METHOD
	 * @param host        host ip
	 * @param port        port
	 * @param uri         uri
	 * @param params
	 * @param requestType
	 * @param returnType
	 * @return jackson deserialization object
	 */
	@SuppressWarnings("unchecked")
	public E rest(int method, String host, String port, String uri, String accept, Map<String, String> params,
	              Class<R> requestType, Class<E> returnType, R request, String user, String pass) {

		E result = null;
		logger.debug("rest client reqest start, host ip:" + host + ", port:" + port + ", uri:" + uri);
		CloseableHttpClient httpClient;
		if(user != null) {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, pass);
			provider.setCredentials(AuthScope.ANY, credentials);
			httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		} else {
			httpClient = HttpClientBuilder.create().build();
		}
		try {
			// parse the port number
			int p = Integer.parseInt(port);
			// specify the host, protocol, and port
			HttpHost target = new HttpHost(host, p, SCHEME);
			// specify the method request
			HttpResponse httpResponse = null;
			switch(method) {
				case GET:
					HttpGet getRequest = new HttpGet(this.handleUri(uri) + this.paramsToString(params));
					getRequest.setHeader(new BasicHeader("Accept", accept));
					httpResponse = httpClient.execute(target, getRequest);
					break;
				case POST:
					HttpPost postRequest = new HttpPost(this.handleUri(uri) + this.paramsToString(params));
					postRequest.setHeader(new BasicHeader("Accept", accept));
					if(request != null) { // serialize with jackson
						String postParam = MAPPER.writeValueAsString(request);
						StringEntity postInput = new StringEntity(postParam);
						postInput.setContentType("application/json");
						postInput.setContentEncoding("utf-8");
						postRequest.setEntity(postInput);
					}
					httpResponse = httpClient.execute(target, postRequest);
					break;
				case PUT:
					HttpPut putRequest = new HttpPut(this.handleUri(uri) + this.paramsToString(params));
					putRequest.setHeader(new BasicHeader("Accept", accept));
					if(request != null) { // serialize with jackson
						String putParam = MAPPER.writeValueAsString(request);
						StringEntity putInput = new StringEntity(putParam);
						putInput.setContentType("application/json");
						putInput.setContentEncoding("utf-8");
						putRequest.setEntity(putInput);
					}
					httpResponse = httpClient.execute(target, putRequest);
					break;
				case DELETE:
					break;
				default:
					HttpGet defaultRequest = new HttpGet(this.handleUri(uri) + this.paramsToString(params));
					httpResponse = httpClient.execute(target, defaultRequest);
					break;
			}

			// specify the response
			HttpEntity entity = null;
			if(httpResponse != null) {
				int resposeCode = httpResponse.getStatusLine().getStatusCode();
				if(!String.valueOf(resposeCode).startsWith("20")) {
					throw new RuntimeException("rest client request Failed, Http error code:" +
							httpResponse.getStatusLine());
				}
				entity = httpResponse.getEntity();
				logger.debug("rest client response: " + httpResponse.getStatusLine());
			}

			// deserialize with jackson
			if(entity != null) {
				String content = EntityUtils.toString(entity);
				if(returnType == null) {
					logger.warn("rest client returnType is null, will return as json string! ");
					return (E) content;
				}
//				logger.debug("rest client reveiced data:" + json);
				result = MAPPER.readValue(content, returnType);
			}

		} catch(NumberFormatException e) {
			logger.error("rest client port is NaN: " + port, e);
		} catch(JsonParseException | JsonMappingException e) {
			logger.error("rest client jackson-json parse error, " + e.getMessage(), e);
		} catch(Exception e) {
			logger.error("rest client error, " + e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch(IOException e) {
				//
			}
		}
		return result;
	}

	private String paramsToString(Map<String, String> params) {
		StringBuilder builder = new StringBuilder("");
		if(params != null && params.size() > 0) {
			builder.append("?");
			for(String key : params.keySet()) {
				builder.append(key);
				builder.append("=");
				builder.append(params.get(key));
				builder.append("&");
			}
			builder.delete(builder.length() - 1, builder.length());
		}
		return builder.toString();
	}

	private String handleUri(String uri) {
		if(uri == null) {
			return "";
		}
		if(!uri.startsWith("/")) {
			return "/" + uri;
		}
		if(uri.endsWith("/")) {
			StringBuilder builder = new StringBuilder(uri);
			builder.delete(builder.length() - 1, builder.length());
			return builder.toString();
		}
		return uri;
	}

}