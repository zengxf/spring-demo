package cn.zxf.spring_research.biz;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Component
public class Biz2Service {

    @PostConstruct
    public void init() {
        log.info( "== Biz2Service init! ==" );
    }

    public void hello( @NotEmpty( message = "msg 不能为空" ) String msg ) {
        log.info( "== hello == {}", msg );
    }

    public void hello( @Valid BizDto dto ) {
        log.info( "== hello == dto: {}", dto );
    }

}
