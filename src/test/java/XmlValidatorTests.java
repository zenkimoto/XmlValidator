import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void justAnExample() {
        System.out.println("This test method should be run");

        assertEquals(2, 2);
    }

    @Test
    public void shouldValidateSimpleXml() {
        boolean result = sut.validate("<book></book>");

        assertEquals(true, result);
    }

    @Test
    public void shouldValidateInvalidXml_whenTagsDoNotMatch() {
        boolean result = sut.validate("<book></bool>");

        assertEquals(false, result);
    }
}
