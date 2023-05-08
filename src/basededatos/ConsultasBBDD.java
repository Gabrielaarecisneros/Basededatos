package basededatos;

import static basededatos.ACCESOaDATOSS.comprueba;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DAW
 */
public class ConsultasBBDD {

    public void guardar() {

    }

    public static void main(String[] args) {
//        try {            //Te devuelve tu ip
//            InetAddress direccion = InetAddress.getLocalHost();
//            System.out.println("La dirección IP de tu computadora es: " + direccion.getHostAddress());
//        } catch (UnknownHostException ex) {
//            System.out.println("No se pudo determinar la dirección IP de tu computadora.");
//        }

        System.out.println();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://10.230.109.243:3306?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            Scanner cs = new Scanner(System.in);
            int nr;
            boolean ex = true;

            String ruta = "Resultados";
            File resultado = new File(ruta);
            if (!resultado.exists()) {
                resultado.mkdir();
            }
            System.out.println("mete el name.");
            String nombre = cs.nextLine();
            FileWriter writer = new FileWriter(ruta + File.separator + nombre + ".txt", false);
             
            
            
            //entra en la base de datos recien creada
            ex = stmt.execute("USE nba;");
            System.out.println("USE nba;");
            comprueba(ex);

            //muestra todo el contenido de la tabla creada previamente
            ResultSet rs = stmt.executeQuery("select * from jugadores limit 25;");
            //bucle para mostrar el contenido
            while (rs.next()) {

//                int isbn = rs.getInt("ISBN");
//                String titulo = rs.getString("TITULO");
//                int año = rs.getInt("AÑO");
//                System.out.println("ISBN: " + isbn + " Titulo: " + titulo + "AÑO" + año);
//
//                int codigo = rs.getInt("codigo");
//                String nombre = rs.getString("Nombre");
//                String procedencia = rs.getNString("Procedencia");
//                String altura = rs.getString("Altura");
//                int peso = rs.getInt("Peso");
//                String posicion = rs.getString("Posicion");
//                String nombre_equipo = rs.getString("Nombre_equipo");
                System.out.println(rs.getInt("codigo") + rs.getString("Nombre") + rs.getString("Procedencia") + rs.getString("Altura") + rs.getInt("Peso") + rs.getString("Posicion") + rs.getString("Nombre_equipo"));
             writer.write(rs.getInt("codigo") + rs.getString("Nombre") + rs.getString("Procedencia") + rs.getString("Altura") + rs.getInt("Peso") + rs.getString("Posicion") + rs.getString("Nombre_equipo")+"\n");

//                System.out.println("codigo : " + codigo + "Nombre : " + nombre + "Procedencia : " + "Altura : " + altura + " Peso :" + peso + "Posicion " + posicion + " nombre_equipo " + nombre_equipo);
//                String nombre = rs.getString("Nombre");
//                String ciudad = rs.getNString("Ciudad");
//                String conferencia = rs.getNString("Conferencia");
//                String division = rs.getNString("Division");
//                System.out.println("Nombre : " + nombre + "Ciudad : " + ciudad + "Conferencia : " + conferencia + " Division : " + division);
            }
            //hay que cerrar el write
            //cierra la conexion y el statement
            
            writer.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

}
