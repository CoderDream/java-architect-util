package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
public class FileUtilTests {
    @Test
    public void test1(){
        try {
            String s = FileUtil.processFile((BufferedReader br) -> br.readLine());
            log.info("{}",s);

            String s1 = FileUtil.processFile((BufferedReader br) -> br.readLine() + "," + br.readLine());
            log.info("{}",s1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
