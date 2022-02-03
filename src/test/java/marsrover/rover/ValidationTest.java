package marsrover.rover;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationTest {
    @Test
    void testIfFileContainsRLMNSWE() throws FileNotFoundException {

       String validFile ="autotests/testInput.txt";
        assertFalse(Validation.fileContainsInvalidDigit(validFile));
    }

    @Test
    void testIfFileDONOTContainsRLMNSWE() throws FileNotFoundException {

        String validFile ="autotests/testInputWrongFormat.txt";
        assertTrue(Validation.fileContainsInvalidDigit(validFile));
    }
    @Test
    void testFileNotFound() throws FileNotFoundException {

        String file404 ="autotests/notExistentFile.txt";
        assertThrows(FileNotFoundException.class, () ->
                Validation.fileContainsInvalidDigit(file404), "file not found");
    }
}
