import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmJuego extends JFrame {

    private Jugador[] jugadores = new Jugador[2];
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JButton btnRepartir;
    private JButton btnVerificar;

    public FrmJuego() {
        initComponents();

        for (int i = 0; i < jugadores.length; i++)
            jugadores[i] = new Jugador();
    }

    private void initComponents() {
        // Definir colores estilo casino
        Color verdeCasino = new Color(39, 119, 20);  // Verde tapete casino
        Color dorado = new Color(218, 165, 32);      // Dorado para detalles
        Color botonFondo = new Color(60, 60, 60);    // Fondo oscuro para botones

        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();
        btnRepartir = new JButton("Repartir");
        btnVerificar = new JButton("Verificar");

        // Envolvemos los paneles de los jugadores en JScrollPane
        scrollPane1 = new JScrollPane(pnlJugador1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2 = new JScrollPane(pnlJugador2, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Configuración de la ventana
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego de Cartas - Casino");
        getContentPane().setBackground(verdeCasino);  // Fondo verde estilo casino
        getContentPane().setLayout(new GridBagLayout());  // Usamos GridBagLayout para más flexibilidad
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado entre componentes

        // Paneles de los jugadores
        pnlJugador1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(dorado, 3), "Jugador 1",
                0, 0, new Font("Serif", Font.BOLD, 16), Color.WHITE));
        pnlJugador1.setBackground(verdeCasino);
        pnlJugador1.setLayout(new BoxLayout(pnlJugador1, BoxLayout.X_AXIS));
        scrollPane1.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(scrollPane1, gbc);

        pnlJugador2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(dorado, 3), "Jugador 2",
                0, 0, new Font("Serif", Font.BOLD, 16), Color.WHITE));
        pnlJugador2.setBackground(verdeCasino);
        pnlJugador2.setLayout(new BoxLayout(pnlJugador2, BoxLayout.X_AXIS));
        scrollPane2.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 0;
        gbc.gridy = 1;
        getContentPane().add(scrollPane2, gbc);

        // Botón Repartir
        btnRepartir.setBackground(botonFondo);  // Fondo oscuro para botones
        btnRepartir.setForeground(dorado);  // Texto dorado
        btnRepartir.setFont(new Font("Serif", Font.BOLD, 14));  // Cambiar la fuente
        btnRepartir.setFocusPainted(false);
        btnRepartir.setBorder(BorderFactory.createLineBorder(dorado, 2));
        btnRepartir.setPreferredSize(new Dimension(120, 40));
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirActionPerformed(evt);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        getContentPane().add(btnRepartir, gbc);

        // Botón Verificar
        btnVerificar.setBackground(botonFondo);  // Fondo oscuro para botones
        btnVerificar.setForeground(dorado);  // Texto dorado
        btnVerificar.setFont(new Font("Serif", Font.BOLD, 14));  // Cambiar la fuente
        btnVerificar.setFocusPainted(false);
        btnVerificar.setBorder(BorderFactory.createLineBorder(dorado, 2));
        btnVerificar.setPreferredSize(new Dimension(120, 40));
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        getContentPane().add(btnVerificar, gbc);

        // Ajustar tamaño de la ventana
        setSize(600, 400);
        setVisible(true);
    }

    private void btnRepartirActionPerformed(ActionEvent evt) {
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].repartir();
        }

        jugadores[0].mostrar(pnlJugador1, false);
        jugadores[1].mostrar(pnlJugador2, false);

        pnlJugador1.revalidate();
        pnlJugador1.repaint();
        scrollPane1.revalidate();
        scrollPane1.repaint();

        pnlJugador2.revalidate();
        pnlJugador2.repaint();
        scrollPane2.revalidate();
        scrollPane2.repaint();
    }

    private void btnVerificarActionPerformed(ActionEvent evt) {
        int jugadorIndex = JOptionPane.showOptionDialog(this, "¿De qué jugador deseas verificar las figuras?",
                "Seleccionar Jugador", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Jugador 1", "Jugador 2"}, "Jugador 1");

        if (jugadorIndex != -1) {
            Jugador jugador = jugadores[jugadorIndex];
            String figuras = jugador.obtenerFiguras();
            String escaleras = jugador.obtenerEscaleras();
            int puntaje = jugador.calcularPuntaje();
            JOptionPane.showMessageDialog(this, figuras + "\n" + escaleras + "\nPuntaje: " + puntaje);
        }
    }

    public static void main(String[] args) {
        new FrmJuego();
    }
}
