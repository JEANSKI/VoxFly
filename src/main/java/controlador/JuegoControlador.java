/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.JuegoModelo;
import vista.VentanaJuego;
import javax.swing.Timer;
/**
 *
 * @author User
 */
public class JuegoControlador {
    
    private JuegoModelo modelo;
    private VentanaJuego vista;
    private MicrofonoSensor microfono;
    private Timer timer;
    // Ajustar este número según la sensibilidad del micrófono de 30 a 50
    private static final double UMBRAL_SONIDO = 40.0; 

    public JuegoControlador(JuegoModelo modelo, VentanaJuego vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.microfono = new MicrofonoSensor();
        
        this.vista.setModelo(modelo);
        // Bucle de juego a 30 milisegundos pasra los 30 fps
        timer = new Timer(30, e -> bucleJuego());
    }

    public void iniciar() {
        microfono.iniciar();
        timer.start();
    }

    private void bucleJuego() {
        if (!modelo.isGameOver()) {
            double volumen = microfono.obtenerNivelVolumen();
            if (volumen > UMBRAL_SONIDO) {
                modelo.getPajaro().saltar();
            }
            modelo.actualizar();
            vista.repaint(); // Llama a la vista para repintarse
        } else {
            timer.stop();
            microfono.detener();
            vista.mostrarGameOver();
        }
    }
}
