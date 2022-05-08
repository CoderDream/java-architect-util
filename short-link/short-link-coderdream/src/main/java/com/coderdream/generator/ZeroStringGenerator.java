
package com.coderdream.generator;

import com.coderdream.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 功能描述
 *
 * @since 2022-05-05
 */
@Slf4j
@Service
public class ZeroStringGenerator {

    private List<String> stringList = new ArrayList<>();

    /**
     *
     * @param randomBit 随机字符串的位数
     * @return
     */
    public String generateCode(Integer randomBit) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < randomBit; i++) {
            stringBuffer.append("0");
        }

        return stringBuffer.toString();
    }

    public ZeroStringGenerator() {

        Integer queueSizeConfig = Constants.CHARS.toCharArray().length;
        for (int i = 0; i < queueSizeConfig; i++) {
            Character character = Constants.CHARS.toCharArray()[i];
            stringList.add(character.toString());
        }

        System.out.println(stringList.size());
    }

}

