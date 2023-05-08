/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

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
public class ACCESOaDATOSS {
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
        //bloque trycatch para conocer mi IP
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
                             /*no comprendo el drop AQUI bro.*/
//            //elimina si existiera la base de datos probando
//            nr = stmt.executeUpdate("DROP DATABASE PROBANDO;");
//            System.out.println("DROP DATABASE PROBANDO;");
//            comprueba(nr);

            //crea de nuevo la base de datos probando
            nr = stmt.executeUpdate("CREATE DATABASE ejemplo2;");
            System.out.println("CREATE DATABASE Ejemplo2;");
            comprueba(nr);

            //entra en la base de datos recien creada
            ex = stmt.execute("USE Ejemplo2;");
            System.out.println("USE Ejemplo2;");
            comprueba(ex);

            //crea una tabla
            ex = stmt.execute("create table LIBROS(ISBN integer(8) PRIMARY KEY,TITULO varchar(30));");
            System.out.println("create table LIBROS(ISBN integer(8) PRIMARY KEY,TITULO varchar(30));");
            comprueba(ex);

            //insertar elementos en la tabla
            ex = stmt.execute("insert into LIBROS(ISBN, TITULO) value (33058621, 'INFERNO'), (35494238, 'CIEN AÑOS DE SOLEDAD'),(58764384, 'LA CASA DE BERNARDA ALBA'), (38784929, 'AEROPUERTO');");
            System.out.println("insert into LIBROS(ISBN, TITULO) value (33058621, 'INFERNO'), (35494238, 'CIEN AÑOS DE SOLEDAD'),(58764384, 'LA CASA DE BERNARDA ALBA'), (38784929, 'AEROPUERTO');");
            comprueba(ex);

            //muestra todo el contenido de la tabla creada previamente
            ResultSet rs = stmt.executeQuery("SELECT * FROM LIBROS");
            //bucle para mostrar el contenido
            while (rs.next()) {
                int isbn = rs.getInt("ISBN");
                String titulo = rs.getString("TITULO");
                System.out.println("ISBN: " + isbn + " Titulo: " + titulo);
            }

            //cierra la conexion y el statement
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
