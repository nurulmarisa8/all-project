import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainOverRiddingFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mahasiswa JavaFX");

        // Membuat objek dari kelas turunan
        OverRiddingMahasiswa reguler = new MahasiswaReguler("Aco", "12345");
        OverRiddingMahasiswa transfer = new MahasiswaTransfer("Baddu", "67890", "UNHAS");

        // Membuat elemen GUI
        Label nameLabel = new Label("Nama:");
        TextField nameText = new TextField();

        Label nimLabel = new Label("NIM:");
        TextField nimText = new TextField();

        Button btn = new Button("Perkenalkan");
        TextArea resultArea = new TextArea();

        // Mengatur tombol aksi
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nama = nameText.getText();
                String nim = nimText.getText();
                resultArea.setText("Halo, saya " + nama + " dengan NIM " + nim);
                reguler.perkenalan();
                transfer.perkenalan();
            }
        });

        // Menambahkan elemen ke dalam layout
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(nameLabel, nameText, nimLabel, nimText, btn, resultArea);

        // Membuat scene dan menampilkan ke stage
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
