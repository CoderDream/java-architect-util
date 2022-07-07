package com.coderdream.ass;

import lombok.Data;

@Data
public class Event {
    private static String COMMON = ",";
    private String format; //
    private String layer; //
    private String start; //
    private String end; //
    private String style; //
    private String name; //
    private String marginL; //
    private String marginR; //
    private String marginV; //
    private String effect; //
    private String text;

    public String toLine() {
        return format + ": " + layer +
                COMMON + start + COMMON +
                end + COMMON + name + COMMON + style + COMMON +
                marginL + COMMON + marginR + COMMON + marginV + COMMON + effect + COMMON + text;
    }
}
