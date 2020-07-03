import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XmlTokenizer {
    public List<String> parse(String xml) {
        String[] ar = xml.trim().split(">");
        List<String> tags = Arrays.asList(ar);

        return tags.stream()
                .map(String::trim)
                .map(s -> s + ">")
                .collect(Collectors.toList());
    }
}
