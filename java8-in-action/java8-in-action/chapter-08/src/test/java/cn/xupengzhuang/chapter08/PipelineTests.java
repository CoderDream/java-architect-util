package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.pipeline.HeaderTextProcessing;
import cn.xupengzhuang.chapter08.pipeline.ProcessingObject;
import cn.xupengzhuang.chapter08.pipeline.SpellCheckerProcessing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
public class PipelineTests {

    @Test
    public void test1(){
        ProcessingObject<String> processingObject1 = new HeaderTextProcessing();
        ProcessingObject<String> processingObject2 = new SpellCheckerProcessing();
        processingObject1.setProcessingObject(processingObject2);
        String result = processingObject1.handle("Aren't labdas really sexy?!!");
        log.info("result={}",result);
    }

    /**
     * 使用lambda表达式来改写上面的代码逻辑
     */
    @Test
    public void test2(){
        //第一个操作逻辑
        UnaryOperator<String> operator1 = (String text) -> "From xupeng:" + text;
        //第二个操作逻辑
        UnaryOperator<String> operator2 = (String text) -> text.replaceAll("labda", "lambda");
        //将两个操作逻辑连接起来
        Function<String, String> pipeline = operator1.andThen(operator2);
        String result = pipeline.apply("Aren't labdas really sexy?!!");
        log.info("result={}",result);

    }

}
