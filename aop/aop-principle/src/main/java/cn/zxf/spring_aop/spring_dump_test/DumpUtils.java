package cn.zxf.spring_aop.spring_dump_test;

import net.sf.cglib.core.DebuggingClassWriter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <br>
 * Created by ZXFeng on 2023/11/10
 */
public class DumpUtils {

    public static void setSavePath() throws URISyntaxException, IOException {
        Path path = Paths.get(DumpUtils.class.getResource("/").toURI());
        Path savePath = path
                .resolve("../../spring-aop-proxy")
                // .resolve("../main") // 打断点，也不能调试进去
                .normalize()
                .toAbsolutePath();
        Files.createDirectories(savePath);

        System.out.println("AOP Class 存放路径：");
        System.out.println(savePath);
        System.out.println("----------------------\n\n");

        // 设置将 CGLib 生成的代理类字节码生成到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, savePath.toString());
    }

}
