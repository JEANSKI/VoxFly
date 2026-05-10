/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author User
 */
public abstract class EntidadJuego implements Colisionable {
    
    protected int x, y, ancho, alto;

    public EntidadJuego(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public abstract void mover();
    public abstract void dibujar(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    @Override
    public boolean colisiona(EntidadJuego otra) {
        return this.getBounds().intersects(otra.getBounds());
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
}
