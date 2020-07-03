import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XmlValidator {
    public boolean validate(String xml) {
        String[] ar = xml.split(">");
        List<String> tags = Arrays.asList(ar);
        tags = tags.stream()
                .map(s -> s.trim())
                .map(s -> s.replaceFirst("<", ""))
                .collect(Collectors.toList());

        return tags.get(1).equals("/" + tags.get(0));
    }
}
