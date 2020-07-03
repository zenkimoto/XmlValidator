import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XmlValidator {
    public boolean validate(String xml) {
        List<String> tags = tokenize(xml);

        return tags.get(1).equals("/" + tags.get(0));
    }

    private List<String> tokenize(String xml) {
        String[] ar = xml.split(">");
        List<String> tags = Arrays.asList(ar);

        return tags.stream()
                .map(String::trim)
                .map(s -> s.replaceFirst("<", ""))
                .collect(Collectors.toList());
    }
}
