package test.user;

import cn.hutool.core.map.MapBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.junit.Test;
import test.utils.HttpUtils;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on 2022/7/13.
 */
@Slf4j
public class TestBizController {

    @Test
    public void testHeader() throws Exception {
        Map<String, Object> paths = MapBuilder.<String, Object>create()
                .put("id", "id-1")
                .build();
        Map<String, Object> params = MapBuilder.<String, Object>create()
                .put("p-key1", "中文")
                .put(" p- space ", " test 1 ")
                .put("p-cn-Xx-xx", "xx 0 ")
                .put("p-中a", " test 2 ")
                .build();
        Map<String, Object> headers = MapBuilder.<String, Object>create()
                .put("token", LoginController.TOKEN)
                .put("space", " test-1 ")
                .put("cn-Xx-%E4%B8%", "中文-%E4%B8%")
                .put("cn-Xx", "中文-%E4%B8%")
                .put("cn-Xx-xx", "xx 0 ")
                // .put("cn中", "中xx")
                // .put(" key1 ", " test-key-1 ")
                // .put("key2 ", " test-key-2 ")
                // .put(" key3", " test-key-3 ")
                .build();
        HttpResponse res = HttpUtils.get("http://localhost:9066/api/biz/herder", paths, params, headers);
        TestUserController.printRes(res);
    }

}
