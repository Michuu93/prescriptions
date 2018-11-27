package pl.michuu93.prescriptions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class AbstractTest {
    protected String loadExample(String filename) throws IOException, URISyntaxException {
        var path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(filename)).toURI());
        return Files.readString(path);
    }
}