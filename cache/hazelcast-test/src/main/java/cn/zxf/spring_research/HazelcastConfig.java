package cn.zxf.spring_research;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
@EnableCaching
@EnableHazelcastHttpSession
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        // 如果有集群管理中心，可以配置
        ManagementCenterConfig centerConfig = new ManagementCenterConfig();
        centerConfig.setUrl( "http://127.0.0.1:8200/mancenter" );
        centerConfig.setEnabled( true );
        MapConfig map = new MapConfig() //
                .setName( "instruments" )
                .setMaxSizeConfig( new MaxSizeConfig( 200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE ) )
                .setEvictionPolicy( EvictionPolicy.LRU )
                .setTimeToLiveSeconds( 20000 );
        return new Config() //
                .setInstanceName( "hazelcast-instance" )
                .setManagementCenterConfig( centerConfig )
                .addMapConfig( map );
    }

}
