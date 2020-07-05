import java.util.List;
import java.util.Stack;

public class XmlValidator {
    private final XmlTokenizer tokenizer = new XmlTokenizer();

    public boolean validate(String xml) {
        List<String> tags = tokenizer.parse(xml);

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
