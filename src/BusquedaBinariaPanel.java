import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class BusquedaBinariaPanel extends JPanel {
    private JTextField inputField;
    private JTextField searchField;
    private JButton searchButton;
    private JButton nextButton;
    private JButton resetButton;
    private JPanel arrayPanel;
    private JLabel statusLabel;
    private int[] array;
    private int left, right, mid, target;

    public BusquedaBinariaPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Números (separados por comas):"));
        inputField = new JTextField(20);
        inputPanel.add(inputField);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Número a buscar:"));
        searchField = new JTextField(10);
        searchPanel.add(searchField);
        searchButton = new JButton("Iniciar Búsqueda");
        searchPanel.add(searchButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        nextButton = new JButton("Siguiente Paso");
        nextButton.setEnabled(false);
        buttonPanel.add(nextButton);
        resetButton = new JButton("Reiniciar");
        resetButton.setEnabled(false);
        buttonPanel.add(resetButton);

        arrayPanel = new JPanel();
        arrayPanel.setPreferredSize(new Dimension(750, 200));

        statusLabel = new JLabel("Ingrese los números y el valor a buscar");

        add(inputPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(arrayPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.EAST);
        add(statusLabel, BorderLayout.WEST);

        searchButton.addActionListener(e -> iniciarBusqueda());
        nextButton.addActionListener(e -> siguientePaso());
        resetButton.addActionListener(e -> reiniciar());
    }

    private void iniciarBusqueda() {
        String input = inputField.getText();
        String[] numbers = input.split(",");
        array = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = Integer.parseInt(numbers[i].trim());
        }
        Arrays.sort(array);

        target = Integer.parseInt(searchField.getText());
        left = 0;
        right = array.length - 1;
        mid = (left + right) / 2;

        actualizarVisualizacion();
        nextButton.setEnabled(true);
        resetButton.setEnabled(true);
        searchButton.setEnabled(false);
        inputField.setEnabled(false);
        searchField.setEnabled(false);
        statusLabel.setText("Búsqueda iniciada. Presione 'Siguiente Paso'");
    }

    private void siguientePaso() {
        if (left <= right) {
            mid = left + (right - left) / 2;

            if (array[mid] == target) {
                statusLabel.setText("¡Encontrado en el índice " + mid + "!");
                nextButton.setEnabled(false);
                return;
            }

            if (array[mid] < target) {
                left = mid + 1;
                statusLabel.setText("El valor en el medio es menor. Moviendo a la derecha.");
            } else {
                right = mid - 1;
                statusLabel.setText("El valor en el medio es mayor. Moviendo a la izquierda.");
            }

            actualizarVisualizacion();
        } else {
            statusLabel.setText("El valor no se encuentra en el arreglo.");
            nextButton.setEnabled(false);
        }
    }

    private void reiniciar() {
        inputField.setEnabled(true);
        searchField.setEnabled(true);
        searchButton.setEnabled(true);
        nextButton.setEnabled(false);
        resetButton.setEnabled(false);
        statusLabel.setText("Ingrese los números y el valor a buscar");
        arrayPanel.removeAll();
        arrayPanel.revalidate();
        arrayPanel.repaint();
    }

    private void actualizarVisualizacion() {
        arrayPanel.removeAll();
        arrayPanel.setLayout(new GridLayout(1, array.length, 5, 5));

        for (int i = 0; i < array.length; i++) {
            JPanel cellPanel = new JPanel();
            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(String.valueOf(array[i]));
            cellPanel.add(label);

            if (i == left) {
                cellPanel.setBackground(Color.GREEN);
            } else if (i == right) {
                cellPanel.setBackground(Color.RED);
            } else if (i == mid) {
                cellPanel.setBackground(Color.YELLOW);
            }

            arrayPanel.add(cellPanel);
        }

        arrayPanel.revalidate();
        arrayPanel.repaint();
    }
}