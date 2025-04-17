import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<SportComands> comands;
    //Метод, который парсит файл
    public static void main(String[] args) {
        comands = new ArrayList<>();
        try (BufferedReader bufferedReaderr = new BufferedReader(new FileReader("Показатели спортивных команд.csv"))) {
            String str = bufferedReaderr.readLine();
            while ((str = bufferedReaderr.readLine()) != null) {
                String[] column = str.split(",");
                SportComands aa = new SportComands(column[0], column[1], column[2], Integer.parseInt(column[3]),
                        Integer.parseInt(column[4]), Double.parseDouble(column[5]));
                comands.add(aa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.sqlite.JDBC");
            //Соединяемся с базой данных
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sport.db");
            ResultSet avg;
            if (connection != null)
            {
                SQL.connection(connection);
                SQL.CreateTable();
                SQL.WriteTable(comands, connection);
                System.out.println();

                //1 задание, построение графика
                List<Double> avgHeight = SQL.AvgHeight(connection);
                List<String> comands = SQL.ComandAvgHeight(connection);
                Chart chart = new Chart("График",  comands, avgHeight);
                chart.pack( );
                chart.setSize(1000,1000);
                chart.setVisible( true );

                //2 задание
                System.out.println("Задание 2");
                ResultSet ss = SQL.NamesComandMostAVGHeight(connection);
                System.out.println("Самый большой рост - " + ss.getDouble(2));
                System.out.println("Самый большой рост в команде - " + ss.getString(1));
                System.out.println("5 самых высоких игроков из команды: ");
                System.out.println();
                SQL.mostHighsPlayers(connection);
                System.out.println();

                //3 задание
                System.out.println("Задание 3");
                avg = SQL.mostOlderPlayer(connection);
                if (avg.getDouble(2) >= 74 && avg.getDouble(2) <= 78
                        && avg.getDouble(3) >= 190 && avg.getDouble(3) <= 210);{
                    System.out.println("Команда " + avg.getString(1) + " с средним ростом равным от 74 до 78 inches и средним весом от 190 до 210 lbs, с самым высоким средним возрастом: " + avg.getDouble(4));
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка");
        }
    }
}