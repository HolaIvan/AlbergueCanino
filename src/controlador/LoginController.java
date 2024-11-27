package controlador;

import vista.LoginView;
import vista.MainVista;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getBotonLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = loginView.getCampoUsuario().getText();
                String contraseña = new String(loginView.getCampoContraseña().getPassword());

                // Validar usuario y contraseña (esto es un ejemplo, agrega tu lógica aquí)
                if (usuario.equals("admin") && contraseña.equals("1234")) {
                    // Si el login es exitoso, abrir MainView
                    new MainVista();
                    loginView.dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos.");
                }
            }
        });
    }
}
