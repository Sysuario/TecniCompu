/* 
 * 
 * Modulo de conexion a la base de datos
 *   
 *  */

package CAD;

import java.sql.*;

public class ModuloConexion {
    
    public static Connection conexionDB(){
        Connection conexion=null;
        //String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1:3306/dbinfox";
        String user="root"; //usuario
        String pass="";// sin pass
        try {
           // Class.forName(driver);
            conexion = DriverManager.getConnection(url,user,pass);
            return conexion;
        } catch (Exception exe) {
            return null;
        }
    }
}
        
        
    
