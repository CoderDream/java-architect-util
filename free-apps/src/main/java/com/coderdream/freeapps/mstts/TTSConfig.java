package com.coderdream.freeapps.mstts;

import lombok.Data;

@Data
public class TTSConfig {

    private LanguageEnum language = LanguageEnum.zhCN_Simplified;
    private VoiceEnum voice = VoiceEnum.Zh_CN_XiaomengNeural;
    private VoiceStyle voiceStyle = VoiceStyle.friendly;
    /**
     * ，0.0<=语速<=3.0
     */
    private double speed = 1.2;
    /**
     * 0.0<=语调<=2.0
     */
    private double pitch = 1.0;
}
