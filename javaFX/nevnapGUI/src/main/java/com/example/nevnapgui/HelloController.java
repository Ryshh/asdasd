package com.example.nevnapgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class HelloController {

    // Átírtam String-re, mert a listában formázott szövegeket akarsz megjeleníteni
    @FXML private ListView<String> lista;
    @FXML private ComboBox<String> cmHonap;

    private FileChooser fc = new FileChooser();

    public class Adat {
        public String nev;
        public ArrayList<String> napok;

        public Adat(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            napok = new ArrayList<>();
            // HIBA JAVÍTVA: A név utáni dátumokat be kell tölteni a listába!
            for (int i = 1; i < s.length; i++) {
                napok.add(s[i]);
            }
        }
    }

    private ArrayList<Adat> adatok = new ArrayList<Adat>();

    public void initialize() {
        String[] honapok = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};
        for (String honap : honapok) {
            cmHonap.getItems().add(honap);
        }
        cmHonap.getSelectionModel().select(0);
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV fájlok", "*.csv"));
    }

    @FXML private void Megnyitas() {
        File fajl = fc.showOpenDialog(lista.getScene().getWindow());
        if(fajl != null) {
            // HIBA JAVÍTVA: Ha új fájlt nyitsz meg, érdemes üríteni a korábbi adatokat
            adatok.clear();
            betolt(fajl);
            listazHonap();
        }
    }

    private void betolt(File fajl) {
        Scanner be = null;
        try {
            be = new Scanner(fajl, "utf-8"); // Kisbetűs "utf-8" a biztosabb
            while(be.hasNextLine()) {
                String sor = be.nextLine();
                if (!sor.isEmpty()) { // Védelem az üres sorok ellen
                    adatok.add(new Adat(sor));
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (be != null) {
                be.close();
            }
        }
    }

    @FXML private void listazHonap() {
        // Ha még nem töltöttünk be adatot, ne fusson le hibára
        if (adatok.isEmpty()) return;

        int honap = cmHonap.getSelectionModel().getSelectedIndex() + 1;
        TreeMap<Integer, ArrayList<String>> map = new TreeMap<>();

        for (Adat adat : adatok) {
            for (String datum : adat.napok) {
                // Biztonsági ellenőrzés, hogy a dátum formátuma megfelelő-e (pl. "0412")
                if (datum.length() >= 4) {
                    int ho = Integer.parseInt(datum.substring(0, 2));
                    int nap = Integer.parseInt(datum.substring(2, 4));

                    if (honap == ho) {
                        if (!map.containsKey(nap)) {
                            map.put(nap, new ArrayList<>());
                        }
                        map.get(nap).add(adat.nev);
                    }
                }
            }
        }

        lista.getItems().clear();
        for (Integer nap : map.keySet()) {
            lista.getItems().add(String.format("%02d.%02d. - %s", honap, nap, String.join(", ", map.get(nap))));
        }
    }

    @FXML private void Kilepes() {
        Platform.exit();
    }

    @FXML private void Nevjegy() {
        Alert nevjegy = new Alert(Alert.AlertType.INFORMATION);
        nevjegy.setTitle("Névjegy");
        nevjegy.setHeaderText(null); // Szebb megjelenés (nincs üres szürke sáv)
        nevjegy.setContentText("Névnap v1.0\n(C) Kandó");
        nevjegy.showAndWait();
    }
}