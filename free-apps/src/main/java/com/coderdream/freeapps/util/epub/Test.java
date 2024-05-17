package com.coderdream.freeapps.util.epub;

public class Test {

    public static void main(String[] args) {
        String content = "* abc";
        content = content.replaceAll("\\* ", "** ");
        System.out.println(content);
    }
}
