package test.user;

import cn.hutool.core.map.MapBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import test.utils.HttpUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@Slf4j
public class TestUserController {

    @Test
    public void testFindAll() throws Exception {
        HttpResponse res = HttpUtils.get("http://localhost:9066/user/find-all");
        printRes(res);
    }

    @Test
    public void testFindOne() throws Exception {
        Map<String, Object> paths = MapBuilder.<String, Object>create()
                .put("id", "id-1")
                .build();
        Map<String, Object> params = MapBuilder.<String, Object>create()
                .put("key1", "中文")
                .build();
        Map<String, Object> headers = MapBuilder.<String, Object>create()
                .put("token", LoginController.TOKEN)
                .build();
        HttpResponse res = HttpUtils.get("http://localhost:9066/user/find-one/{id}", paths, params, headers);
        printRes(res);
    }

    @Test
    public void testCreate() throws Exception {
        UserDto user = new UserDto()
                .setName("test-11")
                .setAge(32)
                .setRemark("单元测试-创建");
        HttpResponse res = HttpUtils.post("http://localhost:9066/user/create", user);
        printRes(res);
    }

    public static void printRes(HttpResponse res) throws IOException {
        System.out.println();
        log.info("status: [{}]", res.getStatusLine());
        Stream.of(res.getAllHeaders())
                .forEach(header -> log.info("[{}]: [{}]", header.getName(), header.getValue()));
        HttpEntity entity = res.getEntity();
        String json = EntityUtils.toString(entity, "UTF-8");
        log.info("content-json: [ {} ]", json);
        System.out.println();
    }

}
