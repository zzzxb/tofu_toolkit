package cn.tofucat.toolkit.http;

import cn.tofucat.toolkit.util.ConfigUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Zzzxb
 * @since 2022/7/20 10:38
 */
public abstract class HttpHandler {

    private final static Integer MAX_TOTAL = ConfigUtil.getInteger("http.conn.max.total", 2000);
    private final static Integer CONN_TIMEOUT = ConfigUtil.getInteger("http.conn.timeout", 60000);
    private final static Integer REQ_TIMEOUT = ConfigUtil.getInteger("http.request.timeout", 60000);
    private final static Integer IDE_TIMEOUT = ConfigUtil.getInteger("http.keepalive.timeout", 1000);

    private final static OkHttpClient okHttpClient = new OkHttpClient(initBuild());

    protected HttpHandler() {
    }

    private static OkHttpClient.Builder initBuild() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.sslSocketFactory(OkHttpUtil.getIgnoreInitedSslContext().getSocketFactory(), OkHttpUtil.IGNORE_SSL_TRUST_MANAGER_X509);
//        builder.hostnameVerifier(OkHttpUtil.getIgnoreSslHostnameVerifier());
        builder.connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);
        builder.callTimeout(REQ_TIMEOUT, TimeUnit.SECONDS);
        builder.connectionPool(new ConnectionPool(MAX_TOTAL, IDE_TIMEOUT, TimeUnit.SECONDS));
        return builder;
    }

    protected String exec(Request request) {
        try (final Response execute = okHttpClient.newCall(request).execute()) {
            final String respBody = execute.body().string();
            if (execute.code() != 200) {
                throw new RuntimeException("请求失败" + respBody);
            }
            return respBody;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void addHeaders(Request.Builder builder, Map<String, String> headers) {
        if (null == headers) {
            return;
        }

        final Set<Map.Entry<String, String>> headerSet = headers.entrySet();
        headerSet.forEach(header -> builder.addHeader(header.getKey(), header.getValue()));
    }
}
