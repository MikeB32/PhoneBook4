package com.example.mike.phonebook4;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Mike on 12/6/2016.
 */

public class EncDec {

    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }

    public String key = stringFromJNI();


    public  String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

    public  String encrypt(String message) throws Exception {
        //KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //kgen.init(128);
        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(message.getBytes());
        return byteArrayToHex(encrypted);
    }
    public  byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }
    public String decrypt(String encrypted) throws Exception {
        //KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //kgen.init(128);
        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(hexToByteArray(encrypted));
        String originalString = new String(original);
        return originalString;
    }

}
