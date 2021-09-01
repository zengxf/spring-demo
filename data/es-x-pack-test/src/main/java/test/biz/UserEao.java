package test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class UserEao {

    @Autowired
    private ElasticsearchRestTemplate ert;

    public void createIndex() {
        IndexOperations indexOp = ert.indexOps(User.class);
        if (!indexOp.exists()) {
            Map<String, Object> settings = new HashMap<>();
            settings.put("number_of_shards", 2);
            indexOp.create(settings);
        }
    }

    public User save(User user) {
        User po = ert.save(user);
        return po;
    }

    public String delete(String id) {
        String delId = ert.delete(id, User.class);
        return delId;
    }

    public User findOne(String id) {
        User one = ert.get(id, User.class);
        return one;
    }


}
