package pl.java.scalatech.test.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author przodownik
 *         Module name : java7features
 *         Creating time : 30 maj 2014
 */
@Slf4j
public class FileReadWriteTest {

    @Test
    public void shouldWriteAndReadFile() throws IOException {
        //given
        List<String> lines = Lists.newArrayList("first", "second", "third");
        //when
        Files.write(Paths.get("bar.txt"), lines, Charset.forName("UTF-8"));
        //then
        lines = Files.readAllLines(Paths.get("bar.txt"), Charset.forName("UTF-8"));
        Assertions.assertThat(lines).contains("first", "second", "third");
    }

    @Test
    public void shouldShowFilesFromDirectory() throws IOException {
        Files.walkFileTree(Paths.get("bin"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                log.info(" {}", file);
                return FileVisitResult.CONTINUE;

            }
        });
    }

    @Test
    public void shouldScanFolderWork() {
        scan("bin", "g");
    }

    private static void scan(String folder, String pattern) {
        Path dir = Paths.get(folder);

        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            log.info("No such directory!");
        }

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, pattern)) {

            int count = 0;
            for (Path path : ds) {
                log.info("path {}", path);
                count++;
            }

            log.info(" Files match the pattern  {}", count);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
