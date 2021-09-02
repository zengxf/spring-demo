package test.biz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import test.AbstractAppTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class UserEaoTest extends AbstractAppTest {

    @Autowired
    UserEao eao;
    @Autowired
    ElasticsearchRestTemplate ert;

    @Test
    public void testCreateAndDelete() {
        eao.createIndex();
        eao.deleteIndex();
    }

    @Test
    public void testBaseCRUD() {
        String id = "f88";
        String id2 = "f8866";
        User user;
//                = eao.findOne(id);
//        log.info("id: [{}], user: [{}]", id, user);

        user = this.buildUser(id, 88);
        log.info("save-before user: [{}]", user);
        User po = eao.save(user);
        log.info("po: [{}], save-after: [{}]", po, user);
        user = this.buildUser(id2, 66);
        eao.save(user);

        String delRes = eao.delete(id);
        String delRes2 = eao.delete(id2);
        log.info("delRes: [{}], delRes2: [{}]", delRes, delRes2);

        user = eao.findOne(id);
        log.info("id: [{}], delete-after user: [{}]", id, user);
    }

    private User buildUser(String id, int age) {
        return User.builder()
                .id(id)
                .name("zxf")
                .age(age)
                .build();
    }

    /*** 会自动生成 ID */
    @Test
    public void testIdIsNull() {
        User user = this.buildUser(null, 22);
        User po = eao.save(user);
        log.info("po: [{}], save-after: [{}]", po, user);
    }

    @Test
    public void testBatchSave() {
        String[] cities = {"邵阳", "长沙", "深圳"};
        List<User> list = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> {
                    User user = User.builder()
                            .id("f88-" + i)
                            .name("test-" + i % 10)
                            .birthday(LocalDate.now().minusDays(i))
                            .age(i % 9)
                            .city(cities[i % cities.length])
                            .updateTime(LocalDateTime.now().minusMinutes(i))
                            .build();
                    return user;
                })
                .collect(Collectors.toList());
        eao.batchSave(list);
        list.forEach(user -> log.info("save po: [{}]", user));
    }

    @Test
    public void findList() {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("age").lt(5))
                .build();
        SearchHits<User> hits = ert.search(query, User.class);
        log.info("hits-total: [{}]", hits.getTotalHits());
        List<SearchHit<User>> list = hits.getSearchHits();
        list.forEach(hit -> log.info("hit-user: [{}]", hit.getContent()));
    }

    @Test
    public void testAggAge() {
        // new CriteriaQuery(new Criteria("age").lessThan(5))

        Query query = new NativeSearchQueryBuilder()
                .withSearchType(SearchType.DEFAULT)
                .withQuery(QueryBuilders.rangeQuery("age").lt(5))
                .addAggregation(AggregationBuilders.terms("ageAgg").field("age")) // ageAgg 是别名
                .build();
        SearchHits<User> hits = ert.search(query, User.class, IndexCoordinates.of("user"));
        log.info("hits: [{}]", hits);

        List<SearchHit<User>> hitList = hits.getSearchHits();
        hitList.forEach(hit -> {
            log.info("hit: [{}]", hit);
        });

        Terms ageAgg = hits.getAggregations().get("ageAgg");
        log.info("ageAgg: [{}]", ageAgg);
        ageAgg.getBuckets().forEach(bucket ->
                log.info("bucket-key: [{}], bucket-count: [{}]", bucket.getKey(), bucket.getDocCount())
        );
    }

    @Test
    public void testAggCityAndAge() {
        Query query = new NativeSearchQueryBuilder()
                .withSearchType(SearchType.DEFAULT)
//                .withQuery(QueryBuilders.termsQuery("city.keyword", "邵阳", "长沙")) // 字符串类型要加 .keyword
                .addAggregation(
                        AggregationBuilders.terms("cityAgg").field("city.keyword") // cityAgg 是别名
                                .subAggregation(
                                        AggregationBuilders.terms("ageAgg").field("age") // ageAgg 是别名
                                )
                )
                .build();
        SearchHits<User> hits = ert.search(query, User.class, IndexCoordinates.of("user"));
        log.info("hits: [{}]", hits);

        List<SearchHit<User>> hitList = hits.getSearchHits();
        hitList.forEach(hit -> {
            log.info("hit: [{}]", hit);
        });

        Terms cityAgg = hits.getAggregations().get("cityAgg");
        log.info("cityAgg: [{}]", cityAgg);
        cityAgg.getBuckets().forEach(bucket -> {
            log.info("city-bucket-key: [{}], bucket-count: [{}]", bucket.getKey(), bucket.getDocCount());
            Terms ageTerm = bucket.getAggregations().get("ageAgg");
            ageTerm.getBuckets().forEach(ageBucket -> {
                log.info("\t ageBucket-key: [{}], ageBucket-count: [{}]", ageBucket.getKey(), ageBucket.getDocCount());
            });
        });
    }

}
