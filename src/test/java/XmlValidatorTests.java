import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XmlValidatorTests {
    XmlValidator sut;

    @Before
    public void setUp() {
        sut = new XmlValidator();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void shouldValidateSimpleXml() {
        boolean result = sut.validate("<book></book>");

        assertTrue(result);
    }

    @Test
    public void shouldValidateInvalidXml_whenTagsDoNotMatch() {
        boolean result = sut.validate("<book></bool>");

        assertFalse(result);
    }

    @Test
    public void shouldIgnoreSpacesBeforeAndAfterTags() {
        boolean result = sut.validate("         <book>   </book>  ");

        assertTrue(result);
    }

    @Test
    public void shouldValidateTagsWithContent() {
        boolean result = sut.validate("    <note>Hello World!</note> ");

        assertTrue(result);
    }
}
