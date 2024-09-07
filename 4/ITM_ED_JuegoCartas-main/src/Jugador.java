import java.util.*;
import javax.swing.*;

public class Jugador {

    private Random r;
    private Carta[] cartas;

    public Jugador() {
        r = new Random();
    }

    public void repartir() {
        cartas = new Carta[10];
        for (int i = 0; i < 10; i++)
            cartas[i] = new Carta(r);
    }

    public void mostrar(JPanel pnl, boolean tapada) {
        pnl.removeAll();
        for (int i = 0; i < 10; i++)
            cartas[i].mostrarCarta(5 + i * 40, 5, pnl, tapada);
        pnl.repaint();
    }

    public String obtenerFiguras() {
        int[] contadores = new int[13];
        for (int i = 0; i < 10; i++)
            contadores[cartas[i].obtenerNombre().ordinal() - 1]++;

        String mensaje = "Figuras encontradas:\n";
        for (int i = 0; i < 13; i++) {
            if (contadores[i] == 2) mensaje += "Par de " + NombreCarta.values()[i + 1] + "\n";
            if (contadores[i] == 3) mensaje += "Tercia de " + NombreCarta.values()[i + 1] + "\n";
            if (contadores[i] == 4) mensaje += "Cuarta de " + NombreCarta.values()[i + 1] + "\n";
        }
        return mensaje.equals("Figuras encontradas:\n") ? "No hay figuras" : mensaje;
    }

    public String obtenerEscaleras() {
        Map<PintaCarta, List<Integer>> secuencias = new HashMap<>();
        for (Carta carta : cartas) {
            PintaCarta pinta = carta.obtenerPinta();
            secuencias.putIfAbsent(pinta, new ArrayList<>());
            secuencias.get(pinta).add(carta.obtenerValor());
        }

        String mensaje = "Escaleras encontradas:\n";
        for (PintaCarta pinta : secuencias.keySet()) {
            List<Integer> valores = secuencias.get(pinta);
            Collections.sort(valores);
            if (esEscalera(valores)) {
                mensaje += "Escalera de " + pinta + ": " + valores + "\n";
            }
        }
        return mensaje.equals("Escaleras encontradas:\n") ? "No hay escaleras" : mensaje;
    }

    private boolean esEscalera(List<Integer> valores) {
        if (valores.size() < 3) return false;
        for (int i = 0; i < valores.size() - 1; i++) {
            if (valores.get(i) + 1 != valores.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        for (Carta carta : cartas) {
            puntaje += carta.obtenerValor();
        }
        return puntaje;
    }
}
