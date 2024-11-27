package modelo;

public class Autenticacion {
    // Usuario de prueba
    private final Usuario usuarioPrueba = new Usuario("admin", "1234");

    public boolean validarCredenciales(String nombre, String contraseña) {
        return nombre.equals(usuarioPrueba.getNombre()) && contraseña.equals(usuarioPrueba.getContraseña());
    }
}
