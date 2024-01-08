package com.coderdream.freeapps.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CoderDream
 */
@Data
public class BbcAudioEntity implements Serializable {

    /**
     *  Should we farm octopus?
     */
    private String title;

    /**
     * Is it OK to eat animals that can feel emotion? Learn vocabulary on this topic.
     */
    private String description;

    /**
     * Thu, 26 Apr 2018 09:51:00 +0000
     */
    private String pubDate;

    /**
     * http://open.live.bbc.co.uk/mediaselector/6/redir/version/2.0/mediaset/audio-nondrm-download-low/proto/http/vpid/p065dkwq.mp3
     */
    private String url;

}
