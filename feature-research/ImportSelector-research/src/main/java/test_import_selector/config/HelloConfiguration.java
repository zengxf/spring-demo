package test_import_selector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( HelloImportSelector.class )
public class HelloConfiguration {

}
