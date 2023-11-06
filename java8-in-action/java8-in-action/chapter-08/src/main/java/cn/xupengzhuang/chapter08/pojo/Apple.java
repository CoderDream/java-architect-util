package cn.xupengzhuang.chapter08.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apple implements Fruit {
    private String color;
    private Integer weight;

    public Apple(String color) {
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return color;
    }
}
