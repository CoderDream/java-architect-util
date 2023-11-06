package cn.xupengzhuang.chapter02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apple {
    private String color;
    private Integer weight;

    public Apple(String color) {
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 150;
    }

    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

}
