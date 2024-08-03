import javax.swing.*;

public class AlgoritmosBusquedaGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private BusquedaBinariaPanel busquedaBinariaPanel;
    private BusquedaLinealPanel busquedaLinealPanel;

    public AlgoritmosBusquedaGUI() {
        setTitle("Algoritmos de Búsqueda Visual");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        busquedaBinariaPanel = new BusquedaBinariaPanel();
        busquedaLinealPanel = new BusquedaLinealPanel();

        tabbedPane.addTab("Búsqueda Binaria", busquedaBinariaPanel);
        tabbedPane.addTab("Búsqueda Lineal", busquedaLinealPanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AlgoritmosBusquedaGUI().setVisible(true));
    }
}
