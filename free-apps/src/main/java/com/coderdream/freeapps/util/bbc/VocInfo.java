package com.coderdream.freeapps.util.bbc;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VocInfo {

    private String word;
    private String wordExplainEn;
    private String wordCn;
    private String wordExplainCn;
    private String sampleSentenceEn;
    private String sampleSentenceCn;
}
