package cn.xupengzhuang.chapter10;

import java.util.Optional;

public class OptionalUtility {
    public static Optional<Integer> stringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
