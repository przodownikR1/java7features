package pl.java.scalatech.test.tryWithResources;

import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

import org.fest.assertions.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

@Slf4j
public class TryWithResourceTest {

    private static File file;

    @BeforeClass
    public static void init() {
        file = new File("src/test/resources/strings.txt");
    }

    @Test
    public void shouldURLConnect() throws IOException {
        URL url = new URL("http://przewidywalna-java.blogspot.com/");
        StringBuilder fileContent;
        try (InputStream in = url.openStream(); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, Charsets.UTF_8));) {
            fileContent = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                fileContent.append(line);
                line = bufferedReader.readLine();
            }
        }
        assertThat(fileContent.toString().contains("przewidywalna-java.blogspot.com/search/label")).isTrue();
    }

    @Test
    public void shouldInputStreamPlusScannerReadSingleAndNextLines() throws IOException {
        try (InputStream inputStream = new FileInputStream(file); Scanner scanner = new Scanner(inputStream);) {
            int lineNumber = 1;
            while (scanner.hasNext()) {
                String fileContent = scanner.nextLine();
                log.info("line {} :  {}", lineNumber, fileContent);
                lineNumber++;
            }
        }

    }

    @Test
    public void shouldGuavaInputStreamIntegrate() throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String fileContent = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
        Closeables.close(inputStream, false);
        Assertions.assertThat(fileContent).contains("R1");
    }

}
