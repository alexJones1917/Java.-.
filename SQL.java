import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    public static Statement statement;
    //public static String[] nameComands = new String[] {"BAL", "CWS", "ANA", "BOS", "CLE", "OAK", "NYY", "DET", "SEA", "TB"};
    public static List<Integer> height;
    public static void connection(Connection connection)
            throws ClassNotFoundException, SQLException{
        statement = connection.createStatement();
    }

    //Метод создает таблицу
    public static void CreateTable()
            throws ClassNotFoundException, SQLException {
        String table = "CREATE TABLE if not exists Indicators (" +
                "'Name' TEXT," +
                "'Team' TEXT," +
                "'Position' TEXT, " +
                "'Height' INT, " +
                "'Weight' INT, " +
                "'Age' REAL) ";
        statement.execute(table);
    }

    //Метод заполняет таблицу
    public static void WriteTable(ArrayList<SportComands> arr, Connection connection) throws SQLException {
        for (SportComands sc : arr){
            String insert = "INSERT INTO Indicators VALUES (?,?,?,?,?,?)";
            PreparedStatement pr = connection.prepareStatement(insert);
            pr.setObject(1, sc.getName());
            pr.setObject(2, sc.getTeam());
            pr.setObject(3, sc.getPosition());
            pr.setObject(4, sc.getHeight());
            pr.setObject(5, sc.getWeight());
            pr.setObject(6, sc.getAge());
            pr.execute();
        }
    }

    //Задание 3
    public static ResultSet mostOlderPlayer(Connection conn) throws java.sql.SQLException {
        List<ResultSet> arr = new ArrayList<>();
        String query = "SELECT team, AVG(Height) AS Средний_рост, AVG(Weight) AS Средний_вес, AVG(Age) AS Средний_возраст FROM 'Indicators' GROUP BY team ORDER BY Средний_возраст DESC LIMIT 1";
        PreparedStatement pr = conn.prepareStatement(query);
        ResultSet rs  = pr.executeQuery();
        return  rs;
    }
    //Задание 2
    public static ResultSet NamesComandMostAVGHeight(Connection connection) throws java.sql.SQLException{
        String query = "SELECT team, AVG(Height) AS Средний_рост FROM 'Indicators' GROUP BY team ORDER BY Средний_рост DESC LIMIT 1";
        PreparedStatement pr = connection.prepareStatement(query);
        ResultSet rs = pr.executeQuery();
        return rs;
    }
    //Задание 2
    public static void mostHighsPlayers(Connection conn) throws java.sql.SQLException {
        String query = "SELECT DISTINCT Name, Height FROM 'Indicators' " +
                "WHERE team = (SELECT team FROM 'Indicators' " +
                "GROUP BY team ORDER BY AVG(Height) DESC LIMIT 1) " +
                "ORDER BY Height DESC, Name ASC LIMIT 5";

        try (PreparedStatement pr = conn.prepareStatement(query);
             ResultSet rs = pr.executeQuery()) {

            int place = 1;
            while(rs.next()) {
                System.out.println(place + ". " + rs.getString("Name") +
                        " - " + rs.getInt("Height") + " inches");
                place++;
            }

            if (place == 1) {
                System.out.println("Не найдено игроков в выбранной команде");
            }
        }
    }
    //Задание 1
    public static List<Double> AvgHeight(Connection conn) throws java.sql.SQLException {
        List<Double> arr = new ArrayList<>();
        String query = "SELECT team, AVG(Age) AS Средний_возраст FROM 'Indicators' GROUP BY team ORDER BY Средний_возраст DESC LIMIT 10";
        PreparedStatement pr = conn.prepareStatement(query);
        ResultSet rs  = pr.executeQuery();
        while(rs.next()){
            arr.add(rs.getDouble(2));
        }
        return arr;
    }
    //Задание 1
    public static List<String> ComandAvgHeight(Connection conn) throws java.sql.SQLException {
        List<String> arr = new ArrayList<>();
        String query = "SELECT team, AVG(Age) AS Средний_возраст FROM 'Indicators' GROUP BY team ORDER BY Средний_возраст DESC LIMIT 10";
        PreparedStatement pr = conn.prepareStatement(query);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            arr.add(rs.getString(1));
        }
        return arr;
    }
}
