/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author User
 */
public class Obstaculo extends EntidadJuego {
    private int velocidadX = -5;

    public Obstaculo(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
    }

    @Override
    public void mover() {
        x += velocidadX;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }
}
