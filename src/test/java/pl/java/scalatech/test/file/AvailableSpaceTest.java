package pl.java.scalatech.test.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Path;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;

@Slf4j
public class AvailableSpaceTest {

    private static File file;

    @Before
    public void init() {
        file = new File("src/test/resources/strings.txt");
        source = file.toPath();
    }

    static Path source;

    @Test
    public void get_available_space() {

        File file = source.toFile();

        long freeSpace = file.getFreeSpace() / (1024 * 1024);

        log.info("+++ fS :  {}", freeSpace);
    }

    @Test
    public void get_available_space_nio() throws IOException {

        FileStore store = java.nio.file.Files.getFileStore(source);

        long availableSpace = store.getUsableSpace() / (1024 * 1024);
        log.info("+++ aS:   {}", availableSpace);

    }
}
