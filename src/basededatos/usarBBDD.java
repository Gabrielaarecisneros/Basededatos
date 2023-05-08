/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static basededatos.ACCESOaDATOSS.comprueba;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DAW
 */
public class usarBBDD {

    public static void comprueba(int resultado) throws Exception {
        if (resultado == 1) {
            System.out.println("Hecho");
        } else {
            throw new Exception("Error en el proceso");
        }
    }

    public static void comprueba(boolean resultado) throws Exception {
        if (!resultado) {
            System.out.println("Hecho");
        } else {
            throw new Exception("Error en el proceso");
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://10.230.109.254:3306?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();

            int nr;
            boolean ex = true;

            //entra en la base de datos recien creada
            ex = stmt.execute("USE :NOMBRE DE LA BBDD:");
            System.out.println("USE :NOMBRE DE LA BBDD;");
            comprueba(ex);

            
            
            //cierra la conexion y el statement
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
