package com.coderdream.freeapps.util.bbc;

import lombok.Data;
import lombok.ToString;

/**
 * @author CoderDream
 */
@Data
@ToString
public class WordResult {

    public String ecWordUsphone; // ec -> word -> usphone
    public String ecWordUkphone; // ec -> word -> ukphone
    public String ecWordTrsPos; // ec -> word -> trs -> pos 词性 adj
    public String ecWordTrsTran; // ec -> word -> trs -> tran 翻译
    public String ecExamType; // ec -> exam_type  高中/CET4/CET6/IELTS/TOEFL/SAT/商务英语
    public String expandEeWordTransListSents; // expand_ce -> word -> transList -> sents ->
}
