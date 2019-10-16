package test.set_singleton;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BizService {

    @PostConstruct
    public void init() {
        log.info( "== BizService init! ==" );
    }

    public void hello() {
        log.info( "== hello ==" );
    }

}

@AllArgsConstructor
@NoArgsConstructor
class StudentA {
    @Setter
    private StudentB studentB;
}

@AllArgsConstructor
@NoArgsConstructor
class StudentB {
    @Setter
    private StudentC studentC;
}

@AllArgsConstructor
@NoArgsConstructor
class StudentC {
    @Setter
    private StudentA studentA;
}