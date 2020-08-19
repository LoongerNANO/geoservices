package com.esrichina.geoservices.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;


public class JwtUtil {


    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 24 * 60 * 60 * 1000;

    /**
     * 私钥
     **/
    private static final String SECRETKEY = "VGhlIFNjaWVuY2Ugb2YgV2hlcmU=";

    /**
     * 签发
     */
    public static String sign(String username, String realname) {

        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRATION);

        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRETKEY);

        // 设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        // 生成签名
        return JWT.create().withHeader(header).withClaim("username", username).withClaim("realname", realname).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }

    /**
     * 校验合法性
     */
    public static boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(JwtUtil.sign("adb", "www"));
        long s = System.currentTimeMillis();
        Thread.sleep(1000 * 60);
        System.out.println(System.currentTimeMillis() - s);

    }

}
