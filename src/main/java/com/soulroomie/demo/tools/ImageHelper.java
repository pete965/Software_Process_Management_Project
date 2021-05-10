package com.soulroomie.demo.tools;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class ImageHelper {


    /**\
     * need to check imgBase64Str is not null and right
     * @param imgBase64Str
     * @return
     * @throws IOException
     */
    public static byte[] string2Image(String imgBase64Str) throws IOException {
        byte[] b = new BASE64Decoder().decodeBuffer(imgBase64Str);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return b;
    }
}
