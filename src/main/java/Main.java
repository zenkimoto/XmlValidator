public class Main {
    public static void main(String[] args) {
        XmlValidator validator = new XmlValidator();

        validator.validate("    <book>   </book>");
    }
}
