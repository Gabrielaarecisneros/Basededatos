/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import static basededatos.ACCESOaDATOSS.comprueba;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DAW
 */
public class insertarBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("La dirección IP de tu computadora es: " + direccion.getHostAddress());
        } catch (UnknownHostException ex) {
            System.out.println("No se pudo determinar la dirección IP de tu computadora.");
        }

        System.out.println();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://10.230.109.254:3306?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();

            int nr;
            boolean ex = true;

            //entra en la base de datos recien creada
            ex = stmt.execute("USE ejemplo4;");
            System.out.println("USE ejemplo4;");
            comprueba(ex);

            //insertar elementos en la tabla
            ex = stmt.execute(""
                    + "insert into comics(ISBN,TITULO,AÑO)"
                    + " value (33058621, 'nose1',1900), "
                    + "(35494238, 'nose2',1999),"
                    + "(58764384, 'nose3',1995), "
                    + "(38784929, 'nose4',1996);");
            
            System.out.println(""
                    + "insert into comics(ISBN, TITULO,AÑO)"
                    + " value (33058621, 'nose1',1900), "
                    + "(35494238, 'nose2',1999),"
                    + "(58764384, 'nose3',1995), "
                    + "(38784929, 'nose4',1996)");
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
