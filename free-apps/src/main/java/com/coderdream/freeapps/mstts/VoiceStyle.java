package com.coderdream.freeapps.mstts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 说话风格
 */
@AllArgsConstructor
@Getter
public enum VoiceStyle {
    general("general","General"),
    assistant("assistant","Assistant"),
    chat("chat","Chat"),
    customerservice("customerservice","Customer Service"),
    newscast("newscast","Newscast"),
    affectionate("affectionate","Affectionate"),
    angry("angry","Angry"),
    calm("calm","Calm"),
    cheerful("cheerful","Cheerful"),
    disgruntled("disgruntled","Disgruntled"),
    fearful("fearful","Fearful"),
    gentle("gentle","Gentle"),
    lyrical("lyrical","Lyrical"),
    sad("sad","Sad"),
    serious("serious","Serious"),
    friendly("friendly","Friendly"),
    poetry_reading("poetry-reading","Poetry-reading"),
    ;
    private String code;
    private String desc;
}
