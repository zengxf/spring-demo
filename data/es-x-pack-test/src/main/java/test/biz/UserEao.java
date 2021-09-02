package test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserEao {

    @Autowired
    private ElasticsearchRestTemplate ert;

    public void createIndex() {
        IndexOperations indexOp = ert.indexOps(User.class);
        if (!indexOp.exists()) {
            indexOp.create();
        }
    }

    public void deleteIndex() {
        IndexOperations indexOp = ert.indexOps(User.class);
        if (indexOp.exists()) {
            indexOp.delete();
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

    public SearchHits<User> searchHits(Query query) {
        SearchHits<User> hits = ert.search(query, User.class);
        return hits;
    }

    public void batchSave(List<User> list) {
        ert.save(list);
    }

}
