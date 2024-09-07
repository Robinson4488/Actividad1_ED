import java.util.*;
import javax.swing.*;

public class Carta {

    private int indice;

    public Carta(Random r) {
        // El número de la carta es generado aleatoriamente
        indice = r.nextInt(52) + 1;
    }

    public PintaCarta obtenerPinta() {
        if (indice <= 13)
            return PintaCarta.TREBOL;
        else if (indice <= 26)
            return PintaCarta.PICA;
        else if (indice <= 39)
            return PintaCarta.CORAZON;
        else
            return PintaCarta.DIAMANTE;
    }

    public NombreCarta obtenerNombre() {
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero];
    }

    public int obtenerValor() {
        int numero = indice % 13;
        if (numero == 0 || numero >= 10) {
            return 10;
        } else {
            return numero;
        }
    }

    public void mostrarCarta(int x, int y, JPanel pnl, boolean tapada) {
        String nombreImagen;
        // Verifica la existencia de la imagen
        if (tapada) {
            nombreImagen = "/imagenes/TAPADA.jpg";
        } else {
            nombreImagen = "/imagenes/CARTA" + String.valueOf(indice) + ".jpg";
        }

        // Comprueba si la imagen existe
        java.net.URL imgURL = getClass().getResource(nombreImagen);
        if (imgURL != null) {
            ImageIcon imagen = new ImageIcon(imgURL);
            JLabel lblCarta = new JLabel(imagen);
            lblCarta.setBounds(x, y, x + imagen.getIconWidth(), y + imagen.getIconHeight());
            pnl.add(lblCarta);
        } else {
            System.err.println("No se pudo encontrar el archivo: " + nombreImagen);
        }
    }
}
