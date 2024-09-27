package cn_zxf_test.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <p/>
 * Created by ZXFeng on 2024/9/27
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User WHERE mobile = ?1 OR email = ?1")
    User login(String account);

    @Query("SELECT status FROM User WHERE id = ?1")
    Integer findStatus(Integer id);

}

