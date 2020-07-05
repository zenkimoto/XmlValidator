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
        List<String> tags = tokenizer.parse(xml);

        if (tags.size() == 0) return false;

        debugPrintTokens(tags);

        Stack<String> start_stack = new Stack<>();
        Stack<String> end_stack = new Stack<>();

        for (String tag : tags) {
            start_stack.push(tag);
        }

        for (int i = 0; i < tags.size(); i++) {
            String tag = start_stack.pop();

            if (isEndTag(tag)) {
                end_stack.push(tag);
            } else if (isStartTag(tag)) {
                String endTag = end_stack.pop();

                if (!endTag.equals("</" + getTagName(tag) + ">")) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isEndTag(String tag) {
        return tag.charAt(0) == '<' && tag.charAt(1) == '/' && tag.charAt(tag.length() - 1) == '>';
    }

    private boolean isStartTag(String tag) {
        return tag.charAt(0) == '<' && tag.charAt(1) != '/' && tag.charAt(tag.length() - 1) == '>';
    }

    private String getTagName(String tag) {
        String[] result = tag.substring(1, tag.length() - 1).split(" ");

        return result[0];
    }

    private void debugPrintTokens(List<String> tokens) {
        System.out.println("---------------------");
        for (String token : tokens) {
            System.out.println(token);
        }
        System.out.println("---------------------");
    }
}
