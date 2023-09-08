package database;
import java.sql.Connection;
import  java.sql.DriverManager;


public class db {
    public static Connection connect() throws Exception
    {
        try{
            String jdbcUrl="jdbc:mysql://localhost:3306/gdb";
            String username="root";
            String password="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect=DriverManager.getConnection(jdbcUrl,username,password);
            return connect;
        }catch(Exception e){
            System.out.println("connection failed \n"+e);
        }

        return null;
    }
}
