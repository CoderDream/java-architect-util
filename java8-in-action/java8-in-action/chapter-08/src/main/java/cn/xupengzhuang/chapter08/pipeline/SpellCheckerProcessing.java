package cn.xupengzhuang.chapter08.pipeline;

public class SpellCheckerProcessing extends ProcessingObject<String>{
    @Override
    public String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
