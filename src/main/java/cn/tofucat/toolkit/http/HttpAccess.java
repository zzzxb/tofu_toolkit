package cn.tofucat.toolkit.http;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Zzzxb
 * @since 2022/7/19 17:02
 */
public class HttpAccess extends HttpHandler {
    private final static Logger log = Logger.getLogger(HttpAccess.class.getName());
    private final static MediaType MEDIA_TYPE = MediaType.get("application/json; charset=utf-8;");

    public final static HttpAccess INSTANCE = new HttpAccess();

    public String postReq(String url, String body) {
        return postReq(url, body, null);
    }

    public String postReq(String url, String body, Map<String, String> headers) {
        log.info("reqUrl: " + url + " reqBody: " + body);
        final RequestBody requestBody = RequestBody.create(body, MEDIA_TYPE);
        return postReq(url, requestBody, headers);
    }

    public String postReq(String url, RequestBody requestBody, Map<String, String> headers) {
        final Request.Builder builder = new Request.Builder()
                .url(url.trim())
                .post(requestBody);
        addHeaders(builder, headers);
        return exec(builder.build());
    }

}
