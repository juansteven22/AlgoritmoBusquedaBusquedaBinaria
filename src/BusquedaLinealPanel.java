import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BusquedaLinealPanel extends JPanel {
    private JTextField inputField;
    private JTextField searchField;
    private JButton searchButton;
    private JButton nextButton;
    private JButton resetButton;
    private JPanel arrayPanel;
    private JLabel statusLabel;
    private int[] array;
    private int currentIndex, target;

    public BusquedaLinealPanel() {
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

        target = Integer.parseInt(searchField.getText());
        currentIndex = 0;

        actualizarVisualizacion();
        nextButton.setEnabled(true);
        resetButton.setEnabled(true);
        searchButton.setEnabled(false);
        inputField.setEnabled(false);
        searchField.setEnabled(false);
        statusLabel.setText("Búsqueda iniciada. Presione 'Siguiente Paso'");
    }

    private void siguientePaso() {
        if (currentIndex < array.length) {
            if (array[currentIndex] == target) {
                statusLabel.setText("¡Encontrado en el índice " + currentIndex + "!");
                nextButton.setEnabled(false);
            } else {
                statusLabel.setText("Buscando... Índice actual: " + currentIndex);
                currentIndex++;
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

            if (i == currentIndex) {
                cellPanel.setBackground(Color.YELLOW);
            } else if (i < currentIndex) {
                cellPanel.setBackground(Color.LIGHT_GRAY);
            }

            arrayPanel.add(cellPanel);
        }

        arrayPanel.revalidate();
        arrayPanel.repaint();
    }
}