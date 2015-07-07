package pl.java.scalatech.test.file;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

@Slf4j
public class AppendStrToFileTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private File file;

    @Before
    public void clean() throws IOException {
        testFolder.newFolder("test_");
        file = testFolder.newFile("t.txt");
        log.info("file :  {}", file.getAbsolutePath());

    }

    @Test
    public void shouldAppendText2FileJava7Approach() throws IOException {

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            out.println("przodownik");
        } catch (IOException e) {
            log.error("{}", e);
        }
        assertThat(Files.readLines(file, Charsets.UTF_8)).hasSize(1);
        assertThat(Files.readLines(file, Charsets.UTF_8)).contains("przodownik");
    }

    @Test
    public void append_to_file_guava() throws IOException {
        Files.append("przodownik", file, Charsets.UTF_8);
        List<String> result = Files.readLines(file, Charset.defaultCharset(), new LineProcessor<List<String>>() {
            List<String> ret = Lists.newArrayList();

            @Override
            public boolean processLine(String line) throws IOException {

                return ret.add(line.trim());
            }

            @Override
            public List<String> getResult() {

                return ret;
            }
        });
        assertThat(Files.readLines(file, Charsets.UTF_8)).hasSize(1);
        assertThat(Files.readLines(file, Charsets.UTF_8)).contains("przodownik");
    }

    @Test
    public void shouldGuavaWriteTextToFile() throws IOException {
        writeToFile(file, "przodownik");
        assertThat(Files.readLines(file, Charsets.UTF_8)).hasSize(1);
        assertThat(Files.readLines(file, Charsets.UTF_8)).contains("przodownik");
    }

    private void writeToFile(File file, String contents) {
        checkNotNull(file, "File must exits");
        checkNotNull(contents, "Unable to write null contents.");

        try {
            Files.write(contents.getBytes(), file);
        } catch (IOException fileIoEx) {
            log.error("{}", fileIoEx);

        }
    }
}
