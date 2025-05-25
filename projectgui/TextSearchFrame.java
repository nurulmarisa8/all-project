import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class TextSearchFrame extends JFrame {

    // Ubah lebar field agar lebih pendek
    private JTextField folderPathField = new JTextField(15);
    private JTextField keywordField = new JTextField(15);
    private DefaultTableModel tableModel;
    private JTable resultTable;
    private File selectedFolder;

    public TextSearchFrame() {
        setTitle("GUI – SWING / JAVA FX\nTUGAS CB 3");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Judul
        JLabel titleLabel = new JLabel("TUGAS CB 3", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel subtitleLabel = new JLabel("GUI – SWING / JAVA FX", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(subtitleLabel, BorderLayout.NORTH);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));

        // Panel Identitas
        JPanel identityPanel = new JPanel(new GridLayout(3, 2, 10, 2));
        identityPanel.add(new JLabel("NIM", SwingConstants.CENTER));
        identityPanel.add(new JLabel("NAMA", SwingConstants.CENTER));
        identityPanel.add(new JLabel("H071241001", SwingConstants.CENTER));
        identityPanel.add(new JLabel("Maynova Christin Gabryela Simamora", SwingConstants.CENTER));
        identityPanel.add(new JLabel("H071241015", SwingConstants.CENTER));
        identityPanel.add(new JLabel("Nurul Marisa Clara Waldi", SwingConstants.CENTER));
        identityPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Identitas"));

        // Panel Pilih Folder & Cari Teks
        JButton folderBtn = new JButton("Folder");
        folderBtn.setPreferredSize(new Dimension(80, 25));
        folderPathField.setEditable(false);

        JButton searchBtn = new JButton("Cari Teks");
        searchBtn.setPreferredSize(new Dimension(100, 25));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Pencarian"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;

        // Baris 1: Folder
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(folderBtn, gbc);

        gbc.gridx = 1;
        inputPanel.add(folderPathField, gbc);

        // Baris 2: Cari Teks
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(searchBtn, gbc);

        gbc.gridx = 1;
        inputPanel.add(keywordField, gbc);

        // Label instruksi
        JLabel instruksiLabel = new JLabel("Masukkan kata kunci, lalu klik Cari Teks", SwingConstants.CENTER);

        // Tabel hasil
        tableModel = new DefaultTableModel(new String[]{"Nama File", "Teks/Kalimat Mengandung Kata"}, 0);
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Hasil Pencarian"));

        // Panel tengah (identitas + input)
        JPanel tengahPanel = new JPanel();
        tengahPanel.setLayout(new BoxLayout(tengahPanel, BoxLayout.Y_AXIS));
        tengahPanel.add(identityPanel);
        tengahPanel.add(Box.createVerticalStrut(10));
        tengahPanel.add(inputPanel);
        tengahPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tambahkan garis pemisah
        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();

        // Layout utama
        setLayout(new BorderLayout(10, 10));
        add(titlePanel, BorderLayout.NORTH);
        add(separator1, BorderLayout.BEFORE_FIRST_LINE);
        add(tengahPanel, BorderLayout.CENTER);
        add(instruksiLabel, BorderLayout.SOUTH);
        add(separator2, BorderLayout.AFTER_LAST_LINE);
        add(scrollPane, BorderLayout.PAGE_END);

        // Action listeners
        folderBtn.addActionListener(_ -> chooseFolder());
        searchBtn.addActionListener(_ -> searchKeyword());

        setVisible(true);
    }

    private void chooseFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFolder = chooser.getSelectedFile();
            folderPathField.setText(selectedFolder.getAbsolutePath());
        }
    }

    private void searchKeyword() {
        tableModel.setRowCount(0); // Bersihkan hasil sebelumnya
        if (selectedFolder == null || keywordField.getText().trim().isEmpty()) return;

        Queue<File> queue = new LinkedList<>();
        queue.add(selectedFolder);

        String keyword = keywordField.getText().toLowerCase();

        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (current.isDirectory()) {
                File[] files = current.listFiles();
                if (files != null) {
                    for (File f : files) queue.add(f);
                }
            } else if (current.getName().endsWith(".txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader(current))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.toLowerCase().contains(keyword)) {
                            tableModel.addRow(new Object[]{current.getName(), line.trim()});
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}