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
    
    // para controlar cada cuanto salen los tubos
    private int contadorFrames; 

    public JuegoModelo() {
        pajaro = new Pajaro(100, 300, 30, 30); //posicion incial del  pajaro
        obstaculos = new ArrayList<>();
        puntaje = 0;
        gameOver = false;
        contadorFrames = 0;
        generarObstaculo(); // el primer tubo sale de una
    }

    public void actualizar() {
        if (gameOver) return;
        pajaro.mover();
        
        // si el pajaro toca el piso pierde
        if (pajaro.getY() >= 550) {
            gameOver = true;
            return; 
        }

        contadorFrames++;
        // cada 60 frames sale un par de tubos, si esta muy pegados se puede poner menos de 60
        if (contadorFrames % 60 == 0) {
            generarObstaculo();
        }

        for (int i = 0; i < obstaculos.size(); i++) {
            Obstaculo obs = obstaculos.get(i);
            obs.mover();

            if (pajaro.colisiona(obs)) { 
                gameOver = true; 
            }

            // si el tubo ya paso de largo por la izquierda
            if (obs.getX() + obs.getAncho() < 0) {
                
                // solo se suma 1 punto con el tubo de arriba para que no me de 2 puntos de golpe
                if (obs.getY() == 0) {
                    puntaje++;
                }
                
                obstaculos.remove(i);
                i--; // se acomoda el indice para que no se rompa la lista al borrar
            }
        }
    }

    public void generarObstaculo() {
        int altoArriba = (int) (Math.random() * 200) + 50; 
        int espacio = 180; //espacio entre los tubos se pueden reducir o ampliar a gusto
        int yTuboAbajo = altoArriba + espacio;
        int altoAbajo = 800 - yTuboAbajo; 

        obstaculos.add(new Obstaculo(800, 0, 50, altoArriba));
        obstaculos.add(new Obstaculo(800, yTuboAbajo, 50, altoAbajo));
    }

    public void reiniciar() {
        
        //reinicia todo como al incio
        
        this.pajaro = new Pajaro(100, 300, 30, 30); 
        this.obstaculos.clear(); 
        this.puntaje = 0;
        this.gameOver = false;
        this.contadorFrames = 0; // hace que se reinicien los frames
        generarObstaculo(); 
    }

    public Pajaro getPajaro() { return pajaro; }
    public List<Obstaculo> getObstaculos() { return obstaculos; }
    public int getPuntaje() { return puntaje; }
    public boolean isGameOver() { return gameOver; }
}
