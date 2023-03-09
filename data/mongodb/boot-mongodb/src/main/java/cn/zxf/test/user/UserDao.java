package cn.zxf.test.user;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    private MongoTemplate mongo;

    public void saveUser(User user) {
        mongo.save(user);
    }

    public void batchInsert(List<User> list) {
        mongo.insertAll(list);
    }

    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        User user = mongo.findOne(query, User.class);
        return user;
    }

    public int updateUser(User user) {
        Query query = new Query(Criteria.where("uid").is(user.getUid()));
        Update update = new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        UpdateResult result = mongo.updateFirst(query, update, User.class);
        return (int) result.getModifiedCount();
    }

    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("uid").is(id));
        mongo.remove(query, User.class);
    }

}
