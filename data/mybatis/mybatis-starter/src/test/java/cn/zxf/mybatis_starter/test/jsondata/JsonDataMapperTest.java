package cn.zxf.mybatis_starter.test.jsondata;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class JsonDataMapperTest {

    @Autowired
    JsonDataMapper mapper;

    @Test
    public void findAll() {
        List<JsonData> list = mapper.findAll();
        log.info("list-size: [{}]", list.size());
        list.forEach(data -> log.info("data: [{}]", data));
    }

    @Test
    public void save() {
        JsonData data = new JsonData()
                .setUserIds(List.of(1L, 2L, 3L).toString())
                .setRemark("test-" + LocalDateTime.now());
        log.info("data: [{}]", data);
        int res = mapper.save(data);
        log.info("save-res: [{}]", res);
        log.info("saved-data: [{}]", data);
    }

}