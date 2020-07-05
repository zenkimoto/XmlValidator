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
    public void shouldValidateInvalidXml_WhenTagDoesNotClose() {
        boolean result = sut.validate("<book");

        assertFalse(result);
    }

    @Test
    public void shouldValidateInvalidXml_WhenNoTagName() {
        boolean result = sut.validate("<>");

        assertFalse(result);
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

    @Test
    public void shouldValidateXmlWithChildNodes() {
        String xml = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertTrue(result);
    }

    @Test
    public void shouldValidateInvalidXmlWithChildNodes() {
        String xml = "<note><to>Tove</to><from>Jani</Ffrom><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertFalse(result);
    }

    @Test
    public void shouldValidateTagsWithAttributes() {
        String xml = "<note id=\"1234\" category=\"todo\"><to>Tove</to><from>Jani</from><heading type=\"header\">Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertTrue(result);
    }
}
