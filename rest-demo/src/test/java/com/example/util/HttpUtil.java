package com.example.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

/**
 * @author yangkun generate on 2017/3/21
 */
public abstract class HttpUtil {

    private static final int connTimeout = 15 * 1000;
    private static final int socketTimeout = 30 * 1000;

    public static String doPost(String url, String jsonStr) {
        try {
            Executor executor = Executor.newInstance(noSslHttpClient());
            return executor.execute(Request.Post(url).bodyString(jsonStr, ContentType.APPLICATION_JSON)
                    .connectTimeout(connTimeout).socketTimeout(socketTimeout))
                    .returnContent().asString(StandardCharsets.UTF_8);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String doPost(String url) {
        try {
            return Request.Post(url).connectTimeout(connTimeout)
                    .socketTimeout(socketTimeout).execute()
                    .returnContent().asString(StandardCharsets.UTF_8);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String doGet(String url) {
        try {
            return Request.Get(url)
                    .connectTimeout(connTimeout).socketTimeout(socketTimeout).execute()
                    .returnContent().asString(StandardCharsets.UTF_8);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static InputStream doGetReturnStream(String url) {
        try {
            return Request.Get(url)
                    .connectTimeout(connTimeout).socketTimeout(socketTimeout).execute()
                    .returnContent().asStream();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] doGetByte(String url) {
        try {
            return Request.Get(url)
                    .connectTimeout(connTimeout).socketTimeout(socketTimeout).execute()
                    .returnContent().asBytes();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] doGetByteWithTimeout(String url, int timeout) {
        try {
            return Request.Get(url)
                    .connectTimeout(timeout).socketTimeout(timeout).execute()
                    .returnContent().asBytes();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String doGetWithTimeout(String url, int timeout) {
        try {
            return Request.Get(url)
                    .connectTimeout(timeout).socketTimeout(timeout).execute()
                    .returnContent().asString(StandardCharsets.UTF_8);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static CloseableHttpClient noSslHttpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        final SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (x509CertChain, authType) -> true)
                .build();
        return HttpClientBuilder.create()
                .setSSLContext(sslContext)
                .setConnectionManager(
                        new PoolingHttpClientConnectionManager(
                                RegistryBuilder.<ConnectionSocketFactory>create()
                                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                                        .register("https", new SSLConnectionSocketFactory(sslContext,
                                                NoopHostnameVerifier.INSTANCE))
                                        .build()
                        ))
                .build();
    }

    public static String reflectGetParams(Object obj) {
        if(obj == null) return "";
        StringBuilder builder = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object content = field.get(obj);
                if(content != null) {
                    builder.append(name).append("=");
                    builder.append(URLEncoder.encode(content.toString(), "UTF-8")).append("&");
                }
            } catch(IllegalArgumentException | IllegalAccessException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if(builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());
            builder.insert(0, "?");
        }
        return builder.toString();
    }

    public static String getParamsFromMap(Map<String, Object> map) {
        if(map == null) return "";
        StringBuilder builder = new StringBuilder();
        for(String key : map.keySet()) {
            Object param = map.get(key);
            try {
                if(param != null) {
                    builder.append(key).append("=");
                    builder.append(URLEncoder.encode(param.toString(), "UTF-8")).append("&");
                }
            } catch(IllegalArgumentException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if(builder.length() > 0) {
            builder.delete(builder.length() - 1, builder.length());
            builder.insert(0, "?");
        }
        return builder.toString();
    }

}
