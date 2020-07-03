import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
