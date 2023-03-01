package cn_zxf_test.jdbc;

import cn_zxf_test.BaseTester5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/1.
 */
@Slf4j
public class TestInsert extends BaseTester5 {

    @Autowired
    JdbcTemplate jdbc;

    @Test
    public void print() {
        log.info("JDBC: [{}]", jdbc);
    }

    @Test
    public void insertOne() {
        this.insert(10);
    }

    @Test
    public void insert100() {
        this.batchInsert(100);
    }

    @Test
    public void insert1000() {
        this.batchInsert(1000);
    }

    @Test
    public void insert10000() {
        this.batchInsert(10000);
    }

    @Test
    public void insert10_0000() {
        this.batchInsert(10_0000);
    }

    @Test
    public void batchBuildUUID() {
        int size = 10_0000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            UUID.randomUUID();
        }
        long use = System.currentTimeMillis() - start;
        log.info("use-time-ms: [{}]", use);
    }

    private void batchInsert(int size) {
        this.insertOne(); // 预热
        log.info("start ---------------------");
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            this.insert(i);
        }
        log.info("end ---------------------");
        long use = System.currentTimeMillis() - start;
        log.info("use-time-ms: [{}]", use);
    }

    private void insert(int i) {
        int fid = i % 100 + 100;
        int tid = i % 200 + 200;
        int type = i % 3 + 1;
        String body = UUID.randomUUID() + "---" + i;
        int status = i % 2 + 1;
        LocalDateTime now = LocalDateTime.now();
        String sql = "INSERT INTO im_msg(from_uid, to_uid, msg_type, msg_body, msg_status, send_time) \n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, fid, tid, type, body, status, now);
    }

}
