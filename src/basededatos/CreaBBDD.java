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
public class CreaBBDD {

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
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("La dirección IP de tu computadora es: " + direccion.getHostAddress());
        } catch (UnknownHostException ex) {
            System.out.println("No se pudo determinar la dirección IP de tu computadora.");
        }

        System.out.println();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://10.230.109.243:3306?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();

            int nr;
            boolean ex = true;

            //crea de nuevo la base de datos probando
            nr = stmt.executeUpdate("CREATE DATABASE ejemploIP;");
            System.out.println("CREATE DATABASE EjemploIP;");
            comprueba(nr);

            
            //cierra la conexion y el statement
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
