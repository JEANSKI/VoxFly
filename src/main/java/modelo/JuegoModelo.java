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

    public JuegoModelo() {
        pajaro = new Pajaro(100, 300);
        obstaculos = new ArrayList<>();
        puntaje = 0;
        gameOver = false;
        generarObstaculo();
    }

    public void actualizar() {
        if (gameOver) return;
        pajaro.mover();

        for (int i = 0; i < obstaculos.size(); i++) {
            Obstaculo obs = obstaculos.get(i);
            obs.mover();

            if (pajaro.colisiona(obs)) { gameOver = true; }

            // Si el obstáculo sale de la pantalla, sumamos punto
            if (obs.getX() + obs.getAncho() < 0) {
                obstaculos.remove(i);
                puntaje++;
                generarObstaculo();
                i--;
            }
        }
    }

    public void generarObstaculo() {
        int altoArriba = (int) (Math.random() * 300) + 50;
        int espacio = 150; // Hueco por donde pasa el pájaro
        obstaculos.add(new Obstaculo(800, 0, 50, altoArriba));
        obstaculos.add(new Obstaculo(800, altoArriba + espacio, 50, 600 - (altoArriba + espacio)));
    }

    public Pajaro getPajaro() { return pajaro; }
    public List<Obstaculo> getObstaculos() { return obstaculos; }
    public int getPuntaje() { return puntaje; }
    public boolean isGameOver() { return gameOver; }
}
