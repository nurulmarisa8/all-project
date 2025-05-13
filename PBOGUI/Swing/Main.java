import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private JFrame frame;
    private JTextField namaField, nimField, univAsalField;
    private JComboBox<String> jenisMahasiswaCombo;
    private JTextArea outputArea;
    private JLabel univAsalLabel;

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
        } else {
            MahasiswaTransfer mahasiswaTransfer = new MahasiswaTransfer(nama, nim, universitasAsal);
            mahasiswaTransfer.perkenalan();
            mahasiswaTransfer.belajar();
            mahasiswaTransfer.tugas();
            mahasiswaTransfer.infoTransfer();
        }
        outputArea.append("\n");
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
        private JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char)b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}