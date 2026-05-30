package singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private static final String host = "sql10.freesqldatabase.com";
    private static final String port = "3306";
    private static final String db_name = "sql10828677";
    private static final String user = "sql10828677";
    private static final String password = "xeNnJBxDck";

    private static final String db_connect_string = String.format(
        "jdbc:mysql://%s:%s/%s" + 
        "?verifyServerCertificate=false" +
        "&useSSL=false" +
        "&requireSSL=false" +
        "&useTimezone=true" +
        "&serverTimezone=UTC" +
        "&allowPublicKeyRetrieval=true", host, port, db_name
    );

    private DatabaseConnection(){}

    public static DatabaseConnection getInstance(){
        if(instance==null)
            instance = new DatabaseConnection();
        return instance;
    }

    private Connection conn = null;

    public void connect(){
        System.out.println("EXECUTANDO CONEXAO COM O BANCO DE DADOS...\n");
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(this.conn != null) this.conn.close();
            conn = DriverManager.getConnection(db_connect_string, user, password);
            System.out.println("STATUS DA CONEXAO: " + (conn.isClosed() ? "FALHA" : "CONECTADO") + "\n");
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
