package com.server.venus.utils;

import com.server.venus.entity.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 项目名称：venus
 * 类名称：TokenUtils
 * 类描述：Token工具类
 * 创建人：yingx
 * 创建时间： 2019/9/26
 * 修改人：yingx
 * 修改时间： 2019/9/26
 * 修改备注：后续使用了spring security需要进行更改，使用UserDetail
 */
public class TokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);


    /**
     * 获取Token
     *
     * @param username   用户名
     * @param isRemember 是否记住我 默认否
     * @return java.lang.String
     * @author yingx
     * @date 2019/9/26
     */
    public static String getToken(String username, boolean isRemember) {

        // 有效期
        Long expiration = isRemember ? Constants.EXPIRATION_REMEMBER : Constants.EXPIRATION;
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET)
                .setIssuer(Constants.ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
        return token;
    }

    /**
     * 根据token获取token中用户名
     *
     * @param token 用户token
     * @return java.lang.String
     * @author yingx
     * @date 2019/9/26
     */
    public static String getUsernameByToken(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token).getBody();
            if (claims != null) {
                return claims.getSubject();
            }
        } catch (Exception e) {
            logger.error("TokenUtils getUsernameByToken error!", e);
        }
        return null;
    }

    /**
     * 判断用户的token是否过期
     *
     * @param token 用户token
     * @return boolean
     * @author yingx
     * @date 2019/9/26
     */
    public static boolean isExpiration(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            logger.error("TokenUtils isExpiration error!", e);
            return false;
        }
    }

    /**
     * 验证用户的token是否有效
     *
     * @param username
     * @param token
     * @return boolean
     * @author yingx
     * @date 2019/9/26
     */
    public static boolean validateToken(String username, String token) {

        String usernameByToken = getUsernameByToken(token);
        boolean result = usernameByToken.equals(username);
        return result;
    }

    /**
     * 验证用户（数据库）及 token是否有效
     *
     * @param username 用户名
     * @param token    用户token
     * @param user     数据库用户
     * @return boolean
     * @author yingx
     * @date 2019/9/26
     */
    public static boolean validateUser(String username, String token, UserDetailsImpl user) {

        boolean result = username.equals(user.getUsername()) && isExpiration(token);
        return result;
    }

    /**
     * 刷新令牌
     *
     * @param token
     * @return java.lang.String
     * @author yingx
     * @date 2019/10/25
     */
    public static String refreshToken(String token) {

        String username = getUsernameByToken(token);
        return getToken(token, false);
    }

    private static Claims getTokenBody(String token) {

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.error("TokenUtils getTokenBody error!", e);
            claims = null;
        }
        return claims;
    }

    public static String getUserRole(String token) {

        return (String) getTokenBody(token).get("rol");
    }
}
