package db_utility;

/**
 *
 * @author Ben Garrison
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class db_connector 
{
    
    private static Connection conn;
    
    public db_connector(){}
       
    private static final String url = ""; //database disabled - see schema
    private static final String user = ""; //database disabled - see schema
    private static final String pass = ""; //database disabled - see schema
            
    public static void init()
    {
        System.out.println("Connecting to the database");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException ce)
        {
            System.out.println("Cannot find the right class.  Did you remember to add the mysql library to your Run Configuration?");
            ce.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //Returns Connection
    public static Connection getConn(){
    
        System.out.println("Connected to the database");
        return conn;                        
    }
    
    //Closes connections
    public static void closeConn(){
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("Connection closed.");
        }
    }
    
}    
    