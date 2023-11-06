package cn.xupengzhuang.chapter10;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Properties {
    private String name;
    private String value;

    public String get(String name){
        if (this.name.equals(name)){
            return this.value;
        }
        return null;
    }
}
