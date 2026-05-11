/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class JuegoModelo {
    private Pajaro pajaro;
    private List<Obstaculo> obstaculos;
    private int puntaje;
    private boolean gameOver;
    private int contadorFrames;

    public JuegoModelo() {
        
        pajaro = new Pajaro(100, 300, 30, 30); 
        obstaculos = new ArrayList<>();
        puntaje = 0;
        gameOver = false;
        contadorFrames = 0;
        generarObstaculo();
    
    }

    public void actualizar() {
            if (gameOver) return;
            pajaro.mover();

            // Si el pájaro toca el piso, pierde
            if (pajaro.getY() >= 550) {
                gameOver = true;
                return; // Salimos para no seguir calculando
            }
            
            contadorFrames++;
            // Cada 60 frames aprox 2 segundos se crea un nuevo par de tubos
        //para que los tubos salgan mas cerca se baja el 60 a 50 o 45
            if (contadorFrames % 60 == 0) { 
            generarObstaculo();
        }

            for (int i = 0; i < obstaculos.size(); i++) {
                Obstaculo obs = obstaculos.get(i);
                obs.mover();

                if (pajaro.colisiona(obs)) { 
                    gameOver = true; 
                }

                // Si el obstáculo sale de la pantalla, sumamos punto
                if (obs.getX() + obs.getAncho() < 0) {
                if (obs.getY() == 0) {
                puntaje++;
                }
                obstaculos.remove(i);
                i--;
                }
            }
    }
    public void generarObstaculo() {
        //  Altura aleatoria para el tubo de arriba (Entre 50 y 250 píxeles)
        int altoArriba = (int) (Math.random() * 200) + 50; 
        
        //  El hueco exacto por donde pasa el pajaro 
        int espacio = 180; 
        
        int yTuboAbajo = altoArriba + espacio;
        

        int altoAbajo = 800 - yTuboAbajo; 

        // Generar tubo de ARRIBA 
        obstaculos.add(new Obstaculo(800, 0, 50, altoArriba));
        // Generar tubo de ARRIBA 
        obstaculos.add(new Obstaculo(800, yTuboAbajo, 50, altoAbajo));
    }
    
    public void reiniciar() {
        this.pajaro = new Pajaro(100, 300, 30, 30); // vuelve a la posición inicial
        this.obstaculos.clear(); // se booran las tuberías con las que chocó
        this.puntaje = 0;
        this.gameOver = false;
        this.contadorFrames = 0;
        generarObstaculo(); // Genera la primera tubería del nuevo intento
    }

    
    public Pajaro getPajaro() { return pajaro; }
    public List<Obstaculo> getObstaculos() { return obstaculos; }
    public int getPuntaje() { return puntaje; }
    public boolean isGameOver() { return gameOver; }
}
