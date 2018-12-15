package cn.jy.util;

import cn.jy.constent.Constent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        try {
            String stringKey = Constent.JWT_SECRET;
            byte[] encodedKey = Base64.decodeBase64(stringKey);
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            return key;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param hour
     * @return
     * @throws Exception
     */
    public static String create(String id, String subject, Map<String, Object> claims, int hour) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            SecretKey key = generalKey();
            JwtBuilder builder = Jwts.builder()
                    .setClaims(claims)
                    .setId(id)
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .signWith(signatureAlgorithm, key);
            if (hour >= 0) {
                long expMillis = nowMillis + (3600*1000*hour);
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }
            return builder.compact();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parse(String jwt) {
        try {
            SecretKey key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
//        long time = 10000L;
//        String jwt = JWTUtils.createJWT("id", "subjec",new HashMap<>(), time);
//        System.out.println(jwt);
//        Claims c = parseJWT(jwt);
//        System.out.println(c.getId());//jwt
//        System.out.println(c.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
//        System.out.println(c.getSubject());//{id:100,name:xiaohong}
//        System.out.println(c.getIssuer());//null

       /* long time = 10000L;
        String jwt = JWTUtils.createJWT("userId", "70", -1);
        System.out.println(jwt);
        Claims claims = parseJWT(jwt);
        System.out.println(claims.getSubject());
        Claims claims1 = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ1c2VySWQiLCJpYXQiOjE1MjU3NDcxNzcsInN1YiI6IjcwIn0._jfVaFdx4T5t8qb3UeQQkzTf2FCsV-v0M9jHK5wKMNk");
        System.out.println(claims1.getSubject());*/
    }


}
