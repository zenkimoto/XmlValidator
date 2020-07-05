public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Missing XML as command line parameter.\n\n");
            System.out.println("Usage: java Main <book></book>");
            return;
        }

        XmlValidator validator = new XmlValidator();

        //String xml = "    <book>   </book>";
        String xml = args[0];

        boolean result = validator.validate(xml);

        System.out.println("The XML provided is " + (result ? "Valid" : "Invalid"));
    }
}
