import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class Cantor {
    private static String[] encabezados = new String[]{"B", "I", "N", "G", "O"};
    private static int[][] balotas = new int[15][5];
    private static int totalBalotasSacadas;
    private static Random r = new Random();

    public static void iniciar() {
        for (int c = 0; c < 5; c++) {
            for (int f = 0; f < 15; f++) {
                balotas[f][c] = 0;
            }
        }
        totalBalotasSacadas = 0;
    }

    public static int sacarBalota() {
        int numero = 0;
        if (totalBalotasSacadas < 75) {
            boolean sacada = false;
            while (!sacada) {
                numero = r.nextInt(75) + 1;
                int f = numero % 15;
                if (f == 0) {
                    f = 15;
                }
                int c = (numero - f) / 15;
                if (balotas[f - 1][c] == 0) {
                    sacada = true;
                    balotas[f - 1][c] = numero;
                    totalBalotasSacadas++;
                }
            }
        }
        return numero;
    }

    public static void mostrarBalotas(JTable tbl) {
        int f = balotas.length;
        int c = balotas[0].length;
        String[][] m = new String[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (balotas[i][j] == 0) {
                    m[i][j] = "";
                } else {
                    m[i][j] = String.valueOf(balotas[i][j]);
                }
            }
        }
        // Configuramos el DefaultTableModel para incluir encabezados
        DefaultTableModel model = new DefaultTableModel(m, encabezados);
        tbl.setModel(model);
    }
    

    public static boolean verificarSacada(int numero) {
        if (totalBalotasSacadas > 0) {
            int f = numero % 15;
            if (f == 0) {
                f = 15;
            }
            int c = (numero - f) / 15;
            return balotas[f - 1][c] != 0;
        }
        return false;
    }

    public static String[] obtenerEncabezados() {
        return encabezados;
    }

    public static int[][] obtenerBalotas() {
        return balotas;
    }

    public static int obtenerTotalBalotasSacadas() {
        return totalBalotasSacadas;
    }
}
