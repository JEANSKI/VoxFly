/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.JuegoModelo;
import vista.VistaJuego;
import vista.LienzoJuego;
import javax.swing.Timer;
/**
 *
 * @author User
 */
public class JuegoControlador {
    private JuegoModelo modelo;
    private VistaJuego vista;
    private MicrofonoSensor microfono;
    private Timer timer;
    private static final double UMBRAL_SONIDO = 40.0; // sensibilidad del microfono recom 40 o 60

    public JuegoControlador(JuegoModelo modelo, VistaJuego vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.microfono = new MicrofonoSensor();
        
        ((LienzoJuego) this.vista.panelLienzo).modelo = this.modelo;
        
        this.vista.lblGameOver.setVisible(false); // se esconde el texto de perder al inicio
        this.vista.lblPuntaje.setText("Puntaje: 0");

        timer = new Timer(30, e -> bucleJuego()); // fps del juego en 30
    }

    public void iniciar() {
        microfono.iniciar();
        timer.start();
    }

    private void bucleJuego() {
        double volumen = microfono.obtenerNivelVolumen();
        
        // para ver en consola si me escucha bien el pc
        System.out.println("Nivel de voz detectado: " + volumen);
        
        if (!modelo.isGameOver()) {
            if (volumen > UMBRAL_SONIDO) {
                modelo.getPajaro().saltar();
            }
            modelo.actualizar();
            
            // actualizo el label de los puntos
            vista.lblPuntaje.setText("Puntaje: " + modelo.getPuntaje());
            vista.panelLienzo.repaint(); // repinta los dibujos
            
        } else {
            // si se pierdie muestro el letrero
            vista.lblGameOver.setVisible(true);
            vista.panelLienzo.repaint();
            
            // si hay ruido otra vez, se reinicia todo
            if (volumen > UMBRAL_SONIDO) {
                modelo.reiniciar();
                vista.lblGameOver.setVisible(false); 
            }
        }
    }
}
