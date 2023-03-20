package demo;

import java.nio.ByteBuffer;
import java.util.Arrays;
import lombok.Getter;

@Getter
public class AudioMp3Part {
    private byte[] bodyByte;
    private byte[] mp3Part;
    private short headSize;
    private AudioMp3MessageHead head;
    private static int i = 0;

    public AudioMp3Part(ByteBuffer buffer) {
        bodyByte = buffer.array();
        if (bodyByte == null || bodyByte.length == 0) {
            throw new RuntimeException("body bytes is null");
        }
        //响应体的前两个字节用于说明接下去的多少个字节是文本消息，文本消息结束就是mp3字节数据，如:128,则表示，前两个字节后的128个字节是文本消息，之后的数据就是mp3字节数据
        headSize = buffer.getShort(0);
        byte[] headBytes = Arrays.copyOfRange(bodyByte, 2, headSize + 2);
        if (headBytes == null || headBytes.length == 0) {
            throw new RuntimeException("head bytes is null");
        }
        head = new AudioMp3MessageHead(headBytes);
        //跳过前两个字节，和文本消息的字节，剩下的字节就是mp3的二进制字节数据体片段
        mp3Part = Arrays.copyOfRange(bodyByte, headSize + 2, bodyByte.length);
    }

    public String getXRequestId() {
        return head.getXRequestId();
    }


}
