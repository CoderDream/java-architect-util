package com.coderdream.freeapps.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CoderDream
 */
@Data
public class BbcPageInfoEntity implements Serializable {

    /**
     *  https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2023/ep-231102
     *
     *  ep = href.substring(href.lastIndexOf("-") + 1);
     */
    private String href;

    /**
     * pic src="https://ichef.bbci.co.uk/images/ic/624xn/p0gbt487.jpg"
     *
     * https://ichef.bbci.co.uk/images/ic/1248xn/p0gbt487.jpg
     */
    private String dataPid;

}
