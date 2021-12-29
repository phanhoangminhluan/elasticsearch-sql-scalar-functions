package com.luanphm.sql.engines.spark.expression.function.scalar.string;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.28 23:21
 */
public class AdditionalStringFunctionUtils {

    public static String md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(s.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
