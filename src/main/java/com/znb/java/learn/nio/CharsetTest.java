package com.znb.java.learn.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午3:29
 */
public class CharsetTest {

    /**
     * 通过字符集的编码解码过滤无法识别字符
     */
    public void filterCharset() {
        String input = "你好1234恩";
        Charset charset = Charset.forName("ISO-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer buffer = CharBuffer.allocate(32);
        buffer.put(input);
        buffer.flip();
        try {
            ByteBuffer byteBuffer = encoder.encode(buffer);
            CharBuffer cbuf = decoder.decode(byteBuffer);
            System.out.println(cbuf);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new CharsetTest().filterCharset();
    }
}
