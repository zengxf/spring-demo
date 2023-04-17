package cn.zxf.mybatis_starter.test.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class UserXmlMapperTest {

    @Autowired
    private UserXmlMapper mapper;

    @Test
    public void findStatus() {
        Integer status = mapper.findStatus(1);
        log.info("status: [{}]", status);
    }

    @Test
    public void findOneStatus() {
        Integer status = mapper.findOneStatus("zxf", 20);
        log.info("status: [{}]", status);
    }

}