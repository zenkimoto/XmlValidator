import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlTokenizerTests {
    XmlTokenizer sut;

    @Before
    public void setUp() {
        sut = new XmlTokenizer();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void shouldParseSimpleXml() {
        List<String> result = sut.parse("<book></book>");

        assertEquals(2, result.size());
        assertEquals(result.get(0), "<book>");
        assertEquals(result.get(1), "</book>");
    }

    @Test
    public void shouldParseXmlIgnoringSurroundingSpaces() {
        List<String> result = sut.parse("      <book>    </book>      ");

        assertEquals(2, result.size());
        assertEquals(result.get(0), "<book>");
        assertEquals(result.get(1), "</book>");
    }
}