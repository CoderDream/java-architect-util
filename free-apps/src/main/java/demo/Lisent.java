package demo;

public interface Lisent {
    void mp3Part(AudioMp3Part part);

    void end(String message);

    byte[] getMP3();
}
