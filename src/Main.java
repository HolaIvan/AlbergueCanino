package main;

import vista.LoginView;
import controlador.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
    }
}
