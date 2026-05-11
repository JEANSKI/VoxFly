/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import modelo.JuegoModelo;
import vista.VistaJuego;
import controlador.JuegoControlador;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        VistaJuego ventana = new VistaJuego();
        ventana.setLocationRelativeTo(null);
        
        JuegoModelo modelo = new JuegoModelo();
        
        JuegoControlador controlador = new JuegoControlador(modelo, ventana);
        
        ventana.setVisible(true);
        controlador.iniciar();
    }
}
