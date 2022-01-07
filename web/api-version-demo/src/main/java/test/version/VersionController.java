package test.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on 2022/1/7.
 */
@RestController
public class VersionController {

    /* 通过 URI 进行版本控制 */

    // curl -i -X GET http://localhost:9001/v1/student
    @GetMapping("/v1/student")
    public Map studentV1() {
        return Map.of(
                "sign", "url-version",
                "name", "zxf-11",
                "version", "v1"
        );
    }

    // curl -i -X GET http://localhost:9001/v2/student
    @GetMapping("/v2/student")
    public Map studentV2() {
        return Map.of(
                "sign", "url-version",
                "name", "zxf-22",
                "version", "v2"
        );
    }

    /* 通过请求参数进行版本控制 */

    // curl -i -X GET http://localhost:9001/student/param?version=v1
    @GetMapping(value = "/student/param", params = "version=v1")
    public Map studentParamV1() {
        return Map.of(
                "sign", "param-version",
                "name", "zxf-11",
                "version", "v1"
        );
    }

    // curl -i -X GET http://localhost:9001/student/param?version=v2
    @GetMapping(value = "/student/param", params = "version=v2")
    public Map studentParamV2() {
        return Map.of(
                "sign", "param-version",
                "name", "zxf-22",
                "version", "v2"
        );
    }

    /* 通过自定义 Header 进行版本控制 */

    // curl -i -X GET -H "x-version: v1" http://localhost:9001/student/header
    // curl -i -X GET --header "x-version: v1" http://localhost:9001/student/header
    @GetMapping(value = "/student/header", headers = "x-version=v1")
    public Map studentHeaderV1() {
        return Map.of(
                "sign", "header-version",
                "name", "zxf-11",
                "version", "v1"
        );
    }

    // curl -i -X GET -H "x-version: v2" http://localhost:9001/student/header
    @GetMapping(value = "/student/header", headers = "x-version=v2")
    public Map studentHeaderV2() {
        return Map.of(
                "sign", "header-version",
                "name", "zxf-22",
                "version", "v2"
        );
    }

    /* 通过媒体类型进行版本控制 */

    // curl -i -X GET -H "Accept: application/api-v1+json" http://localhost:9001/student/produce
    // 不能设置为 application/api-v1-json，要用 +
    @GetMapping(value = "/student/produce", produces = "application/api-v1+json")
    public Map studentProduceV1() {
        return Map.of(
                "sign", "produce-version",
                "name", "zxf-11",
                "version", "v1"
        );
    }

    // curl -i -X GET -H "Accept: application/api-v2+json" http://localhost:9001/student/produce
    @GetMapping(value = "/student/produce", produces = "application/api-v2+json")
    public Map studentProduceV2() {
        return Map.of(
                "sign", "produce-version",
                "name", "zxf-22",
                "version", "v2"
        );
    }

}
