import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class BusquedaBinariaGUI extends JFrame {
    private JTextField inputField;
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private int[] array;

    public BusquedaBinariaGUI() {
        setTitle("Búsqueda Binaria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Ingrese números separados por comas:"));
        inputField = new JTextField(20);
        inputPanel.add(inputField);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(new JLabel("Número a buscar:"));
        searchField = new JTextField(10);
        searchPanel.add(searchField);
        searchButton = new JButton("Buscar");
        searchPanel.add(searchButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
    }

    private void buscar() {
        String input = inputField.getText();
        String[] numbers = input.split(",");
        array = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = Integer.parseInt(numbers[i].trim());
        }
        
        // Ordenar el arreglo (necesario para la búsqueda binaria)
        Arrays.sort(array);

        int target = Integer.parseInt(searchField.getText());
        int result = busquedaBinaria(array, target);

        if (result != -1) {
            resultArea.setText("El número " + target + " se encontró en la posición " + result + 
                               " del arreglo ordenado.\nArreglo ordenado: " + Arrays.toString(array));
        } else {
            resultArea.setText("El número " + target + " no se encontró en el arreglo.\n" + 
                               "Arreglo ordenado: " + Arrays.toString(array));
        }
    }

    private int busquedaBinaria(int[] arr, int target) {
        int izquierda = 0;
        int derecha = arr.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arr[medio] == target) {
                return medio;
            }

            if (arr[medio] < target) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BusquedaBinariaGUI().setVisible(true);
            }
        });
    }
}