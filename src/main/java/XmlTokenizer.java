import java.util.ArrayList;
import java.util.List;

/**
 * The XML Tokenizer class parses an XML string into nodes and content.
 * All spaces that are not part of content are ignored.
 */
public class XmlTokenizer {
    public List<String> parse(String xml) {
        ArrayList<String> tokens = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < xml.length(); i++) {
            char ch = xml.charAt(i);

            switch (ch) {
                case '<':
                    addTokenIfValid(tokens, xml, index, i);
                    index = i;
                    break;
                case '>':
                    if (index + 1 == i) break;
                    addTokenIfValid(tokens, xml, index, i + 1);
                    index = i + 1;
                    break;
            }
        }

        return tokens;
    }

    /**
     * Adds a token to a token result list if the token appears to be a valid token.
     *
     * @param tokens ArrayList of tokens.  This is the result array list and valid tokens will be added here.
     * @param xml Original XML string
     * @param startIndex Start index of the candidate token
     * @param endIndex End index of the candidate token
     */
    private void addTokenIfValid(ArrayList<String> tokens, String xml, int startIndex, int endIndex) {
        String token = xml.substring(startIndex, endIndex);

        if (isValidToken(token)) {
            tokens.add(token);
        }
    }

    /**
     * Determines if a token is valid using basic checks.
     *
     * @param token Xml token to validate
     * @return True if the token is valid
     */
    private boolean isValidToken(String token) {
        boolean isValidLength = token.trim().length() > 0;

        boolean isValidTag = isValidLength && token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>';
        boolean isValidNonTag = isValidLength && token.charAt(0) != '<' && token.charAt(token.length() - 1) != '>';

        return isValidTag || isValidNonTag;
    }
}
