package com.coderdream.freeapps.mstts;

public interface Listen {
    void mp3Part(AudioMp3Part part);

    void end(String message);

    byte[] getMP3();
}
