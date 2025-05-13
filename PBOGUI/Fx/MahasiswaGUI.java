import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MahasiswaGUI extends Application {
    private TextField namaField, nimField, univField;
    private ComboBox<String> jenisCombo;
    private TextArea outputArea;

    private ArrayList<OverRiddingMahasiswa> daftarMahasiswa = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Input Fields
        namaField = new TextField();
        nimField = new TextField();
        univField = new TextField();
        jenisCombo = new ComboBox<>();
        jenisCombo.getItems().addAll("Reguler", "Transfer");
        jenisCombo.setValue("Reguler");

        // Enable/Disable univField based on type
        jenisCombo.setOnAction(e -> {
            univField.setDisable(!jenisCombo.getValue().equals("Transfer"));
        });

        // Text Area Output
        outputArea = new TextArea();
        outputArea.setPrefHeight(250);
        outputArea.setEditable(false);

        // Buttons
        Button tambahBtn = new Button("Tambah/Simpan");
        Button viewBtn = new Button("View");
        Button keluarBtn = new Button("Keluar");

        // Layout Grid Input
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Nama:"), 0, 0);
        form.add(namaField, 1, 0);
        form.add(new Label("NIM:"), 0, 1);
        form.add(nimField, 1, 1);
        form.add(new Label("Jenis Mahasiswa:"), 0, 2);
        form.add(jenisCombo, 1, 2);
        form.add(new Label("Univ Asal (Jika Transfer):"), 0, 3);
        form.add(univField, 1, 3);

        // Tombol Bawah
        HBox tombolBox = new HBox(10, tambahBtn, viewBtn, keluarBtn);
        tombolBox.setPadding(new Insets(10));

        VBox root = new VBox(10, form, new Label("=== Data Mahasiswa ==="), outputArea, tombolBox);
        root.setPadding(new Insets(10));

        // Event Tambah
        tambahBtn.setOnAction(e -> {
            String nama = namaField.getText().trim();
            String nim = nimField.getText().trim();
            String jenis = jenisCombo.getValue();
            String univ = univField.getText().trim();

            if (nama.isEmpty() || nim.isEmpty() || (jenis.equals("Transfer") && univ.isEmpty())) {
                showAlert("Semua field harus diisi.");
                return;
            }

            if (jenis.equals("Reguler")) {
                daftarMahasiswa.add(new MahasiswaReguler(nama, nim));
            } else {
                daftarMahasiswa.add(new MahasiswaTransfer(nama, nim, univ));
            }

            namaField.clear();
            nimField.clear();
            univField.clear();
            univField.setDisable(true);
        });

        // Event View
        viewBtn.setOnAction(e -> {
            outputArea.clear();
            for (OverRiddingMahasiswa m : daftarMahasiswa) {
                outputArea.appendText("Halo, saya " + m.nama + " dengan NIM " + m.nim + "\n");
                if (m instanceof MahasiswaReguler) {
                    outputArea.appendText(m.nama + " sedang belajar di kampus.\n");
                    outputArea.appendText(m.nama + " mengerjakan tugas kuliah biasa.\n");
                } else if (m instanceof MahasiswaTransfer) {
                    MahasiswaTransfer mt = (MahasiswaTransfer) m;
                    outputArea.appendText(mt.nama + " belajar sambil menyesuaikan diri dari transfer.\n");
                    outputArea.appendText(mt.nama + " mengerjakan tugas tambahan untuk adaptasi.\n");
                    outputArea.appendText(mt.nama + " berasal dari Perguruan Tinggi " + mt.getUniversitasAsal() + ".\n");
                }
                outputArea.appendText("\n");
            }
        });

        // Event Keluar
        keluarBtn.setOnAction(e -> stage.close());

        // Stage
        stage.setTitle("Data Mahasiswa GUI");
        stage.setScene(new Scene(root, 500, 450));
        stage.show();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.showAndWait();
    }
}

abstract class OverRiddingMahasiswa {
    protected String nama;
    protected String nim;

    public OverRiddingMahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public abstract void belajar();
    public abstract void tugas();
}

class MahasiswaReguler extends OverRiddingMahasiswa {
    public MahasiswaReguler(String nama, String nim) {
        super(nama, nim);
    }

    public void belajar() {}
    public void tugas() {}
}

class MahasiswaTransfer extends OverRiddingMahasiswa {
    private String universitasAsal;

    public MahasiswaTransfer(String nama, String nim, String universitasAsal) {
        super(nama, nim);
        this.universitasAsal = universitasAsal;
    }

    public String getUniversitasAsal() {
        return universitasAsal;
    }

    public void belajar() {}
    public void tugas() {}
}
