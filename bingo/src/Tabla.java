import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabla {
    private Random r;
    private int[][] tabla;

    public Tabla(int numero) {
        r = new Random(System.currentTimeMillis() * numero);
        tabla = new int[5][5];
        for (int c = 0; c < 5; c++) {
            for (int f = 0; f < 5; f++) {
                tabla[f][c] = 0;
                if (f != 2 || c != 2) {
                    while (tabla[f][c] == 0) {
                        numero = (c * 15) + r.nextInt(14) + 1;
                        if (!verificarNumero(numero, c)) {
                            tabla[f][c] = numero;
                        }
                    }
                }
            }
        }
    }

    private boolean verificarNumero(int numero, int columna) {
        for (int f = 0; f < 5; f++) {
            if (tabla[f][columna] == numero) {
                return true;
            }
        }
        return false;
    }

    public int[][] obtenerTabla() {
        return tabla;
    }

    public void mostrar(JTable tbl) {
        int f = tabla.length;
        int c = tabla[0].length;
        String[][] m = new String[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (i != 2 || j != 2) {
                    m[i][j] = String.valueOf(tabla[i][j]);
                    if (Cantor.verificarSacada(tabla[i][j])) {
                        m[i][j] += "*";
                    }
                } else {
                    m[i][j] = ""; // Para el espacio vacío en el centro de la tabla
                }
            }
        }
        // Configuramos el DefaultTableModel para incluir encabezados
        DefaultTableModel model = new DefaultTableModel(m, Cantor.obtenerEncabezados());
        tbl.setModel(model);
    }
    
    public boolean verificarBingo() {
        for (int ft = 0; ft < 5; ft++) {
            for (int c = 0; c < 5; c++) {
                if (ft != 2 || c != 2) {
                    boolean bingo = false;
                    for (int fb = 0; fb < 15 && !bingo; fb++) {
                        if (tabla[ft][c] == Cantor.obtenerBalotas()[fb][c]) {
                            bingo = true;
                        }
                    }
                    if (!bingo) return false;
                }
            }
        }
        return true;
    }

    public boolean verificarBinguito() {
        boolean binguito = verificarFila() || verificarColumna();
        return binguito;
    }

    private boolean verificarFila() {
        for (int ft = 0; ft < 5; ft++) {
            boolean binguito = true;
            for (int c = 0; c < 5; c++) {
                if (ft != 2 || c != 2) {
                    binguito = false;
                    for (int fb = 0; fb < 15 && !binguito; fb++) {
                        if (tabla[ft][c] == Cantor.obtenerBalotas()[fb][c]) {
                            binguito = true;
                        }
                    }
                    if (!binguito) return false;
                }
            }
        }
        return true;
    }

    private boolean verificarColumna() {
        for (int c = 0; c < 5; c++) {
            boolean binguito = true;
            for (int ft = 0; ft < 5; ft++) {
                if (ft != 2 || c != 2) {
                    binguito = false;
                    for (int fb = 0; fb < 15 && !binguito; fb++) {
                        if (tabla[ft][c] == Cantor.obtenerBalotas()[fb][c]) {
                            binguito = true;
                        }
                    }
                    if (!binguito) return false;
                }
            }
        }
        return true;
    }
}
