### Névjegy
````
@FXML public void Nevjegy()
{
    Alert nevjegy = new Alert(Alert.AlertType.INFORMATION);
    nevjegy.setTitle("Névjegy");
    nevjegy.setContentText("Projekt v1.0.0\n(C) 2026");
    nevjegy.setHeaderText(null);

    // Title icon 
    ((Stage)nevjegy.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("icons/icon.png")));

    // Image
    nevjegy.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/icon.png"))));

    nevjegy.showAndWait();
}
````
### File chooser
````
private FileChooser saveChooser;
private FileChooser openChooser;

public void initialize()
{
    saveChooser = new FileChooser();
    saveChooser.setInitialDirectory(new File("./"));
    saveChooser.setInitialFileName("save");
    saveChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files", "*.txt"));
    saveChooser.setTitle("Mentés");

    openChooser = new FileChooser();
    openChooser.setInitialDirectory(new File("./"));
    openChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt lifes", "*.txt"));
    openChooser.setTitle("Megnyitás");
}
````
### File Open/Save
````
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

    @FXML private void onMentesClick() {
        mentes("listak.txt");
    }

    private void mentes(String fajlnev) {
        PrintWriter ki = null;
        try {
            ki = new PrintWriter( new File(fajlnev), "utf8");
            for (int i = 0; i < lista.getItems().size(); i++) {
                ki.printf("%s\r\n", lista.getItems().get(i));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (ki != null) {
                ki.close();
            }
        }
    }
````
