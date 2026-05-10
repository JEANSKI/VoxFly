/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.LineUnavailableException;
/**
 *
 * @author User
 */
public class MicrofonoSensor {
    
    private TargetDataLine microfono;
    private boolean corriendo;
    
    public void iniciar() {
        try {
            AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, true);
            microfono = AudioSystem.getTargetDataLine(format);
            microfono.open(format);
            microfono.start();
            corriendo = true;
        } catch (LineUnavailableException e) {
            System.err.println("Error accediendo al micrófono: " + e.getMessage());
        }
    }

    public double obtenerNivelVolumen() {
        if (!corriendo) return 0;
        byte[] buffer = new byte[1024];
        int bytesLeidos = microfono.read(buffer, 0, buffer.length);
        long suma = 0;
        for (int i = 0; i < bytesLeidos; i++) {
            suma += Math.abs(buffer[i]); // Calcular la amplitud del sonido
        }
        return (bytesLeidos > 0) ? (suma / (double) bytesLeidos) : 0;
    }

    public void detener() {
        corriendo = false;
        if (microfono != null) { microfono.stop(); microfono.close(); }
    }
}
