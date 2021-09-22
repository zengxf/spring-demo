package test.utils;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
public class HttpUtils {

    public static HttpResponse get(
            String url
    ) throws Exception {
        return get(url, null, null, null);
    }

    public static HttpResponse get(
            String url,
            Map<String, Object> paths,
            Map<String, Object> params,
            Map<String, Object> headers
    ) throws Exception {
        return execute("GET", url, paths, params, headers, null);
    }

    public static HttpResponse post(
            String url,
            Object body
    ) throws Exception {
        return execute("POST", url, null, null, null, body);
    }

    public static HttpResponse execute(
            String method,
            String url,
            Map<String, Object> paths,
            Map<String, Object> params,
            Map<String, Object> headers,
            Object body
    ) throws Exception {
        if (MapUtil.isNotEmpty(paths)) {
            String[] finds = new String[paths.size()];
            String[] replaces = new String[paths.size()];
            int i = 0;
            for (Map.Entry<String, Object> entry : paths.entrySet()) {
                finds[i] = "{" + entry.getKey() + "}";
                replaces[i] = entry.getValue().toString();
            }
            url = StringUtils.replaceEach(url, finds, replaces);
        }
        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(url);
        if (MapUtil.isNotEmpty(params)) {
            params.forEach((key, value) -> requestBuilder.addParameter(key, value.toString()));
        }
        if (MapUtil.isNotEmpty(headers)) {
            headers.forEach((key, value) -> requestBuilder.addHeader(key, value.toString()));
        }
        if (body != null) {
            String json = JsonUtils.toJson(body);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            requestBuilder.setEntity(entity);
        }
        requestBuilder.addHeader("Accept", ContentType.APPLICATION_JSON.toString());
        requestBuilder.addHeader("Content-type", ContentType.APPLICATION_JSON.toString());
        HttpUriRequest request = requestBuilder.build();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        return response;
    }


}
