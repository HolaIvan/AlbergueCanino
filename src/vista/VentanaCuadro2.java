package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaCuadro2 extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelFormulario;
    private JButton btnGuardar;
    private JTextField txtProducto, txtCantidad, txtPrecioUnidad;
    private JTextArea txtObservaciones;
    private JDateChooser dateChooser;
    private JComboBox<String> cbCategoria, cbUnidadMedida;
    private int rowIndexToEdit = -1; // Índice del registro que se está editando

    public VentanaCuadro2() {
        setTitle("Registro Inventario");
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

        String[] columnNames = {"Producto", "Cantidad", "Precio Unidad", "Observaciones", "Fecha Vencimiento", "Categoría", "Unidad Medida", "Editar", "Eliminar"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7 || column == 8; // Permitir solo botones editar y eliminar
            }
        };

        // Renderizadores y editores de botones
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Editar"));
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor("Editar"));

        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer("Eliminar"));
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor("Eliminar"));

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
        ImageIcon backgroundImage = new ImageIcon("src/images/registroInventario.jpg");
        Image img = backgroundImage.getImage();
        
        // Establecer el ancho y la altura que deseas
        int customWidth = 340;  // Tu propio ancho deseado
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
        txtPrecioUnidad = new JTextField(15);
        txtObservaciones = new JTextArea(3, 15);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());

        cbCategoria = new JComboBox<>(new String[]{"Electrónica", "Ropa", "Comida", "Herramientas", "Otros"});
        cbUnidadMedida = new JComboBox<>(new String[]{"Kilogramo (kg)", "Metro (m)", "Unidad (u)", "Litro (L)", "Caja (c)", "Gramo (g)"});

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
        panelFormulario.add(new JLabel("Precio Unidad:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPrecioUnidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(new JScrollPane(txtObservaciones), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Fecha Vencimiento:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(dateChooser, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(cbCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(new JLabel("Unidad Medida:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(cbUnidadMedida, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
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
        String precioUnidad = txtPrecioUnidad.getText();
        String observaciones = txtObservaciones.getText();
        Date fechaVencimiento = dateChooser.getDate();
        String categoria = (String) cbCategoria.getSelectedItem();
        String unidadMedida = (String) cbUnidadMedida.getSelectedItem();

        if (producto.isEmpty() || cantidad.isEmpty() || precioUnidad.isEmpty() || fechaVencimiento == null) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(fechaVencimiento);

        if (rowIndexToEdit >= 0) {
            // Actualizar fila existente
            tableModel.setValueAt(producto, rowIndexToEdit, 0);
            tableModel.setValueAt(cantidad, rowIndexToEdit, 1);
            tableModel.setValueAt(precioUnidad, rowIndexToEdit, 2);
            tableModel.setValueAt(observaciones, rowIndexToEdit, 3);
            tableModel.setValueAt(fecha, rowIndexToEdit, 4);
            tableModel.setValueAt(categoria, rowIndexToEdit, 5);
            tableModel.setValueAt(unidadMedida, rowIndexToEdit, 6);

            btnGuardar.setText("Guardar");
            rowIndexToEdit = -1;
        } else {
            // Crear nueva fila
            tableModel.addRow(new Object[]{producto, cantidad, precioUnidad, observaciones, fecha, categoria, unidadMedida, "Editar", "Eliminar"});
        }

        limpiarFormulario();
    }

    private void cargarDatosEnFormulario(int rowIndex) {
        txtProducto.setText((String) tableModel.getValueAt(rowIndex, 0));
        txtCantidad.setText((String) tableModel.getValueAt(rowIndex, 1));
        txtPrecioUnidad.setText((String) tableModel.getValueAt(rowIndex, 2));
        txtObservaciones.setText((String) tableModel.getValueAt(rowIndex, 3));

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateChooser.setDate(dateFormat.parse((String) tableModel.getValueAt(rowIndex, 4)));
        } catch (Exception e) {
            dateChooser.setDate(new Date());
        }

        cbCategoria.setSelectedItem(tableModel.getValueAt(rowIndex, 5));
        cbUnidadMedida.setSelectedItem(tableModel.getValueAt(rowIndex, 6));

        btnGuardar.setText("Editar");
        rowIndexToEdit = rowIndex;
    }

    private void limpiarFormulario() {
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecioUnidad.setText("");
        txtObservaciones.setText("");
        dateChooser.setDate(new Date());
        cbCategoria.setSelectedIndex(0);
        cbUnidadMedida.setSelectedIndex(0);
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
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(String text) {
            super(new JCheckBox());
            button = new JButton();
            button.setOpaque(true);
            label = text;

            button.addActionListener(e -> {
                int row = table.getSelectedRow();
                if ("Editar".equals(label)) {
                    cargarDatosEnFormulario(row);   
                } else if ("Eliminar".equals(label)) {
                    int confirm = JOptionPane.showConfirmDialog(
                        VentanaCuadro2.this, 
                        "¿Está seguro de eliminar el registro?", 
                        "Confirmar eliminación", 
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(row);
                    }
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaCuadro2::new);
    }
}
