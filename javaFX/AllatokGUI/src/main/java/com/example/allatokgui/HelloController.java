package com.example.allatokgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloController {

    @FXML private ListView<String> lista;
    @FXML private TextField tfMettol, tfMeddig;
    @FXML private Button btSzures;

    public class Allat {
        public String faj;
        public int magas;
        public int suly;
        public int kor;

        public Allat(String sor) {
            String[] s = sor.split(";");
            faj = s[0];
            magas = Integer.parseInt(s[1]);
            suly = Integer.parseInt(s[2]);
            kor = Integer.parseInt(s[3]);
        }
    }

    private ArrayList<Allat> allatok = new ArrayList<>();
    private FileChooser fc = new FileChooser();

    public void initialize() {
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV fájlok", "*.csv"));
    }

    @FXML private void Szures() {
        lista.getItems().clear();
        int min = Integer.parseInt(tfMettol.getText());
        int max = Integer.parseInt(tfMeddig.getText());
        for (Allat a : allatok) {
            if (a.magas >= min && a.magas <= max) {
                lista.getItems().add(a.faj + " (" + a.magas + "cm, " + a.suly + "kg)");
            }
        }
    }

    @FXML private void Megnyitas() {
        File fajl = fc.showOpenDialog(lista.getScene().getWindow());
        if (fajl != null) {
            betolt(fajl);
            btSzures.setDisable(false);
            Szures();
        }
    }

    private void betolt(File fajl) {
        Scanner be = null;
        try {
            be = new Scanner(fajl, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) {
                allatok.add(new Allat(be.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (be != null) {
                be.close();
            }
        }
    }

    @FXML private void Kilepes() {
        Platform.exit();
    }

    @FXML private void Nevjegy() {
    Alert nevjegy = new Alert(Alert.AlertType.INFORMATION);
        nevjegy.setTitle("");
        nevjegy.setHeaderText(null);
        nevjegy.setContentText("Állatok v1.0.0\n\nKandó 2025");
        nevjegy.showAndWait();
    }
}