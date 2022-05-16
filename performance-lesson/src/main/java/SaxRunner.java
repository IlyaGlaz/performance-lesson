import org.xml.sax.SAXException;
import util.ParserManager;
import util.PropertiesUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class SaxRunner {

    private static final String FILE = "file.source";
    private static final int LIMIT = 1_000_000;


    public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException {

        SAXParser parser = ParserManager.getParser();
        VotersDao votersDao = new VotersDao();
        File file = new File(PropertiesUtil.get(FILE));
        XMLHandler xmlHandler = new XMLHandler();
        parser.parse(file, xmlHandler);
        int votersCount = xmlHandler.getVotersCount();

        long start = System.currentTimeMillis();

        for (int i = 0; i < votersCount; i += LIMIT) {
            XMLHandler handler = new XMLHandler(i, LIMIT);
            parser.parse(file, handler);
            System.out.println(votersDao.saveVoters(handler.getStringBuilder()));
            handler.resetBuilder();
        }

        long finish = System.currentTimeMillis() - start;

        System.out.println(finish);
    }
}
