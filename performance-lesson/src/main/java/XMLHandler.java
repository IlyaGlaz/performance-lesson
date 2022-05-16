import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

    private final StringBuilder stringBuilder = new StringBuilder();
    private int start;
    private int end;
    private int count;
    private boolean countFlag;

    public XMLHandler() {
        countFlag = true;
    }

    public XMLHandler(int start, int limit) {
        this.start = start;
        this.end = start + limit;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("voter")
                && count >= start && count < end
                && !countFlag) {
            stringBuilder.append((stringBuilder.length() == 0 ? "" : ","))
                    .append("('").append(attributes.getValue("name"))
                    .append("'")
                    .append(", ");
            stringBuilder.append("'")
                    .append(attributes.getValue("birthDay"))
                    .append("')");
        }

        if (qName.equalsIgnoreCase("voter")) {
            count++;
        }
    }

    public int getVotersCount() {
        return count;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void resetBuilder() {
        stringBuilder.setLength(0);
    }
}
