package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class StreamTest {

    @Test()
    @DisplayName("InputStream 테스트")
    void InputStream() throws IOException {
        InputStream in = System.in;
        byte[] a = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //일반적으로 read 메서드는 인코딩 후, Integer로 변환되며 1Byte 씩 읽는다.
        // 그러나 byte 배열을 매개변수로 넘기면 배열 크기의 끝까지 read 메서드를 반복하여 데이터를 입력 받는다.
        in.read(a);

        for(byte val:a){
            Assertions.assertEquals(val, 0);
        }
    }

    @Test()
    @DisplayName("InputStreamReader 테스트")
    void InputStreamReader() throws IOException {
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        //InputStreamReader는 InputStream과 다르게 1Byte가 아닌 문자를 처리 가능하게 해준다.
        char[] str = new char[]{'가','나'};
        inputStreamReader.read(str);

        Assertions.assertEquals('가',str[0]);
        Assertions.assertEquals('나',str[1]);
    }

    @Test()
    @DisplayName("BufferedReader 테스트")
    void BufferedReader() throws IOException {
        InputStream in = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        //BufferedReader는 InputStreamReader의 불편함을 해소시켜준다.
        // 만약 문자열을 입력 받고 싶다면 매번 배열을 선언해야하고 가변적인 길이에 대해서는 더욱 불편하다.
        // 그래서 Buffer를 통해 입력 받은 문자를 쌓아둔 뒤 한번에 문자열 처럼 보낸다.
        char[] str = new char[]{'가','나'};
        bufferedReader.read(str);

        Assertions.assertEquals('가',str[0]);
        Assertions.assertEquals('나',str[1]);
    }

}