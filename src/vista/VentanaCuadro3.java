package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaCuadro3 extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelFormulario;
    private JButton btnGuardar;
    private JTextField txtProducto, txtCantidad, txtPersonaEntidad, txtDniRuc;
    private JTextArea txtObservaciones;
    private JComboBox<String> cmbTipoDniRuc; // Selector DNI/RUC
    private int rowIndexToEdit = -1; // Índice del registro que se está editando

    public VentanaCuadro3() {
        setTitle("Registro Donaciones");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(Color.RED);
        btnRegresar.setForeground(Color.WHITE);

        panelBotones.add(btnRegresar);

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());

        // Modificar las columnas de la tabla según los nuevos campos
        String[] columnNames = {"Producto", "Cantidad", "Persona o Entidad Responsable", "DNI o RUC", "Observaciones", "Editar", "Eliminar"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Permitir solo botones editar y eliminar
            }
        };

        // Renderizadores y editores de botones
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Editar"));
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor("Editar"));

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Eliminar"));
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor("Eliminar"));

        JScrollPane scrollPane = new JScrollPane(table);
        panelContenido.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);

        mostrarFormularioRegistrar();
    }

    private void mostrarFormularioRegistrar() {
        panelFormulario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                super.paintComponent(g);
        
        // Cargar la imagen
        ImageIcon backgroundImage = new ImageIcon("src/images/donacionPerrito.jpg");
        Image img = backgroundImage.getImage();
        
        // Establecer el ancho y la altura que deseas
        int customWidth = 320;  // Tu propio ancho deseado
        int customHeight = 300; // Tu propio alto deseado
        
        // Dibujar la imagen con el tamaño personalizado
        g.drawImage(img, 0, 0, customWidth, customHeight, this);
            }
        };
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de entrada
        txtProducto = new JTextField(15);
        txtCantidad = new JTextField(15);
        txtPersonaEntidad = new JTextField(15);
        txtDniRuc = new JTextField(15);
        txtObservaciones = new JTextArea(3, 15);
        
        // Aplicar filtro numérico solo en el campo DNI o RUC
    txtDniRuc.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            // Permitir solo números
            if (!Character.isDigit(c)) {
                e.consume();  // Ignorar la tecla si no es un número
            }
        }
    });
        

        // Selector DNI/RUC
        cmbTipoDniRuc = new JComboBox<>(new String[]{"DNI", "RUC"});

        btnGuardar = new JButton("Guardar");

        // Añadir los campos al panel
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Producto:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtProducto, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Persona o Entidad Responsable:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPersonaEntidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Tipo de documento:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(cmbTipoDniRuc, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("DNI o RUC:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtDniRuc, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(new JScrollPane(txtObservaciones), gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnGuardar, gbc);

        panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(panelFormulario, BorderLayout.NORTH);

        btnGuardar.addActionListener(e -> guardarOEditarRegistro());

        revalidate();
        repaint();
    }

    private void guardarOEditarRegistro() {
        String producto = txtProducto.getText();
        String cantidad = txtCantidad.getText();
        String personaEntidad = txtPersonaEntidad.getText();
        String dniRuc = txtDniRuc.getText();
        String observaciones = txtObservaciones.getText();
        String tipoDoc = (String) cmbTipoDniRuc.getSelectedItem();

        // Validar DNI o RUC
        if (!validarDniRuc(tipoDoc, dniRuc)) {
            return; // Detener la ejecución si la validación falla
        }

        if (producto.isEmpty() || cantidad.isEmpty() || personaEntidad.isEmpty() || dniRuc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rowIndexToEdit >= 0) {
            // Actualizar fila existente
            tableModel.setValueAt(producto, rowIndexToEdit, 0);
            tableModel.setValueAt(cantidad, rowIndexToEdit, 1);
            tableModel.setValueAt(personaEntidad, rowIndexToEdit, 2);
            tableModel.setValueAt(dniRuc, rowIndexToEdit, 3);
            tableModel.setValueAt(observaciones, rowIndexToEdit, 4);

            btnGuardar.setText("Guardar");
            rowIndexToEdit = -1;
        } else {
            // Crear nueva fila
            tableModel.addRow(new Object[]{producto, cantidad, personaEntidad, dniRuc, observaciones, "Editar", "Eliminar"});
        }

        limpiarFormulario();
    }

    private boolean validarDniRuc(String tipoDoc, String dniRuc) {
        // Validación para DNI
        if (tipoDoc.equals("DNI")) {
            if (dniRuc.length() != 8 || !dniRuc.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos numéricos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        // Validación para RUC
        else if (tipoDoc.equals("RUC")) {
            if (dniRuc.length() != 11 || !dniRuc.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El RUC debe tener exactamente 11 dígitos numéricos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void cargarDatosEnFormulario(int rowIndex) {
        txtProducto.setText((String) tableModel.getValueAt(rowIndex, 0));
        txtCantidad.setText((String) tableModel.getValueAt(rowIndex, 1));
        txtPersonaEntidad.setText((String) tableModel.getValueAt(rowIndex, 2));
        txtDniRuc.setText((String) tableModel.getValueAt(rowIndex, 3));
        txtObservaciones.setText((String) tableModel.getValueAt(rowIndex, 4));

        btnGuardar.setText("Editar");
        rowIndexToEdit = rowIndex;
    }

    private void limpiarFormulario() {
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPersonaEntidad.setText("");
        txtDniRuc.setText("");
        txtObservaciones.setText("");
    }

    // Renderizador de botones
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor de botones
    class ButtonEditor extends DefaultCellEditor {
        private String text;

        public ButtonEditor(String text) {
            super(new JCheckBox());
            this.text = text;
            getComponent().setBackground(Color.GRAY);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = new JButton(text);
            button.addActionListener(e -> {
                if (text.equals("Editar")) {
                    cargarDatosEnFormulario(row);
                } else {
                    tableModel.removeRow(row);
                }
            });
            return button;
        }
    }

    public static void main(String[] args) {
        new VentanaCuadro3();
    }
}
