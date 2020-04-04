import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME =
            "minions_db";

    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;
    private static BufferedReader reader;


    public static void main(String[] args) throws SQLException, IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));


        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Mitreto15@");

        connection = DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, props);

        // 2. Get Villains’ Names

        getVillainsNameAndMinionsCount();

        // 3. Get Minion Names

        getMinionNamesEx();

        // 4. Add Minion

        addMinionEx();

        // 9. Increase Age Stored Procedure

       increaseAgeWithStoredProcedure();


    }

    private static void increaseAgeWithStoredProcedure() throws IOException, SQLException {
        System.out.println("Enter minion id:");
        int minionId = Integer.parseInt(reader.readLine());

        query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection
                .prepareCall(query);

        callableStatement.setInt(1, minionId);
        callableStatement.execute();

    }

    private static void addMinionEx() throws IOException, SQLException {
        System.out.println("Enter minion parameters: ");
        String[] minionParameters = reader.readLine().split("\\s+");
        String minionName = minionParameters[0];
        int minionAge = Integer.parseInt(minionParameters[1]);
        String minionTown = minionParameters[2];

        System.out.println("Enter villain name: ");
        String name = reader.readLine();


        if (!checkIfEntityExistByName(minionTown, "towns")) {
            insertEntityInTown(minionName);
        }

    }

    private static void insertEntityInTown(String minionName) throws SQLException {

        query = "INSERT INTO towns (name, country) values(?, ?)";

        statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        statement.setString(2, "NULL");

        statement.execute();
    }

    private static boolean checkIfEntityExistByName(String entityName, String tableName) throws SQLException {
        query = "SELECT * FROM " + tableName + " WHERE name = ?";

        statement = connection.prepareStatement(query);
        statement.setString(1, entityName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();

    }

    private static void getMinionNamesEx() throws IOException, SQLException {
        System.out.println("Enter villain id:");
        int villain_id = Integer.parseInt(reader.readLine());

        if (!checkIfEntityExist(villain_id, "villains")) {
            System.out.printf("No villain with ID %d exists in the database.", villain_id);
            return;
        }
        System.out.printf("Villain: %s%n", getEntityNameById(villain_id, "villains"));

        getMinionsNameAndAgeByVillainId(villain_id);
    }

    private static void getMinionsNameAndAgeByVillainId(int villain_id) throws SQLException {

        query = "SELECT m.name, m.age FROM minions AS m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);

        ResultSet resultSet = statement.executeQuery();
        int minionNumber = 0;
        while (resultSet.next()) {
            System.out.printf("%d. %s %d%n",
                    ++minionNumber,
                    resultSet.getString("name"),
                    resultSet.getInt("age")
            );
        }
    }

    private static String getEntityNameById(int entityId, String tableName) throws SQLException {
        query = "SELECT name FROM " + tableName + " WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, entityId);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }

    private static boolean checkIfEntityExist(int villain_id, String villains) throws SQLException {
        query = "SELECT * FROM " + villains + " WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNameAndMinionsCount() throws SQLException {

        query = "SELECT v.name, COUNT(mv.minion_id) AS 'count'\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > 15\n" +
                "ORDER BY `count` DESC";

        statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d %n",
                    resultSet.getString("name"),
                    resultSet.getInt(2)
            );
        }
    }
}
