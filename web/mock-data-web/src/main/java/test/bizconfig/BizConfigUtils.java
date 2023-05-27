package test.bizconfig;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * <br>
 * Created by ZXFeng on 2023/5/27
 */
@Slf4j
public class BizConfigUtils {

    private static final String file = "/biz-config/user-config.json";

    @Deprecated // 打包之后读取有问题
    public static String readContent_ErrRef() throws URISyntaxException, IOException {
        URL url = BizConfigUtils.class.getResource(file);
        log.info("url: [{}]", url);
        URI uri = url.toURI();
        log.info("uri: [{}]", uri);
        Path path = Paths.get(uri);
        log.info("path: [{}]", path);
        return Files.readAllLines(path)
                .stream()
                .collect(Collectors.joining("\n"));
    }

    @Deprecated // 还是有错
    public static String readContentUseSpring_ErrRef() throws IOException {
        URI uri = new ClassPathResource(file).getURI(); // 直接用 URI，会像上面一样出错
        log.info("uri: [{}]", uri);
        Path path = Paths.get(uri);
        log.info("path: [{}]", path);
        return Files.readAllLines(path)
                .stream()
                .collect(Collectors.joining("\n"));
    }

    // OK
    public static String readContentUseSpring() throws IOException {
        // 内部实现也是调用 Class.getResourceAsStream(file)
        InputStream is = new ClassPathResource(file).getInputStream(); // 直接用流就可以
        return IoUtil.read(is, "UTF-8");
    }

    public static String readContent() {
        InputStream is = BizConfigUtils.class.getResourceAsStream(file); // 直接用流就可以
        return IoUtil.read(is, "UTF-8");
    }

}
