package com.coderdream.freeapps.util.nlp;

public class CnTest {

    public static void main(String []args){
        System.out.println(new Segmentation("这家酒店很好，我很喜欢。").getSegtext());
        System.out.println(new Segmentation("他和我在学校里常打桌球。").getSegtext());
        System.out.println(new Segmentation("貌似实际用的不是这几篇。").getSegtext());
        System.out.println(new Segmentation("硕士研究生产。").getSegtext());
        System.out.println(new Segmentation("我是中国人。").getSegtext());
    }
}
