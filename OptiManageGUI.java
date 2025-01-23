import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OptiManageGUI {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField projectNameField;
    private JButton agregarTareasButton;
    private JButton eliminarTareasButton;
    private JButton asignarEmpleadosButton;
    private JButton generarReportesButton;
    private JTable tasksTable;
    private JComboBox<String> employeeSelector;
    private JTextArea descriptionArea;
    private JScrollPane tasksScrollPane;
    private JComboBox<String> taskStatusSelector;
    private JComboBox<String> projectSelector;
    private JComboBox<String> reportTypeSelector;

    private DefaultTableModel tableModel;

    public OptiManageGUI() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Descripción", "Estado", "Proyecto", "Empleado"}, 0);
        tasksTable.setModel(tableModel);

        agregarTareasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la tarea:");
                if (descripcion != null && !descripcion.isEmpty()) {
                    String estado = (String) taskStatusSelector.getSelectedItem();
                    String proyecto = (String) projectSelector.getSelectedItem();
                    tableModel.addRow(new Object[]{
                            tableModel.getRowCount() + 1, descripcion, estado, proyecto, "No asignado"
                    });
                    JOptionPane.showMessageDialog(mainPanel, "Tarea agregada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Debe ingresar una descripción válida.");
                }
            }
        });

        eliminarTareasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tasksTable.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(mainPanel, "Tarea eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Seleccione una tarea para eliminar.");
                }
            }
        });

        asignarEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tasksTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String empleadoNombre = projectNameField.getText().trim();
                    if (empleadoNombre != null && !empleadoNombre.isEmpty()) {
                        tableModel.setValueAt(empleadoNombre, selectedRow, 4);
                        JOptionPane.showMessageDialog(mainPanel, "Empleado asignado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Debe ingresar el nombre del empleado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Seleccione una tarea.");
                }
            }
        });

        generarReportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder reporte = new StringBuilder();
                String tipoReporte = (String) reportTypeSelector.getSelectedItem();
                String recursoAsignado = (String) employeeSelector.getSelectedItem();

                reporte.append("Tipo de Reporte: ").append(tipoReporte).append("\n");
                reporte.append("Recurso Asignado: ").append(recursoAsignado).append("\n\n");

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    reporte.append("Tarea ID: ").append(tableModel.getValueAt(i, 0))
                            .append(", Descripción: ").append(tableModel.getValueAt(i, 1))
                            .append(", Estado: ").append(tableModel.getValueAt(i, 2))
                            .append(", Proyecto: ").append(tableModel.getValueAt(i, 3))
                            .append(", Empleado: ").append(tableModel.getValueAt(i, 4)).append("\n");
                }

                if (reporte.length() > 0) {
                    descriptionArea.setText(reporte.toString());
                    JOptionPane.showMessageDialog(mainPanel, "Reporte generado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "No hay tareas para generar un reporte.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OptiManageGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new OptiManageGUI().mainPanel);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
