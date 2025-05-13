import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Main {
    private final JFrame frame;
    private final JTextField namaField, nimField, univAsalField;
    private final JComboBox<String> jenisMahasiswaCombo;
    private final JTextArea outputArea;
    private final JLabel univAsalLabel;

    public Main() {
        frame = new JFrame("Data Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Components
        JLabel jenisLabel = new JLabel("Jenis Mahasiswa:");
        String[] jenis = {"Mahasiswa Reguler", "Mahasiswa Transfer"};
        jenisMahasiswaCombo = new JComboBox<>(jenis);
        jenisMahasiswaCombo.addActionListener(e -> toggleUnivAsalField());

        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField();

        JLabel nimLabel = new JLabel("NIM:");
        nimField = new JTextField();

        univAsalLabel = new JLabel("Universitas Asal:");
        univAsalField = new JTextField();
        univAsalField.setEnabled(false);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleSubmit());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());

        // Add components to input panel
        inputPanel.add(jenisLabel);
        inputPanel.add(jenisMahasiswaCombo);
        inputPanel.add(namaLabel);
        inputPanel.add(namaField);
        inputPanel.add(nimLabel);
        inputPanel.add(nimField);
        inputPanel.add(univAsalLabel);
        inputPanel.add(univAsalField);
        inputPanel.add(submitButton);
        inputPanel.add(clearButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Redirect console output to text area
        TextAreaOutputStream taos = new TextAreaOutputStream(outputArea);
        System.setOut(new java.io.PrintStream(taos));

        frame.setVisible(true);
    }

    private void toggleUnivAsalField() {
        boolean isTransfer = jenisMahasiswaCombo.getSelectedIndex() == 1;
        univAsalField.setEnabled(isTransfer);
        univAsalLabel.setEnabled(isTransfer);
    }

    private void handleSubmit() {
        String nama = namaField.getText().trim();
        String nim = nimField.getText().trim();
        String universitasAsal = univAsalField.getText().trim();
        int pilihan = jenisMahasiswaCombo.getSelectedIndex();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nama dan NIM harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pilihan == 1 && universitasAsal.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Universitas asal harus diisi untuk mahasiswa transfer!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        outputArea.append("--- Data Mahasiswa ---\n");

        if (pilihan == 0) {
            MahasiswaReguler mahasiswa = new MahasiswaReguler(nama, nim);
            mahasiswa.perkenalan();
            mahasiswa.belajar();
            mahasiswa.tugas();
            saveToFile(nama, nim, universitasAsal, "Mahasiswa Reguler");
        } else {
            MahasiswaTransfer mahasiswaTransfer = new MahasiswaTransfer(nama, nim, universitasAsal);
            mahasiswaTransfer.perkenalan();
            mahasiswaTransfer.belajar();
            mahasiswaTransfer.tugas();
            mahasiswaTransfer.infoTransfer();
            saveToFile(nama, nim, universitasAsal, "Mahasiswa Transfer");
        }
        outputArea.append("\n");
    }

    // Fixed saveToFile method using BufferedWriter
    private void saveToFile(String nama, String nim, String universitasAsal, String jenisMahasiswa) {
        try {
            // Prepare data to be written to the file
            String txtData = "Nama: " + nama + "\nNIM: " + nim + "\nJenis Mahasiswa: " + jenisMahasiswa + "\nUniversitas Asal: " + universitasAsal + "\n\n";

            // Open BufferedWriter in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));  // 'true' means append mode

            // Write data to the file
            writer.write(txtData);

            // Close the writer after writing
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error while saving the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // Print the stack trace for debugging
        }
    }

    private void clearFields() {
        namaField.setText("");
        nimField.setText("");
        univAsalField.setText("");
        jenisMahasiswaCombo.setSelectedIndex(0);
        outputArea.setText("");
    }

    // Class to redirect console output to JTextArea
    private static class TextAreaOutputStream extends java.io.OutputStream {
        private final JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

abstract class OverRiddingMahasiswa {
    protected final String nama;
    protected final String nim;

    public OverRiddingMahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public abstract void belajar();

    public abstract void tugas();

    public void perkenalan() {
        System.out.println("Halo, saya " + nama + " dengan NIM " + nim);
    }
}

class MahasiswaReguler extends OverRiddingMahasiswa {
    public MahasiswaReguler(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public void belajar() {
        System.out.println(nama + " sedang belajar di kampus.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas kuliah biasa.");
    }
}

class MahasiswaTransfer extends OverRiddingMahasiswa {
    private final String universitasAsal;

    public MahasiswaTransfer(String nama, String nim, String universitasAsal) {
        super(nama, nim);
        this.universitasAsal = universitasAsal;
    }

    @Override
    public void belajar() {
        System.out.println(nama + " belajar sambil menyesuaikan diri dari transfer.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas tambahan untuk adaptasi.");
    }

    public void infoTransfer() {
        System.out.println(nama + " berasal dari universitas " + universitasAsal + ".");
    }
}
