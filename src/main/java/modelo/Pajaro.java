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

    // Constructor completo
    public Pajaro(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidadY = 0;
    }

    // Polimorfismo estático
    public Pajaro(int x, int y) {
        this(x, y, 30, 30); // Tamaño por defecto
    }

@Override
    public void mover() {
        velocidadY += GRAVEDAD;
        y += velocidadY;
        
        // Si toca el techo, no lo deja salir y lo empuja un poco abajo
        if (y < 0) { 
            y = 0; 
            velocidadY = 0; 
        }
        
        // LIMITAMOS EL PISO
        // serestamos el tamaño del pájaro
        if (y > 550) {
            y = 550;
        }
    }

    public void saltar() {
        velocidadY = FUERZA_SALTO;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, ancho, alto);
    }
}
