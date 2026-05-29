package singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;

    private DatabaseConnection(){}

    public static DatabaseConnection getInstance(){
        if(instance==null)
            instance = new DatabaseConnection();
        return instance;
    }

    private Connection conn = null;

    public void connect(){
        String host = "sql10.freesqldatabase.com";
        String port = "3306";
        String db_name = "sql10828677";
        String user = "sql10828677";
        String password = "xeNnJBxDck";


        String db_connect_string = "jdbc:mysql://" + host + ":" + port + "/" + db_name + 
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useTimezone=true" +
            "&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                if(this.conn != null) this.conn.close();

                conn = DriverManager.getConnection(db_connect_string, user, password);

                if(!conn.isClosed()) 
                    System.out.println("conectado com sucesso.");
                
            } catch(Exception e){
                System.err.println("erro ao conectar:");
                e.printStackTrace();
            }
    }

    public Connection getConnection(){
        if(this.conn == null) connect();
        return this.conn;
    }
}
