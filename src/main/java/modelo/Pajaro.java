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
public class Pajaro extends EntidadJuego {
    
    private int velocidadY;
    private static final int GRAVEDAD = 2;
    private static final int FUERZA_SALTO = -15;

    public Pajaro(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidadY = 0;
    }

    // sobrecarga por si solo mandan x e y
    public Pajaro(int x, int y) {
        this(x, y, 30, 30); // le pongo 30x30 por defecto
    }

    @Override
    public void mover() {
        velocidadY += GRAVEDAD;
        y += velocidadY;
        
        // no dejo que se salga por el techo
        if (y < 0) { 
            y = 0; 
            velocidadY = 0; 
        }
    }

    public void saltar() {
        velocidadY = FUERZA_SALTO;
    }

    @Override //pinta el pajaro al color deseado como un ovalo
    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, ancho, alto);
    }
}
