package com.edx.shell.android.rommiematic.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;

/**
 * @author Shell_Core
 */
public class Util {

    // Ubicación para obtener el avatar del usuario por el servicio Gravatar.
    public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";

    public static String getAvatarUrl(String email) {
        return GRAVATAR_URL + md5(email) + "?s=64";
    }

    /**
     * Función para obtener el avatar de md5
     * @param s
     * @return
     */
    private static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest :
                    messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() > 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getCurrencyFromDouble(double value) {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        return currencyInstance.format(value);
    }
}
