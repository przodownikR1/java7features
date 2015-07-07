package pl.java.scalatech.test.strings;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.google.common.base.Strings;

@Slf4j
public class StringTest {
    @Test
    public void shouldCapitalFirstLetter() {
        String basic = "slawek borowiec";
        String capitalSentence = Character.toUpperCase(basic.charAt(0)) + basic.substring(1);

        log.info("{}", capitalSentence);
    }

    @Test
    public void shouldCenterWord() {
        int width = 15;
        String s = "abc";
        int padSize = width - s.length();
        s = Strings.padStart(s, s.length() + padSize / 2, ' ');
        s = Strings.padEnd(s, width, ' ');
        log.info("{}", s);
    }

}
