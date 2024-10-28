import org.example.utils.PostalCodeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostalCodeTests {

    @Test
    public void formatPostalCode_withoutSpace() {
        String expected = "1234AB";
        String actual = PostalCodeUtils.formatPostalCode("1234AB");

        assertEquals(expected, actual);
    }

    @Test
    public void formatPostalCode_withSpace() {
        String expected = "1234AB";
        String actual = PostalCodeUtils.formatPostalCode("1234 AB");

        assertEquals(expected, actual);
    }

    @Test
    public void formatPostalCode_withoutSpace_lowerCase() {
        String expected = "1234AB";
        String actual = PostalCodeUtils.formatPostalCode("1234ab");

        assertEquals(expected, actual);
    }

    @Test
    public void isValidPostalCode_valid() {
        assertTrue(PostalCodeUtils.isValidPostalCode("1234AB"));
    }

    @Test
    public void isValidPostalCode_invalidFormat() {
        assertFalse(PostalCodeUtils.isValidPostalCode("123AB"));
    }

    @Test
    public void isValidPostalCode_invalid_noLetters() {
        assertFalse(PostalCodeUtils.isValidPostalCode("1234"));
    }

    @Test
    void isValidPostalCodeRegion_valid() {
        assertTrue(PostalCodeUtils.isValidPostalCodeRegion("1234AB"));
    }

    @Test
    void isValidPostalCodeRegion_invalid() {
        assertFalse(PostalCodeUtils.isValidPostalCodeRegion("9679AB"));
    }
}
