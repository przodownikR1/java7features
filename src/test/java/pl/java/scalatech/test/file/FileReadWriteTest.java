package pl.java.scalatech.test.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author przodownik
 * Module name :    java7features
 * Creating time :  30 maj 2014
 */
public class FileReadWriteTest {

    @Test
    public void shouldWriteAndReadFile() throws IOException{
        //given
        List<String> lines = Lists.newArrayList("first", "second", "third");
        //when
        Files.write(Paths.get("bar.txt"), lines, Charset.forName("UTF-8"));
        //then
        lines = Files.readAllLines(Paths.get("bar.txt"), Charset.forName("UTF-8"));
        Assertions.assertThat(lines).contains("first","second","third");
    }
}
