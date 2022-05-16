import util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class VotersDao {

    public int saveVoters(StringBuilder stringBuilder) throws SQLException {

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            String insertSql = "INSERT INTO voter_count (name, birthDate) " +
                    "VALUES" + stringBuilder.toString();


            return statement.executeUpdate(insertSql);
        }
    }
}
