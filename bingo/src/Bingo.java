import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bingo extends JFrame {
    private Tabla[] tablas;
    private JTextField txtTotalTablas;
    private JButton btnIniciar, btnSacarBalota;
    private JComboBox<String> cmbTablas;
    private JTable tblBingo, tblTabla;

    public Bingo() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Juego de Bingo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 700);

        // Fondo de la ventana principal
        getContentPane().setBackground(new Color(44, 62, 80));  // Color oscuro bonito

        // Etiqueta para "Total Tablas"
        JLabel jLabel1 = new JLabel("Total Tablas:");
        jLabel1.setBounds(30, 30, 150, 25);
        jLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel1.setForeground(Color.WHITE); // Color de texto blanco
        add(jLabel1);

        // Campo de texto para ingresar el número total de tablas
        txtTotalTablas = new JTextField();
        txtTotalTablas.setBounds(180, 30, 50, 30);
        txtTotalTablas.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTotalTablas.setBackground(Color.WHITE);
        txtTotalTablas.setBorder(new LineBorder(Color.WHITE, 2)); // Borde blanco
        add(txtTotalTablas);

        // Botón "Iniciar" con estilo
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(250, 30, 150, 40);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(46, 204, 113)); // Verde llamativo
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBorder(new LineBorder(Color.WHITE, 2));
        add(btnIniciar);

        // Botón "Sacar Balota"
        btnSacarBalota = new JButton("Sacar Balota");
        btnSacarBalota.setBounds(420, 30, 150, 40);
        btnSacarBalota.setFont(new Font("Arial", Font.BOLD, 16));
        btnSacarBalota.setBackground(new Color(52, 152, 219)); // Azul llamativo
        btnSacarBalota.setForeground(Color.WHITE);
        btnSacarBalota.setBorder(new LineBorder(Color.WHITE, 2));
        btnSacarBalota.setEnabled(false);
        add(btnSacarBalota);

        // ComboBox para seleccionar las tablas
        cmbTablas = new JComboBox<>();
        cmbTablas.setBounds(590, 30, 200, 40);
        cmbTablas.setFont(new Font("Arial", Font.PLAIN, 16));
        cmbTablas.setBackground(Color.WHITE);
        cmbTablas.setBorder(new LineBorder(Color.WHITE, 2));
        add(cmbTablas);

        // JTable para mostrar las balotas sacadas, dentro de un JScrollPane
        tblBingo = new JTable();
        JScrollPane scrollPaneBingo = new JScrollPane(tblBingo);
        scrollPaneBingo.setBounds(30, 100, 400, 500);
        scrollPaneBingo.setBorder(new LineBorder(Color.WHITE, 3)); // Borde blanco
        add(scrollPaneBingo);

        // JTable para mostrar la tabla seleccionada del jugador, dentro de un JScrollPane
        tblTabla = new JTable();
        JScrollPane scrollPaneTabla = new JScrollPane(tblTabla);
        scrollPaneTabla.setBounds(450, 100, 400, 500);
        scrollPaneTabla.setBorder(new LineBorder(Color.WHITE, 3)); // Borde blanco
        add(scrollPaneTabla);

        // Estilo de las tablas
        personalizarTablas(tblBingo);
        personalizarTablas(tblTabla);

        // Eventos
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnSacarBalota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnSacarBalotaActionPerformed(evt);
            }
        });

        cmbTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cmbTablasActionPerformed(evt);
            }
        });

        setVisible(true);
    }

    // Método para personalizar las tablas con colores y estilos
    private void personalizarTablas(JTable table) {
        table.setFont(new Font("Arial", Font.BOLD, 18)); // Fuente más grande y en negrita
        table.setRowHeight(50); // Aumentar el alto de las filas
        table.setGridColor(Color.WHITE); // Color de las líneas entre celdas
        table.setBackground(new Color(236, 240, 241)); // Fondo gris claro
        table.setForeground(new Color(44, 62, 80)); // Texto en gris oscuro

        // Renderizador de celdas para centrar el texto
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private void btnIniciarActionPerformed(ActionEvent evt) {
        int totalTablas = 2;
        try {
            totalTablas = Integer.parseInt(txtTotalTablas.getText());
        } catch (Exception ex) {
            txtTotalTablas.setText("2");
        }
        tablas = new Tabla[totalTablas];
        cmbTablas.removeAllItems();
        for (int i = 0; i < totalTablas; i++) {
            tablas[i] = new Tabla(i);
            cmbTablas.addItem("Tabla " + (i + 1));
        }
        Cantor.iniciar();
        Cantor.mostrarBalotas(tblBingo);
        btnSacarBalota.setEnabled(true);
    }

    private void cmbTablasActionPerformed(ActionEvent evt) {
        if (cmbTablas.getSelectedIndex() >= 0) {
            tablas[cmbTablas.getSelectedIndex()].mostrar(tblTabla);
        }
    }

    private void btnSacarBalotaActionPerformed(ActionEvent evt) {
        if (Cantor.sacarBalota() > 0) {
            Cantor.mostrarBalotas(tblBingo);
            if (cmbTablas.getSelectedIndex() >= 0) {
                tablas[cmbTablas.getSelectedIndex()].mostrar(tblTabla);
            }

            for (int i = 0; i < cmbTablas.getItemCount(); i++) {
                if (tablas[i].verificarBingo()) {
                    JOptionPane.showMessageDialog(new JFrame("Verificando Bingo"), "BINGO para el jugador " + (i + 1));
                } else if (tablas[i].verificarBinguito()) {
                    JOptionPane.showMessageDialog(new JFrame("Verificando Binguito"), "BINGUITO para el jugador " + (i + 1));
                }
            }

            if (Cantor.obtenerTotalBalotasSacadas() == 75) {
                btnSacarBalota.setEnabled(false);
                JOptionPane.showMessageDialog(new JFrame(), "El juego termina porque no hay más balotas para sacar");
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "El juego había terminado");
        }
    }

    public static void main(String[] args) {
        new Bingo();
    }
}