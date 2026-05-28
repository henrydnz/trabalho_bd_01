import singleton.SingletonDb;

public class mysql_connection_test {
    public static void main(String[] args) throws Exception {
        System.out.println("testando conexao com a database");

        SingletonDb bd = SingletonDb.getInstance();

        bd.connect();
    }
}
