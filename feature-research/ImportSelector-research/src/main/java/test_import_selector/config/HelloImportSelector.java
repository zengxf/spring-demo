package test_import_selector.config;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import lombok.extern.slf4j.Slf4j;
import test_import_selector.biz.HelloServiceA;
import test_import_selector.biz.HelloServiceB;

@Slf4j
public class HelloImportSelector implements ImportSelector {

    @Override
    public String[] selectImports( AnnotationMetadata importingClassMetadata ) {
        log.info( "Annotations ==> {}", importingClassMetadata.getAnnotationTypes() );
        log.info( "Selector ==> {}", importingClassMetadata.getAnnotationAttributes( Import.class.getName() ) );
        return new String[] { HelloServiceA.class.getName(), HelloServiceB.class.getName() };
    }

}