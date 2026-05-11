/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import modelo.JuegoModelo;
import vista.VentanaJuego;
import controlador.JuegoControlador;
import javax.swing.JFrame;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("VoxFly - Juego Accesible por Voz");
        VentanaJuego lienzo = new VentanaJuego();
        
        ventana.add(lienzo);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        JuegoModelo modelo = new JuegoModelo();
        JuegoControlador controlador = new JuegoControlador(modelo, lienzo);
        controlador.iniciar();
    }
}
