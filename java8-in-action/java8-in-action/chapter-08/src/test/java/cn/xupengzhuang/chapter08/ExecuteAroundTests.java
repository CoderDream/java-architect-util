package cn.xupengzhuang.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class ExecuteAroundTests {

    @Test
    public void test1() throws IOException {

        String s = processFile((BufferedReader br) -> br.readLine());
        log.info("{}",s);

        String s1 = processFile((BufferedReader br) -> br.readLine() + "," + br.readLine());
        log.info("{}",s1);
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("data.txt")))){
            return p.process(br);
        }
    }


}
