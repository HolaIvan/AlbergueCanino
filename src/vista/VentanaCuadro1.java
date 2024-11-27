package vista;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class VentanaCuadro1 extends JFrame {
    private JPanel mainPanel;
    private JPanel formularioPanel;
    private JPanel listaPanel;
    private JTextField nombreField, razaField, edadField, colorField, tamanoField, pesoField, enfermedadField, discapacidadField;
    private JComboBox<String> sexoCombo, esterilizadoCombo;
    private JButton guardarButton, regresarButton, registrarButton, listaButton, actualizarButton;
    private JTable tablaMascotas;
    private List<Mascota> listaMascotas;
    private Mascota mascotaEnEdicion; // Referencia a la mascota que se está editando

    public VentanaCuadro1(MainVista mainVista) {
        setTitle("Registro de Mascotas");
        setSize(850, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listaMascotas = new ArrayList<>();
        
        
        // Panel de fondo con imagen
        FondoPanel fondoPanel = new FondoPanel("src/images/registroCanino3.jpg");
        fondoPanel.setLayout(new BorderLayout()); // Para organizar los componentes
        setContentPane(fondoPanel); // Establece el panel con imagen como el contenido principal
        
        // Panel de botones en la parte superior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botonesPanel.setOpaque(false); // Hacer transparente para que se vea el fondo
        registrarButton = new JButton("Registrar");
        listaButton = new JButton("Lista");
        regresarButton = new JButton("Regresar");
        regresarButton.setBackground(Color.RED);
        regresarButton.setForeground(Color.WHITE);

        regresarButton.addActionListener(e -> {
            mainVista.setVisible(true);
            dispose();
        });

        botonesPanel.add(registrarButton);
        botonesPanel.add(listaButton);
        botonesPanel.add(regresarButton);

        // Panel principal para alternar entre formulario y lista
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setOpaque(false);

        // Panel del formulario con GridBagLayout
        formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos del formulario
        nombreField = new JTextField(15);
        razaField = new JTextField(15);
        edadField = new JTextField(15);
        colorField = new JTextField(15);
        tamanoField = new JTextField(15);
        pesoField = new JTextField(15);
        enfermedadField = new JTextField(15);
        discapacidadField = new JTextField(15);

        // Selectores
        sexoCombo = new JComboBox<>(new String[]{"Hembra", "Macho"});
        esterilizadoCombo = new JComboBox<>(new String[]{"Sí", "No"});

        // Agregar campos al formulario
        gbc.gridx = 0; gbc.gridy = 0;
        formularioPanel.add(new JLabel("Nombre del perro:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(nombreField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formularioPanel.add(new JLabel("Raza:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(razaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formularioPanel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(edadField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formularioPanel.add(new JLabel("Color:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(colorField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formularioPanel.add(new JLabel("Tamaño:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(tamanoField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formularioPanel.add(new JLabel("Peso:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(pesoField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formularioPanel.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(sexoCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        formularioPanel.add(new JLabel("Enfermedad:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(enfermedadField, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        formularioPanel.add(new JLabel("Discapacidad:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(discapacidadField, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        formularioPanel.add(new JLabel("Esterilizado:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(esterilizadoCombo, gbc);

        // Botón de guardar
        guardarButton = new JButton("Guardar Registro");
        guardarButton.addActionListener(e -> guardarRegistro());

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioPanel.add(guardarButton, gbc);

        // Botón de actualizar
        actualizarButton = new JButton("Actualizar Registro");
        actualizarButton.setVisible(false);
        actualizarButton.addActionListener(e -> actualizarRegistro());

        gbc.gridy = 11;
        formularioPanel.add(actualizarButton, gbc);

        // Panel de la tabla de lista de mascotas
        listaPanel = new JPanel(new BorderLayout());
        listaPanel.setOpaque(false);

        String[] columnNames = {"Nombre", "Raza", "Edad", "Color", "Tamaño", "Peso", "Sexo", "Enfermedad", "Discapacidad", "Esterilizado", "Acciones"};
        tablaMascotas = new JTable(new javax.swing.table.DefaultTableModel(new Object[0][11], columnNames));
        JScrollPane scrollPane = new JScrollPane(tablaMascotas);
        listaPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(formularioPanel, "Formulario");
        mainPanel.add(listaPanel, "Lista");

        registrarButton.addActionListener(e -> mostrarFormulario());
        listaButton.addActionListener(e -> mostrarLista());

        add(botonesPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void mostrarFormulario() {
        guardarButton.setVisible(true);
        actualizarButton.setVisible(false);
        limpiarFormulario();
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Formulario");
    }

    private void mostrarLista() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Lista");
        actualizarTabla();
    }

    private void guardarRegistro() {
        Mascota mascota = new Mascota(
                nombreField.getText(),
                razaField.getText(),
                edadField.getText(),
                colorField.getText(),
                tamanoField.getText(),
                pesoField.getText(),
                (String) sexoCombo.getSelectedItem(),
                enfermedadField.getText(),
                discapacidadField.getText(),
                (String) esterilizadoCombo.getSelectedItem()
        );
        listaMascotas.add(mascota);
        JOptionPane.showMessageDialog(this, "Registro guardado exitosamente.");
        limpiarFormulario();
    }
    
    // Clase para el fondo con imagen
    class FondoPanel extends JPanel {
        private Image imagenFondo;

        public FondoPanel(String rutaImagen) {
            try {
                imagenFondo = new ImageIcon(rutaImagen).getImage();
            } catch (Exception e) {
                System.err.println("Error cargando la imagen de fondo: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenFondo != null) {
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    

    private void actualizarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Nombre", "Raza", "Edad", "Color", "Tamaño", "Peso", "Sexo", "Enfermedad", "Discapacidad", "Esterilizado", "Editar", "Eliminar"};
        model.setColumnIdentifiers(columnNames);

        for (Mascota mascota : listaMascotas) {
            Object[] row = new Object[12];
            row[0] = mascota.getNombre();
            row[1] = mascota.getRaza();
            row[2] = mascota.getEdad();
            row[3] = mascota.getColor();
            row[4] = mascota.getTamano();
            row[5] = mascota.getPeso();
            row[6] = mascota.getSexo();
            row[7] = mascota.getEnfermedad();
            row[8] = mascota.getDiscapacidad();
            row[9] = mascota.getEsterilizado();
            row[10] = "Editar"; // Texto que se mostrará en la columna
            row[10] = "Eliminar"; // Texto que se mostrará en la columna
            model.addRow(row);
        }

    tablaMascotas.setModel(model);

    // Configuración para mostrar botones
    tablaMascotas.getColumn("Editar").setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = new JButton("Editar");
            return button;
        }
    });

    tablaMascotas.getColumn("Editar").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        private JButton button = new JButton("Editar");

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.addActionListener(e -> {
                Mascota mascota = listaMascotas.get(row);
                mostrarFormulario();
                mascotaEnEdicion = mascota;
                nombreField.setText(mascota.getNombre());
                razaField.setText(mascota.getRaza());
                edadField.setText(mascota.getEdad());
                colorField.setText(mascota.getColor());
                tamanoField.setText(mascota.getTamano());
                pesoField.setText(mascota.getPeso());
                enfermedadField.setText(mascota.getEnfermedad());
                discapacidadField.setText(mascota.getDiscapacidad());
                sexoCombo.setSelectedItem(mascota.getSexo());
                esterilizadoCombo.setSelectedItem(mascota.getEsterilizado());
                guardarButton.setVisible(false);
                actualizarButton.setVisible(true);
            });
            return button;
        }
    });
    
     // Configuración de la columna "Eliminar"
    tablaMascotas.getColumn("Eliminar").setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = new JButton("Eliminar");
            return button;
        }
    });
    tablaMascotas.getColumn("Eliminar").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        private JButton button = new JButton("Eliminar");

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaMascotas.remove(row);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(null, "Registro eliminado.");
                }
            });
            return button;
        }
    });
}
    private JButton crearBotonEditar(Mascota mascota) {
        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(e -> {
            mostrarFormulario();
            mascotaEnEdicion = mascota;
            nombreField.setText(mascota.getNombre());
            razaField.setText(mascota.getRaza());
            edadField.setText(mascota.getEdad());
            colorField.setText(mascota.getColor());
            tamanoField.setText(mascota.getTamano());
            pesoField.setText(mascota.getPeso());
            enfermedadField.setText(mascota.getEnfermedad());
            discapacidadField.setText(mascota.getDiscapacidad());
            sexoCombo.setSelectedItem(mascota.getSexo());
            esterilizadoCombo.setSelectedItem(mascota.getEsterilizado());
            guardarButton.setVisible(false);
            actualizarButton.setVisible(true);
        });
        return editarButton;
    }

    private void actualizarRegistro() {
        mascotaEnEdicion.setNombre(nombreField.getText());
        mascotaEnEdicion.setRaza(razaField.getText());
        mascotaEnEdicion.setEdad(edadField.getText());
        mascotaEnEdicion.setColor(colorField.getText());
        mascotaEnEdicion.setTamano(tamanoField.getText());
        mascotaEnEdicion.setPeso(pesoField.getText());
        mascotaEnEdicion.setEnfermedad(enfermedadField.getText());
        mascotaEnEdicion.setDiscapacidad(discapacidadField.getText());
        mascotaEnEdicion.setSexo((String) sexoCombo.getSelectedItem());
        mascotaEnEdicion.setEsterilizado((String) esterilizadoCombo.getSelectedItem());

        JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente.");
        mostrarLista();
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        razaField.setText("");
        edadField.setText("");
        colorField.setText("");
        tamanoField.setText("");
        pesoField.setText("");
        enfermedadField.setText("");
        discapacidadField.setText("");
        sexoCombo.setSelectedIndex(0);
        esterilizadoCombo.setSelectedIndex(0);
        mascotaEnEdicion = null;
    }
}

class Mascota {
    private String nombre, raza, edad, color, tamano, peso, sexo, enfermedad, discapacidad, esterilizado;

    public Mascota(String nombre, String raza, String edad, String color, String tamano, String peso, String sexo, String enfermedad, String discapacidad, String esterilizado) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
        this.tamano = tamano;
        this.peso = peso;
        this.sexo = sexo;
        this.enfermedad = enfermedad;
        this.discapacidad = discapacidad;
        this.esterilizado = esterilizado;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }
    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getEnfermedad() { return enfermedad; }
    public void setEnfermedad(String enfermedad) { this.enfermedad = enfermedad; }
    public String getDiscapacidad() { return discapacidad; }
    public void setDiscapacidad(String discapacidad) { this.discapacidad = discapacidad; }
    public String getEsterilizado() { return esterilizado; }
    public void setEsterilizado(String esterilizado) { this.esterilizado = esterilizado; }
}
