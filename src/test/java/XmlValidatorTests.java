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
    public void shouldReturnTrue_WhenSingleStartAndEndTag() {
        boolean result = sut.validate("<book></book>");

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_WhenTagDoesNotClose() {
        boolean result = sut.validate("<book");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalse_WhenNoTagNameProvided() {
        boolean result = sut.validate("<>");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalse_whenOpenAndCloseTagsDoNotMatch() {
        boolean result = sut.validate("<book></bool>");

        assertFalse(result);
    }

    @Test
    public void shouldIgnoreSpaces_WhenSpacesAreBeforeAndAfterTags() {
        boolean result = sut.validate("         <book>   </book>  ");

        assertTrue(result);
    }

    @Test
    public void shouldReturnTrue_WhenTagsHaveContent() {
        boolean result = sut.validate("    <note>Hello World!</note> ");

        assertTrue(result);
    }

    @Test
    public void shouldReturnTrue_WhenXmlContainsChildNodes() {
        String xml = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_WhenChildNodesDoNotMatchOpenAndCloseTags() {
        // Note: <from> does not match </Ffrom>
        String xml = "<note><to>Tove</to><from>Jani</Ffrom><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertFalse(result);
    }

    @Test
    public void shouldReturnTrue_WhenTagsHaveAttributes() {
        String xml = "<note id=\"1234\" category=\"todo\"><to>Tove</to><from>Jani</from><heading type=\"header\">Reminder</heading><body>Don't forget me this weekend!</body></note>";

        boolean result = sut.validate(xml);

        assertTrue(result);
    }
}
