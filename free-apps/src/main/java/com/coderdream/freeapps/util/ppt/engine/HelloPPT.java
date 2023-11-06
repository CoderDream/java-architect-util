package com.coderdream.freeapps.util.ppt.engine;



import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import io.gitee.jinceon.core.SimpleEngine;
import java.util.HashMap;
import java.util.Map;
import io.gitee.jinceon.core.DataSource;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CoderDream
 */
public class HelloPPT {
    public static void main(String[] args) {

        // 1. create engine instance 创建引擎
        SimpleEngine engine = new SimpleEngine( CdPptxUtils.getTemplatePath() + "hello-ppt.pptx");

        // 2. add data to dataSource 填充数据
        DataSource dataSource = new DataSource();
        User user = new User("jinceon");
        Map props = new HashMap();
        props.put("key1", "value1");
        props.put("key2", "value2");
        dataSource.setVariable("user", user);
        dataSource.setVariable("props", props);
        engine.setDataSource(dataSource);

        // 3. render data to template 将数据渲染到模板上
        engine.process();

        // 4. save result
        engine.save(CdPptxUtils.getTemplatePath() + "hello-ppt-render.pptx");
    }
}

@Data
@AllArgsConstructor
class User {
    private String name;
}
