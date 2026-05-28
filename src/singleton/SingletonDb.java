package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SingletonDb {
    private static SingletonDb instance = null;

    private SingletonDb(){}

    public static SingletonDb getInstance(){
        if(instance==null)
            instance = new SingletonDb();
        return instance;
    }

    private Connection conn = null;
    private Statement stmt = null;

    public void connect(){
        String host = "blazxqxusxjalx8ltidr-mysql.services.clever-cloud.com";
        String port = "3306";
        String db_name = "blazxqxusxjalx8ltidr";
        String user = "udu2mgxqn6ibug9a";
        String password = "mrHgEaMGw9NzaDEadchn";


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

                if(!conn.isClosed()) {
                    stmt = conn.createStatement();
                    System.out.println("conectado com sucesso.");
                }
            } catch(Exception e){
                System.err.println("erro ao conectar:");
                e.printStackTrace();
            }
    }

    public Statement getStatement(){
        if(conn==null) connect();
        return stmt;
    }

    public static void main(String[] args){
        SingletonDb.getInstance().connect();
    }
}
