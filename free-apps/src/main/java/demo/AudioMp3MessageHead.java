package demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import lombok.Getter;

@Getter
public class AudioMp3MessageHead {
    private String sourceMsg;
    private String xRequestId;
    private String contentType;
    private String xStreamId;
    private String path;

    public AudioMp3MessageHead(byte[] bytes) {
        this.sourceMsg = new String(bytes);
        analyzeByte(bytes);
    }

    private void analyzeByte(byte[] bytes) {
        try {
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals("")) {
                    String[] split = line.split(":");
                    if ("X-RequestId".equals(split[0])) {
                        this.xRequestId = split[1];
                    } else if ("Content-Type".equals(split[0])) {
                        this.contentType = split[1];

                    } else if ("X-StreamId".equals(split[0])) {
                        this.xStreamId = split[1];

                    } else if ("Path".equals(split[0])) {
                        this.path = split[1];
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
