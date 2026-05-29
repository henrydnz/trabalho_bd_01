package util;
import singleton.DatabaseConnection;

public class mysql_connection_test {
    public static void main(String[] args) throws Exception {
        System.out.println("testando conexao com a database");

        DatabaseConnection bd = DatabaseConnection.getInstance();

        bd.connect();
    }
}
