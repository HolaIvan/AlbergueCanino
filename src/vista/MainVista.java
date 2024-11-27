package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainVista extends JFrame {
    private static MainVista instance;

    public MainVista() {
        instance = this;
        setTitle("Main Vista");
        setSize(750, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Crear el panel de fondo con imagen traslúcida
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fondo = new ImageIcon("src/images/fondo2.jpeg"); // Cambia la ruta a la imagen de fondo
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Opacidad del fondo
                g2d.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        panelFondo.setLayout(new GridBagLayout()); // Usar GridBagLayout en el fondo
        setContentPane(panelFondo); // Usar panelFondo como contenido principal

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Crear cada cuadro con dimensiones y configuraciones independientes
        JPanel cuadro1 = crearCuadroConImagen("src/images/registroCanino.jpg", 200, 200);
        JPanel cuadro2 = crearCuadroConImagen("src/images/inventario.jpg", 200, 200);
        JPanel cuadro3 = crearCuadroConImagen("src/images/donacion.jpg", 200, 200);

        // Crear labels con texto personalizable debajo de cada cuadro
        JLabel label1 = new JLabel("REGISTRO CANINO");
        JLabel label2 = new JLabel("REGISTRO INVENTARIO");
        JLabel label3 = new JLabel("DONACIONES");

        cuadro1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                abrirVentanaCuadro1();
            }
        });

        cuadro2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                abrirVentanaCuadro2();
            }
        });

        cuadro3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                abrirVentanaCuadro3();
            }
        });

        // Añadir cada cuadro y label a una posición específica en el panel de fondo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFondo.add(cuadro1, gbc);
        gbc.gridy = 1;
        panelFondo.add(label1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFondo.add(cuadro2, gbc);
        gbc.gridy = 1;
        panelFondo.add(label2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelFondo.add(cuadro3, gbc);
        gbc.gridy = 1;
        panelFondo.add(label3, gbc);

        setVisible(true);
    }

    private JPanel crearCuadroConImagen(String rutaImagen, int ancho, int alto) {
        JPanel cuadro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon(rutaImagen);
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        cuadro.setBackground(Color.LIGHT_GRAY);
        cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cuadro.setPreferredSize(new Dimension(ancho, alto));
        cuadro.setMinimumSize(new Dimension(ancho, alto));

        return cuadro;
    }

    private void abrirVentanaCuadro1() {
        new VentanaCuadro1(this);
    }
    
    private void abrirVentanaCuadro2() {
        new VentanaCuadro2();
    }
    private void abrirVentanaCuadro3() {
        new VentanaCuadro3();
    }
    public static void main(String[] args) {
        new MainVista();
    }
}
