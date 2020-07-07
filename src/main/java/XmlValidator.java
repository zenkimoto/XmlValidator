import java.util.List;
import java.util.Stack;

/**
 * The XmlValidator class validates simple XML markup.  It checks for
 * matching start and end tags and ignores invalid simple errors.
 * There are no DTD doctype validations.
 * Note: This is a toy class!
 */
public class XmlValidator {
    private final XmlTokenizer tokenizer = new XmlTokenizer();

    /**
     * Validates an XML string for matching start and end tags.
     *
     * @param xml Xml string to validate
     * @return True when XML is validated successfully otherwise false.
     */
    public boolean validate(String xml) {
        List<String> tokens = tokenizer.parse(xml);

        if (tokens.size() == 0) return false;

        return performXmlTokenValidation(tokens);
    }

    private boolean performXmlTokenValidation(List<String> tokens) {
        Stack<String> start_stack = new Stack<>();
        Stack<String> end_stack = new Stack<>();

        for (String token : tokens) {
            start_stack.push(token);
        }

        while (!start_stack.empty()) {
            String token = start_stack.pop();

            if (isEndTag(token)) {
                end_stack.push(token);
            } else if (isStartTag(token)) {
                String endTag = end_stack.pop();

                boolean endTagMatchesStartTag = endTag.equals("</" + getTagName(token) + ">");

                if (!endTagMatchesStartTag) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks to see if the XML tag is an end tag.  i.e. </tagname>
     * @param tag Tag string to validate
     * @return boolean indicating a valid end tag
     */
    private boolean isEndTag(String tag) {
        if (tag.length() <= 3) return false;

        return tag.charAt(0) == '<' && tag.charAt(1) == '/' && tag.charAt(tag.length() - 1) == '>';
    }

    /**
     * Checks to see if the XML tag is a start tag.  i.e. <tagname>
     * @param tag Tag string to validate
     * @return boolean indicating a valid start tag
     */
    private boolean isStartTag(String tag) {
        if (tag.length() <= 2) return false;

        return tag.charAt(0) == '<' && tag.charAt(1) != '/' && tag.charAt(tag.length() - 1) == '>';
    }

    /**
     * Gets the tag name of a start tag when there are attributes.
     * Example:
     *   <tagname id="tag">
     *
     * The method will return "tagname"
     * @param tag Tag string to extract the tag name
     * @return tag name string
     */
    private String getTagName(String tag) {
        if (tag.length() <= 2) return "";

        String[] result = tag.substring(1, tag.length() - 1).split(" ");

        return result[0];
    }
}
