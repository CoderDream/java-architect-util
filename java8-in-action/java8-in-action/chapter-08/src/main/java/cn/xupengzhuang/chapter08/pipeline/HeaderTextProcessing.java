package cn.xupengzhuang.chapter08.pipeline;

public class HeaderTextProcessing extends ProcessingObject<String>{
    @Override
    public String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
