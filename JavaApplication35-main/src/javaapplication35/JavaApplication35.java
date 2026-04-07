/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication35;
import conexion.Conexion;
import java.util.Scanner;
/**
 *
 * @author Whiteraven
 */
public class JavaApplication35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Conexion con=new Conexion();
        
        System.out.println("Digite su nombre: ");
        String nombre=sc.nextLine();
        
        con.insertarDato(nombre);
    }
    
}
