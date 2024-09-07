import java.util.Random;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Dado {

    private int numero;

    public void lanzar(Random r) {
        // generar mumero aleatorio en 1 y 6
        numero = r.nextInt(6) + 1;
    }

    public void mostrar(JLabel lbl) {
        String rutaImagenDado = "/imagenes/" + numero + ".jpg";
        ImageIcon imgDado = new ImageIcon(getClass().getResource(rutaImagenDado));
        lbl.setIcon(imgDado);
    }

    public int getNumero() {
        return numero;
    }
    
}
