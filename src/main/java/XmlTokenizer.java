import java.util.ArrayList;
import java.util.List;

public class XmlTokenizer {
    public List<String> parse(String xml) {
        ArrayList<String> tokens = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < xml.length(); i++) {
            char ch = xml.charAt(i);

            switch (ch) {
                case '<':
                    addTokenToTokenList(tokens, xml, index, i);
                    index = i;
                    break;
                case '>':
                    if (index + 1 == i) break;
                    addTokenToTokenList(tokens, xml, index, i + 1);
                    index = i + 1;
                    break;
            }
        }

        return tokens;
    }

    private void addTokenToTokenList(ArrayList<String> tokens, String xml, int startIndex, int endIndex) {
        String token = xml.substring(startIndex, endIndex);
        if (token.trim().length() > 0) {
            tokens.add(token);
        }
    }
}
