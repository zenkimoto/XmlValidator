import java.util.List;

public class XmlValidator {
    private XmlTokenizer tokenizer = new XmlTokenizer();

    public boolean validate(String xml) {
        List<String> tags = tokenizer.parse(xml);

        debugPrintTokens(tags);

        return tags.get(1).equals("/" + tags.get(0));
    }

    private void debugPrintTokens(List<String> tokens) {
        System.out.println("---------------------");
        for (String token : tokens) {
            System.out.println(token);
        }
        System.out.println("---------------------");
    }
}
