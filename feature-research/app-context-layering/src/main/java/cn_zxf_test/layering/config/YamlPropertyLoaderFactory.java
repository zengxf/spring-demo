package cn_zxf_test.layering.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@Slf4j
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource createPropertySource(String name, EncodedResource resource) throws IOException {
        String filename = resource.getResource().getFilename();

        log.info("--------------------------");
        log.info("加载资源：name: [{}], file-name: [{}], resource: [{}]", name, filename, resource);

        return new YamlPropertySourceLoader()
                .load(filename, resource.getResource())
                .get(0);
    }

}