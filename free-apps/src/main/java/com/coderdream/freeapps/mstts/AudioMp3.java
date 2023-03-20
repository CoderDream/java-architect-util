package com.coderdream.freeapps.mstts;

import com.google.common.primitives.Bytes;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public class AudioMp3 {
    private final ArrayList<AudioMp3Part> parts = new ArrayList<>();
    private String requestId;
    private String streamId;

    public byte[] getMp3Byte() {
        byte[] b = new byte[0];
        //将所有mp3片段数据合并成一个完整的mp3文件
        for (AudioMp3Part b1 : parts) {
            b = Bytes.concat(b, b1.getMp3Part());
        }
        return b;
    }

    public void add(AudioMp3Part part) {
        parts.add(part);
    }


}
