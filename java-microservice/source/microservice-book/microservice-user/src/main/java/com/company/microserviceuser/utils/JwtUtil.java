package com.company.microserviceuser.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import static com.company.microserviceuser.constant.DigitalConstant.EXPIRE_TIME_ONE_MINUTE;
import static com.company.microserviceuser.constant.StringConstant.PRIVATE_TOKEN_JWT;

/**
 * Jwt工具
 *
 * @author xindaqi
 * @since 2021-01-15
 */
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 生成签名
     *
     * @param username 用户名
     * @return 签名
     */
    public static String generateToken(String username) {
        Instant instant = LocalDateTime.now().plusMinutes(2L).atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant);
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_ONE_MINUTE);
        // 私钥加密
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_TOKEN_JWT);
        // 设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header).withClaim("username", username).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 验证Token
     *
     * @param token 令牌
     * @return 验证结果：true/false
     */
    public static boolean tokenVerify(String token) {
        try {
            // 私钥加密
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_TOKEN_JWT);
            // 构建验证器
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            // Token解码
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            logger.info("过期时间：{}", decodedJWT.getExpiresAt());
            return true;
        } catch(IllegalArgumentException e) {
            logger.error("参数异常：", e);
            return false;
        } catch(JWTVerificationException e) {
            logger.error("验证异常：", e);
            return false;
        }
    }

    private JwtUtil() {}
}
