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
    private static final double UMBRAL_SONIDO = 60.0; //Sensibilidad del microfono recom. 40-60

    public JuegoControlador(JuegoModelo modelo, VistaJuego vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.microfono = new MicrofonoSensor();
        
        ((LienzoJuego) this.vista.panelLienzo).modelo = this.modelo;
        
        this.vista.lblGameOver.setVisible(false); // Ocultar mensaje al inicio
        this.vista.lblPuntaje.setText("Puntaje: 0");

        timer = new Timer(30, e -> bucleJuego()); //30 se refiere a los fps
    }

    public void iniciar() {
        microfono.iniciar();
        timer.start();
    }

    private void bucleJuego() {
        double volumen = Math.random() * 100; //se puede reemplazar Math.random() * 100; para probarlo
        System.out.println("Nivel de voz detectado: " + volumen);
        
        if (!modelo.isGameOver()) {
            // --- LÓGICA MIENTRAS ESTÁ JUGANDO ---
            if (volumen > UMBRAL_SONIDO) {
                modelo.getPajaro().saltar();
            }
            modelo.actualizar();
            
            // Actualizamos la vista
            vista.lblPuntaje.setText("Puntaje: " + modelo.getPuntaje());
            vista.panelLienzo.repaint(); 
            
        } else {
            // --- LOGICA CUANDO PIERDE ---
            vista.lblGameOver.setVisible(true); // Muestra el letrero
            vista.panelLienzo.repaint(); // Dibuja dónde chocó
            
            // Si el jugador vuelve a hablar fuerte, se reinicia
            if (volumen > UMBRAL_SONIDO) {
                modelo.reiniciar(); // Llama al método que se creo al final de juegomodelo
                vista.lblGameOver.setVisible(false); // Oculta el letrero de perder
            }
        }
    }
}
