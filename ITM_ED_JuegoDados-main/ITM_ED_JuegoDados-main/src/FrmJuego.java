import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    JLabel lblDado1, lblDado2, lblLanzamientos, lblCenas;
    Dado dado1, dado2;
    int lanzamientos, cenas;
    Random r = new Random();

    public FrmJuego() {
        setSize(600, 300);
        setTitle("Juguemos a los dados");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lblDado1 = new JLabel();
        lblDado2 = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        lblLanzamientos = new JLabel();
        lblCenas = new JLabel();
        JButton btnIniciar = new JButton();
        JButton btnLanzar = new JButton();

        dado1 = new Dado();
        dado2 = new Dado();
        lanzamientos = 0;
        cenas = 0;

        String rutaImagenDado = "/imagenes/6.jpg";
        ImageIcon imgDado = new ImageIcon(getClass().getResource(rutaImagenDado));
        lblDado1.setIcon(imgDado);
        lblDado2.setIcon(imgDado);

        lblDado1.setBounds(10, 10, imgDado.getIconWidth(), imgDado.getIconHeight());
        lblDado2.setBounds(20 + imgDado.getIconWidth(), 10, imgDado.getIconWidth(), imgDado.getIconHeight());

        jLabel1.setText("Lanzamientos");
        jLabel2.setText("Cenas");

        jLabel1.setBounds(50 + 2 * imgDado.getIconWidth(), 10, 100, 25);
        jLabel2.setBounds(160 + 2 * imgDado.getIconWidth(), 10, 100, 25);

        lblLanzamientos.setBounds(50 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblLanzamientos.setFont(new Font("Tahoma", 1, 72));
        lblLanzamientos.setBackground(new Color(0, 0, 0));
        lblLanzamientos.setForeground(new Color(51, 255, 0));
        lblLanzamientos.setOpaque(true);
        lblLanzamientos.setHorizontalAlignment(SwingConstants.CENTER);

        lblCenas.setBounds(160 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblCenas.setFont(new Font("Tahoma", 1, 72));
        lblCenas.setBackground(new Color(0, 0, 0));
        lblCenas.setForeground(new Color(51, 255, 0));
        lblCenas.setOpaque(true);
        lblCenas.setHorizontalAlignment(SwingConstants.CENTER);

        mostrarContadores();

        btnIniciar.setText("Iniciar");
        btnIniciar.setBounds(10, 150, 100, 25);
        btnIniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnIniciar_Click(e);
            }

        });

        btnLanzar.setText("Lanzar");
        btnLanzar.setBounds(120, 150, 100, 25);
        btnLanzar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnLanzar_Click(e);
            }

        });

        getContentPane().setLayout(null);
        getContentPane().add(lblDado1);
        getContentPane().add(lblDado2);
        getContentPane().add(jLabel1);
        getContentPane().add(jLabel2);
        getContentPane().add(lblLanzamientos);
        getContentPane().add(lblCenas);
        getContentPane().add(btnIniciar);
        getContentPane().add(btnLanzar);

    }

    private void mostrarContadores() {
        lblLanzamientos.setText(String.valueOf(lanzamientos));
        lblCenas.setText(String.valueOf(cenas));
    }

    private void btnIniciar_Click(ActionEvent evt) {
        lanzamientos = 0;
        cenas = 0;
        mostrarContadores();
    }

    private void btnLanzar_Click(ActionEvent evt) {
        dado1.lanzar(r);
        dado2.lanzar(r);

        dado1.mostrar(lblDado1);
        dado2.mostrar(lblDado2);

        lanzamientos++;
        if (dado1.getNumero() + dado2.getNumero() >= 11) {
            cenas++;
        }
        mostrarContadores();

    }

}
