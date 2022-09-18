package datastructure;

import common.UserEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据校验测试
 * @author xindaqi
 * @since 2021-01-23
 */

public class DataVerification {

    private static final Logger logger = Logger.getLogger("DataVerification");

    public static void main(String[] args) {
        logger.info("数据验证测试");
        List<String> list = Stream.of("xiaxiao", "xiaohua").collect(Collectors.toList());
        UserEntity userEntity = new UserEntity();
        userEntity.setUid(1);
        if(StringUtils.isEmpty(userEntity.getNickname())) {
            logger.info("List is empty");
        } else {
            logger.info(list.get(0));
        }
    }
}
