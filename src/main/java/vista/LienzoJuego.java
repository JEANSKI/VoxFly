/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import modelo.EntidadJuego;
import modelo.JuegoModelo;
/**
 *
 * @author User
 */
public class LienzoJuego extends JPanel {
    // Al ser público, el controlador puede asignarle el modelo
    public JuegoModelo modelo; 

    public LienzoJuego() {
        setBackground(new Color(135, 206, 235)); // Color cielo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (modelo == null) return;

        // Dibuja el pájaro y los obstáculos
        modelo.getPajaro().dibujar(g);
        for (EntidadJuego obs : modelo.getObstaculos()) {
            obs.dibujar(g);
        }
    }
}
