package com.coderdream.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
}
