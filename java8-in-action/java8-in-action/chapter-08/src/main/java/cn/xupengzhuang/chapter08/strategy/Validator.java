package cn.xupengzhuang.chapter08.strategy;

public class Validator {
    private ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s){
        return validationStrategy.execute(s);
    }
}
