package com.coderdream.freeapps.util.ppt.engine;


import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import io.gitee.jinceon.core.DataSource;
import io.gitee.jinceon.core.SimpleEngine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CoderDream
 */
public class HelloPPTV2 {

    public static void main(String[] args) {

        // 1. create engine instance 创建引擎
        SimpleEngine engine = new SimpleEngine(CdPptxUtils.getTemplatePath() + "6minV2.pptx");

        // 2. add data to dataSource 填充数据
        DataSource dataSource = new DataSource();
        byte[] png = new byte[0];
        try {
            png = Files.readAllBytes(Paths.get(CdPptxUtils.getResourcePath() + "230504.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource.setVariable("img1", png);
        engine.setDataSource(dataSource);

        // 3. render data to template 将数据渲染到模板上
        engine.process();

        // 4. save result
        engine.save(CdPptxUtils.getTemplatePath() + "6minV2-render.pptx");
    }
}

