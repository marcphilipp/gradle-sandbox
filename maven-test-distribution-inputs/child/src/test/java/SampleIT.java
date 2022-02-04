import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleIT {

    @Test
    void test() {
        assertTrue(Files.exists(Path.of(".db/sample.sql")));
    }
}
