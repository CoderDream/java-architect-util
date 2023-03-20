package demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import lombok.Getter;

/**
 * X-RequestId:B89AD8BA80084865AB6D3F1BF10C0DCC Content-Type:application/json; charset=utf-8
 * Path:turn.end
 * <p>
 * {}
 */
@Getter
public class AudioMessage {
    private String sourceMsg;
    private String xRequestId;
    private String contentType;
    private String path;

    public AudioMessage(String msg) {
        this.sourceMsg = msg;
        initByByte(msg.getBytes(StandardCharsets.UTF_8));
    }

    private void initByByte(byte[] bytes) {
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
