package test.commodity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {


    // @Query(value = "SELECT * FROM commodity WHERE status & ?1")
    List<Commodity> findByStatus(int status);

    @Query(value = "SELECT * FROM commodity WHERE status & ?1", nativeQuery = true)
    List<Commodity> findByStatusByNative(int status);

}
