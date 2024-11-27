package vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton botonLogin;

    public LoginView() {
        // Configuración de la ventana
        setTitle("Login - Albergue Canino");
        setSize(750, 700); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel de fondo con imagen
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("src/images/fondo.jpg"); // Reemplaza con la ruta de tu imagen
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new GridBagLayout()); // Para centrar el panel blanco sobre la imagen

        // Panel blanco para el formulario
        JPanel panelRectangulo = new JPanel();
        panelRectangulo.setLayout(new GridBagLayout());
        panelRectangulo.setBackground(Color.WHITE);
        panelRectangulo.setPreferredSize(new Dimension(400, 200)); // Ajustable

        // Panel del formulario de login
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false); // Transparente para ver fondo blanco

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Etiqueta y campo de usuario
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelFormulario.add(etiquetaUsuario, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelFormulario.add(campoUsuario, gbc);

        // Etiqueta y campo de contraseña
        JLabel etiquetaContraseña = new JLabel("Contraseña:");
        campoContraseña = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelFormulario.add(etiquetaContraseña, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelFormulario.add(campoContraseña, gbc);

        // Botón de login centrado
        botonLogin = new JButton("Ingresar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa las dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(botonLogin, gbc);

        // Agregar el formulario al panel blanco
        panelRectangulo.add(panelFormulario);

        // Añadir el panel blanco al panel de fondo
        panelFondo.add(panelRectangulo);

        // Añadir el panel de fondo a la ventana
        add(panelFondo);
        setVisible(true);
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContraseña() {
        return campoContraseña;
    }

    public JButton getBotonLogin() {
        return botonLogin;
    }
}
