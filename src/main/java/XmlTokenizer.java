import java.util.ArrayList;
import java.util.List;

public class XmlTokenizer {
    public List<String> parse(String xml) {
        ArrayList<String> tokens = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < xml.length(); i++) {
            char ch = xml.charAt(i);
            String token;

            switch (ch) {
                case '<':
                    token = xml.substring(index, i);
                    if (token.trim().length() > 0) {
                        tokens.add(token);
                    }
                    index = i;
                    break;
                case '>':
                    token = xml.substring(index, i + 1);
                    if (token.trim().length() > 0) {
                        tokens.add(token);
                    }
                    index = i + 1;
                    break;
            }
        }

        return tokens;
    }
}
