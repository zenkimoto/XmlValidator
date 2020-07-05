import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void shouldParseStringContentInXmlTags() {
        List<String> result = sut.parse("<note>Hello World!</note>");

        assertEquals(3, result.size());
        assertEquals(result.get(0), "<note>");
        assertEquals(result.get(1), "Hello World!");
        assertEquals(result.get(2), "</note>");
    }

    @Test
    public void shouldIgnoreUnclosedTags() {
        List<String> result = sut.parse("<note<Hello World!</note>");

        for (String s : result) {
            System.out.println(s);
        }

        assertEquals(0, result.size());
    }

}
