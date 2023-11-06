package cn.xupengzhuang.chapter03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try( BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("data.txt")))) {
            return processor.process(br);
        }

    }
}
