package gui;

import dao.DAO;
import dao.DAO_Impl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import model.*;
import gui.DateAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Erstellt von Matthias Bauer am 03.03.2016.
 */

public class Controller implements Initializable{


    DAO dao = new DAO_Impl();

    @FXML
    public ToggleGroup group1;

    @FXML
    public RadioButton rbtn_stat_Pat;
    @FXML
    public RadioButton rbtn_stat_date;

    @FXML
    public Pane pane_statistikbyPatient;
    @FXML
    public Pane pane_statistikbyDate;

    @FXML
    public ChoiceBox cb_patient;
    @FXML
    public ChoiceBox cb_beschreibung;
    @FXML
    public ChoiceBox cb_patient_bearb_loesch;


    @FXML
    public DatePicker dc_datum;
    @FXML
    public DatePicker dc_gebdatum_anlegen;
    @FXML
    public DatePicker dc_gebdatum_bearb;
    @FXML
    public DatePicker dp_fromDate;
    @FXML
    public DatePicker dp_toDate;
    @FXML
    public DatePicker dp_toDate_datumsstatistik;
    @FXML
    public DatePicker dp_fromDate_datumsstatistik;

    @FXML
    public TextField tf_dauer;
    @FXML
    public TextField tf_einnahme;
    @FXML
    public TextField tf_vname_anlegen;
    @FXML
    public TextField tf_nname_anlegen;
    @FXML
    public TextField tf_adresse_anlegen;
    @FXML
    public TextField tf_telnr_anlegen;
    @FXML
    public TextField tf_tarif_anlegen;
    @FXML
    public TextField tf_tarif_bearb;
    @FXML
    public TextField tf_telnr_bearb;
    @FXML
    public TextField tf_adresse_bearb;
    @FXML
    public TextField tf_nname_bearb;
    @FXML
    public TextField tf_vname_bearb;
    @FXML
    public TextField tf_beschreibung_sonstiges;
    @FXML
    public TextField tf_addKrankheit;
    @FXML
    public TextField tf_changeKrankheit;

    @FXML
    public TextArea ta_bemerkung;

    @FXML
    public Button btn_beh_speichern;
    @FXML
    public Button btn_pat_anlegen_speichern;
    @FXML
    public Button btn_pat_bearb_speichern;
    @FXML
    public Button btn_pfeilRechts;
    @FXML
    public Button btn_pfeilLinks;
    @FXML
    public Button btn_delete_patient;
    @FXML
    public Button btn_addKrankheit;
    @FXML
    public Button btn_changeKrankheit;
    @FXML
    public Button btn_deleteKrankheit;
    @FXML
    public Button btn_showStatistik;
    @FXML
    public Button btn_datumsstatistik;

    @FXML
    public ListView lv_krankheiten_anlegen;
    @FXML
    public ListView lv_krankheiten_bearb;
    @FXML
    public ListView lv_krankheiten_bearb_right;
    @FXML
    public ListView lv_statistikPatienten;

    @FXML
    public TableView<Patient> tv_anzeigeAllePatienten = new TableView<Patient>();

    @FXML
    public TableView<Krankheit> tv_alleKrankheiten = new TableView<Krankheit>();

    @FXML
    public LineChart lc_statistik;

    @FXML
    private DateAxis xAxis = new DateAxis();

    @FXML
    private Rectangle rect;

    @FXML
    public Pane p_statistic;

    @FXML
    public Label lbl_gesamtWert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Changelistener für sonstiges bei Beschreibung der Behandlungsart

        cb_beschreibung.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(cb_beschreibung.getSelectionModel().getSelectedIndex() == 9){
                    tf_beschreibung_sonstiges.setVisible(true);
                } else {
                    tf_beschreibung_sonstiges.setVisible(false);
                }
            }
        });

        cb_patient.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Patient currentPatient = null;
                int i = cb_patient.getSelectionModel().getSelectedIndex();
                currentPatient = (Patient) cb_patient.getItems().get(i);
                if(currentPatient != null){
                    tf_einnahme.setTooltip(new Tooltip("Der Tarif für diesen Patient ist: " + dao.getTarifByPatient(currentPatient)));
                } else {
                    System.out.println("kein Patient gewählt.");
                }
            }
        });

        cb_patient_bearb_loesch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Patient chosen = (Patient) cb_patient_bearb_loesch.getItems().get(newValue.intValue());

                if(chosen != null) {
                    initializePatientBearbeitenKrankheitenListView(chosen);
                    fillBearbeitenPatient(chosen);

                }
            }
        });

        tv_alleKrankheiten.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(tv_alleKrankheiten.getColumns().size() > 0) {
                    Krankheit chosen = (Krankheit) tv_alleKrankheiten.getItems().get(newValue.intValue());

                    if (chosen != null) {
                        tf_changeKrankheit.setText(chosen.getBeschreibung());
                    }
                }
            }
        });

        // Initialize choicebox/listviews
        initializePatientenChoicebox();
        initializeBeschreibungChoicebox();
        initializeKrankheitenListView();
        initializePatientBearbeitenChoicebox();
        initializePatientBearbeitenKrankheitenListView(null);
        initializeTableviewPatient();
        initializeTableviewKrankheiten();
        initializePatientenListView();
    }

    private void initializePatientenListView() {
        if(this.lv_statistikPatienten.getItems().size() > 0){
            this.lv_statistikPatienten.getItems().remove(0, lv_statistikPatienten.getItems().size());
        }
        this.lv_statistikPatienten.setItems(FXCollections.observableArrayList(dao.getAllPatienten()));
        this.lv_statistikPatienten.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lv_statistikPatienten.setTooltip(new Tooltip("Multiauswahl: STRG (halten) und mit Maus auswählen."));
    }

    private void initializeTableviewKrankheiten() {
        if(tv_alleKrankheiten.getColumns().size() == 0){
            tv_alleKrankheiten.setEditable(true);

            TableColumn beschreibungColumn = new TableColumn("Beschreibung");
            beschreibungColumn.setMinWidth(400);
            beschreibungColumn.setResizable(true);
            beschreibungColumn.setCellValueFactory(new PropertyValueFactory<Krankheit, String>("beschreibung"));
            beschreibungColumn.setEditable(true);

            tv_alleKrankheiten.getColumns().add(beschreibungColumn);
            tv_alleKrankheiten.getItems().addAll(dao.getAllKrankheiten());
        } else {
            tv_alleKrankheiten.getColumns().remove(0, tv_alleKrankheiten.getColumns().size());
            tv_alleKrankheiten.getItems().remove(0, tv_alleKrankheiten.getItems().size());

            tv_alleKrankheiten.setEditable(true);

            TableColumn beschreibungColumn = new TableColumn("Beschreibung");
            beschreibungColumn.setMinWidth(400);
            beschreibungColumn.setResizable(true);
            beschreibungColumn.setCellValueFactory(new PropertyValueFactory<Krankheit, String>("beschreibung"));
            beschreibungColumn.setEditable(true);

            tv_alleKrankheiten.getColumns().add(beschreibungColumn);
            tv_alleKrankheiten.getItems().addAll(dao.getAllKrankheiten());
        }


    }

    private void initializeTableviewPatient() {
        tv_anzeigeAllePatienten.setEditable(false);

        if(tv_anzeigeAllePatienten.getColumns().size() == 0){

            // Set Tooltip
            tv_anzeigeAllePatienten.setRowFactory(tv -> new TableRow<Patient>() {
                private Tooltip tooltip = new Tooltip();
                @Override
                public void updateItem(Patient pat, boolean empty) {
                    super.updateItem(pat, empty);
                    if (pat == null) {
                        setTooltip(null);
                    } else {
                        ArrayList<Krankheit> krankheiten = dao.getAllKrankheitenByPatient(pat);

                        String tooltipText = "";

                        if(krankheiten.size() == 0){
                            tooltipText = "keine Krankheiten gespeichert.";
                        } else {
                            for (Krankheit k : krankheiten) {
                                tooltipText += k + "\n";
                            }
                        }

                        tooltip.setText(tooltipText);
                        setTooltip(tooltip);
                    }
                }
            });

            TableColumn vnameColumn = new TableColumn("Vorname");
            vnameColumn.setResizable(true);
            vnameColumn.setMinWidth(100);

            TableColumn nnameColumn = new TableColumn("Nachname");
            nnameColumn.setResizable(true);
            nnameColumn.setMinWidth(100);

            TableColumn adresseColumn = new TableColumn("Adresse");
            adresseColumn.setResizable(true);
            adresseColumn.setMinWidth(200);

            TableColumn gebDatumColumn = new TableColumn("Geburtsdatum");
            gebDatumColumn.setResizable(true);
            gebDatumColumn.setMinWidth(55);

            TableColumn tarifColumn = new TableColumn("Tarif");
            tarifColumn.setResizable(true);
            tarifColumn.setMinWidth(50);


            vnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("vorname"));
            nnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("nachname"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("adresse"));
            gebDatumColumn.setCellValueFactory(new PropertyValueFactory<Patient, LocalDate>("gebDatum"));
            tarifColumn.setCellValueFactory(new PropertyValueFactory<Patient, Double>("tarif"));


            tv_anzeigeAllePatienten.getColumns().addAll(vnameColumn, nnameColumn, adresseColumn, gebDatumColumn, tarifColumn);

            tv_anzeigeAllePatienten.setItems(FXCollections.observableArrayList(dao.getAllPatienten()));
        }
    }

    private void initializePatientBearbeitenKrankheitenListView(Patient p) {
        if(this.lv_krankheiten_bearb.getItems().size() > 0){
            this.lv_krankheiten_bearb.getItems().remove(0, lv_krankheiten_bearb.getItems().size());
        }
        if(this.lv_krankheiten_bearb_right.getItems().size() > 0){
            this.lv_krankheiten_bearb_right.getItems().remove(0, lv_krankheiten_bearb_right.getItems().size());
        }

        if(p != null) {
            ArrayList<Krankheit> kAllList = dao.getAllKrankheiten();
            ArrayList<Krankheit> kPatList = dao.getAllKrankheitenByPatient(p);
            this.lv_krankheiten_bearb.setItems(FXCollections.observableArrayList(kPatList));

            ArrayList<Krankheit> list = new ArrayList<>();
            for(Krankheit k : kAllList){
                if(!kPatList.contains(k)){
                    list.add(k);
                }
            }

            this.lv_krankheiten_bearb_right.setItems(FXCollections.observableArrayList(list));

        } else if(p == null){
            if(cb_patient_bearb_loesch.getItems().size() == 0) {
                this.lv_krankheiten_bearb.setItems(FXCollections.observableArrayList(dao.getAllKrankheiten()));

                this.lv_krankheiten_bearb_right.getItems().remove(0, lv_krankheiten_bearb_right.getItems().size());
            } else {
                initializePatientBearbeitenKrankheitenListView((Patient) cb_patient_bearb_loesch.getItems().get(0));
            }
        }
    }

    private void fillBearbeitenPatient(Patient p){
        tf_vname_bearb.setText(p.getVorname());
        tf_nname_bearb.setText(p.getNachname());
        tf_adresse_bearb.setText(p.getAdresse());
        dc_gebdatum_bearb.setValue(p.getGebDatum());
        tf_telnr_bearb.setText(p.getTelNummer());
        tf_tarif_bearb.setText(Double.toString(p.getTarif()));
    }

    private void initializePatientBearbeitenChoicebox() {
        if(cb_patient_bearb_loesch.getItems().size() > 0){
            this.cb_patient_bearb_loesch.getItems().remove(0, cb_patient_bearb_loesch.getItems().size());
        }
        this.cb_patient_bearb_loesch.setItems(FXCollections.observableArrayList(dao.getAllPatienten()));

        ArrayList<Patient> pList = dao.getAllPatienten();

        this.cb_patient_bearb_loesch.setItems(FXCollections.observableArrayList(pList));
        fillBearbeitenPatient(pList.get(0));
    }

    private void initializeKrankheitenListView() {
        if(this.lv_krankheiten_anlegen.getItems().size() > 0){
            this.lv_krankheiten_anlegen.getItems().remove(0, lv_krankheiten_anlegen.getItems().size());
        }
        this.lv_krankheiten_anlegen.setItems(FXCollections.observableArrayList(dao.getAllKrankheiten()));
        this.lv_krankheiten_anlegen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lv_krankheiten_anlegen.setTooltip(new Tooltip("Multiauswahl: STRG (halten) und mit Maus auswählen."));
    }

    private void initializeBeschreibungChoicebox() {
        if(this.cb_beschreibung.getItems().size() > 0){
            this.cb_beschreibung.getItems().remove(0, cb_beschreibung.getItems().size());
        }
        this.cb_beschreibung.setItems(FXCollections.observableArrayList(Behandlung_Beschreibung.values()));
    }

    private void initializePatientenChoicebox() {
        if(this.cb_patient.getItems().size() > 0){
            this.cb_patient.getItems().remove(0, cb_patient.getItems().size());
        }
        this.cb_patient.setItems(FXCollections.observableArrayList(dao.getAllPatienten()));

        if(this.cb_patient_bearb_loesch.getItems().size() > 0){
            this.cb_patient_bearb_loesch.getItems().remove(0, cb_patient_bearb_loesch.getItems().size());
        }
        this.cb_patient_bearb_loesch.setItems(FXCollections.observableArrayList(dao.getAllPatienten()));

    }

    public void saveBehandlung(ActionEvent actionEvent) {

        Behandlung save = new Behandlung();

        save.setPatient((Patient) cb_patient.getValue());
        save.setDatum(dc_datum.getValue());

        String beschreibung = "";

        if(cb_beschreibung.getValue().equals(Behandlung_Beschreibung.sonstiges)){
            save.setBeschreibung(tf_beschreibung_sonstiges.getText());
        } else {
            Behandlung_Beschreibung b = (Behandlung_Beschreibung) cb_beschreibung.getValue();
            save.setBeschreibung(b.name());
        }

        Double chosenEinnahme = 0.0;

        try {
            save.setEinnahme(Double.valueOf(tf_einnahme.getText()));
        } catch (NumberFormatException ex){
            System.err.println("keine Double eingabe in Einnahme.");
        }

        save.setBemerkung(ta_bemerkung.getText());

        ButtonType type = createDialog(Alert.AlertType.CONFIRMATION, "Speichern der Behandlung", null, "Willst du diese Behandlung speichern?");

        boolean ret = false;

        if(type == ButtonType.OK){
            ret = dao.createBehandlung(save);
        } else if(type == ButtonType.CANCEL){
            ret = dao.createBehandlung(save);
        }

        if(ret){
            createDialog(Alert.AlertType.INFORMATION, null, null, "Behandlung wurde erfolgreich gespeichert.");
        } else {
            createDialog(Alert.AlertType.INFORMATION, null, null, "Behandlung konnte nicht gespeichert werden.");
        }
    }

    public void savePatient(ActionEvent event) {
        Patient p = new Patient();
        p.setAdresse(tf_adresse_anlegen.getText());
        boolean fehler = false;
        try {
            p.setGebDatum(dc_gebdatum_anlegen.getValue());
        } catch (Exception e){
            createDialog(Alert.AlertType.ERROR, null, null, "Bitte ein Gültiges Datum auswählen.");
        }
        p.setTelNummer(tf_telnr_anlegen.getText());
        if(tf_tarif_anlegen.getText() != null || tf_vname_anlegen.equals("") || tf_nname_anlegen.equals("")) {
            p.setVorname(tf_vname_anlegen.getText());
            p.setNachname(tf_nname_anlegen.getText());
            try {
                p.setTarif(Double.valueOf(tf_tarif_anlegen.getText()));
            } catch (NumberFormatException ex){
                createDialog(Alert.AlertType.ERROR, null, null, "Vorname/Nachname/Tarif sind Pflichtfelder. Der Tarif muss ein Zahlenwert sein!!");
                fehler = true;
            }
        } else {
            p.setTarif(0.0);
        }

        ObservableList<Krankheit> krankList = (ObservableList<Krankheit>)this.lv_krankheiten_anlegen.getSelectionModel().getSelectedItems();
        ArrayList<Krankheit> krankheitenListe = new ArrayList<>();
        for(Krankheit k : krankList){
            krankheitenListe.add(k);
        }

        p.setKrankheiten(krankheitenListe);

        boolean ret = false;
        boolean saveKrankheiten = false;
        if(!fehler) {
            ret = dao.createPatient(p);
            saveKrankheiten = dao.saveAllKrankheitenByPatient(p, p.getKrankheiten());
        }

        if(ret && saveKrankheiten){
            createDialog(Alert.AlertType.INFORMATION, null, null, "Patient wurde erfolgreich gespeichert.");
            initializeTableviewPatient();
            initializePatientBearbeitenChoicebox();
        } else {
            createDialog(Alert.AlertType.ERROR, null, null, "Patient konnte nicht gespeichert werden.");
        }

    }

    public ButtonType createDialog(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            return ButtonType.OK;
        } else {
            return ButtonType.CANCEL;
        }
    }

    public void deletePatient(ActionEvent actionEvent) {

        Patient chosenPatient = (Patient) cb_patient_bearb_loesch.getValue();

        boolean fehler = false;

        if(chosenPatient != null){
            fehler = dao.deletePatient(chosenPatient);
        }

        if(!fehler){
            createDialog(Alert.AlertType.ERROR, null, null, "Patient konnte nicht gelöscht werden.");
        } else {
            createDialog(Alert.AlertType.INFORMATION, null, null, "Patient erfolgreich gelöscht.");
        }

    }

    public void deselectKrankheittoPatient(ActionEvent actionEvent) {
        ObservableList<Krankheit> chosenKrankheiten = null;
        Patient chosenPatient = null;
        if(lv_krankheiten_bearb.getSelectionModel().getSelectedItems().size() > 0 && cb_patient_bearb_loesch.getItems() != null){
            chosenKrankheiten = lv_krankheiten_bearb.getSelectionModel().getSelectedItems();
            chosenPatient = (Patient) cb_patient_bearb_loesch.getValue();

            ObservableList<Krankheit> nicht_ausgewaehlt = lv_krankheiten_bearb_right.getItems();
            for(Krankheit k : chosenKrankheiten){
                nicht_ausgewaehlt.add(k);
                lv_krankheiten_bearb_right.setItems(nicht_ausgewaehlt);
                lv_krankheiten_bearb.getItems().remove(k);
            }
        }
    }

    public void selectKrankheitToPatient(ActionEvent actionEvent) {
        ObservableList<Krankheit> chosenKrankheiten = null;
        Patient chosenPatient = null;
        if(lv_krankheiten_bearb_right.getSelectionModel().getSelectedItems().size() > 0 && cb_patient_bearb_loesch.getItems() != null){
            chosenKrankheiten = lv_krankheiten_bearb_right.getSelectionModel().getSelectedItems();
            chosenPatient = (Patient) cb_patient_bearb_loesch.getValue();

            ObservableList<Krankheit> ausgewaehlt = lv_krankheiten_bearb.getItems();
            for(Krankheit k : chosenKrankheiten){
                ausgewaehlt.add(k);
                lv_krankheiten_bearb.setItems(ausgewaehlt);
                lv_krankheiten_bearb_right.getItems().remove(k);
            }
        }
    }

    public void saveBearbeitetenPatienten(ActionEvent actionEvent) {

        // TODO
    }

    public void saveKrankheit(ActionEvent actionEvent) {
        boolean save = false;

        if(tf_addKrankheit.getText() != null){
            Krankheit k = new Krankheit();
            k.setBeschreibung(tf_addKrankheit.getText());

            save = dao.saveKrankheit(k);
        }

        if(save){
            createDialog(Alert.AlertType.INFORMATION, null, null, "Krankheit erfolgreich gespeichert.");
            initializeTableviewKrankheiten();
            initializeKrankheitenListView();
            tf_addKrankheit.setText("");
        } else {
            createDialog(Alert.AlertType.ERROR, null, null, "Krankheit konnte nicht gespeichert werden.");
        }
    }

    public void changeKrankheit(ActionEvent actionEvent) {
        if(tf_changeKrankheit.getText() == ""){
            createDialog(Alert.AlertType.ERROR, null, null, "Bitte einen Namen für die Krankheit eingeben.");
        } else {
            Krankheit k = dao.getKrankheitbyBeschreibung(tv_alleKrankheiten.getSelectionModel().getSelectedItem().getBeschreibung());
            k.setBeschreibung(tf_changeKrankheit.getText());

            boolean change = dao.updateKrankheit(k);

            if(change){
                createDialog(Alert.AlertType.INFORMATION, null, null, "Krankheit wurde bearbeitet");
                initializeKrankheitenListView();
                initializeTableviewKrankheiten();
                tf_changeKrankheit.setText("");
            } else {
                createDialog(Alert.AlertType.ERROR, null, null, "Krankheit konnte nicht bearbeitet werden.");
            }
        }
    }

    public void deleteKrankheit(ActionEvent actionEvent) {
        if(tv_alleKrankheiten.getSelectionModel().getSelectedItem().equals(null)){
            createDialog(Alert.AlertType.INFORMATION, null, null, "Bitte wähle eine Krankheit aus der Liste aus!");
        } else {
            Krankheit k = dao.getKrankheitbyBeschreibung(tv_alleKrankheiten.getSelectionModel().getSelectedItem().getBeschreibung());

            boolean delete = dao.deleteKrankheit(k);

            if(delete){
                createDialog(Alert.AlertType.INFORMATION, null, null, "Krankheit wurde gelöscht.");
                initializeKrankheitenListView();
                initializeTableviewKrankheiten();
                tf_changeKrankheit.setText("");
            } else {
                createDialog(Alert.AlertType.ERROR, null, null, "Krankheit konnte nicht gelöscht werden.");
            }
        }
    }

    public void viewPanePatient(ActionEvent actionEvent) {
        System.out.println("view patienten pane");
    }

    public void viewPaneDate(ActionEvent actionEvent) {
        System.out.println("view date pane");
    }

    public void showStatistik(ActionEvent actionEvent) {

        if(this.lv_statistikPatienten.getSelectionModel().getSelectedItems().size() == 0){
            createDialog(Alert.AlertType.ERROR, null, null, "Bitte mindestens einen Patienten auswählen.");
        }else if(this.dp_fromDate.getValue() == null || this.dp_toDate.getValue() == null){
            ObservableList<Patient> patList = (ObservableList<Patient>)this.lv_statistikPatienten.getSelectionModel().getSelectedItems();
            ArrayList<Patient> patientenListe = new ArrayList<>();

            LocalTime lt = LocalTime.of(0, 0);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy");

            for(Patient p : patList) {
                DateAxis xAxisDate = getDateAxis(dtf);
                NumberAxis yAxisMoney = new NumberAxis();
                yAxisMoney.setLowerBound(0.0);
                yAxisMoney.setUpperBound(100.0);

                LineChart<LocalDateTime, Number> lc_statistic = new LineChart<LocalDateTime, Number>(xAxisDate, yAxisMoney);

                XYChart.Series<LocalDateTime, Number> seriePerson = new XYChart.Series<>();
                lc_statistik = new LineChart<LocalDateTime, Number>(xAxisDate, yAxisMoney);

                lc_statistik.setTitle("Statistik der ausgewählten Personen");

                lc_statistik.setMinHeight(500);
                lc_statistik.setCreateSymbols(false);

                ArrayList<Statistik_Daten> patientenDaten = dao.getStatistikByPatient(p, null, null);

                for(int i = 0; i < patientenDaten.size(); i++){
                    if(patientenDaten.get(i) != null){
                        seriePerson.getData().add(new XYChart.Data<>(patientenDaten.get(i).getDate(), patientenDaten.get(i).getWert()));
                    }
                    if(i == 0){
                        xAxisDate.setLowerBound(patientenDaten.get(i).getDate().minusDays(1));
                    }
                    if(i == patientenDaten.size()){
                        xAxisDate.setUpperBound(patientenDaten.get(i).getDate().plusDays(1));
                    }
                }

                seriePerson.setName(p.getVorname() + " " + p.getNachname());
                lc_statistik.getData().add(seriePerson);
                lc_statistic.setCreateSymbols(false);
                p_statistic.getChildren().add(lc_statistik);
                lc_statistik.setVisible(true);
            }
        } else if(this.dp_fromDate.getValue() != null || this.dp_toDate.getValue() != null){
            ObservableList<Patient> patList = (ObservableList<Patient>)this.lv_statistikPatienten.getSelectionModel().getSelectedItems();
            ArrayList<Patient> patientenListe = new ArrayList<>();

            // TODO
        } else if(!this.dp_fromDate.getValue().isBefore(this.dp_toDate.getValue())) {
            createDialog(Alert.AlertType.ERROR, null, null, "Von-Datum muss vor dem Bis-Datum liegen.");
        } else {
            createDialog(Alert.AlertType.ERROR, null, null, "Ein unbekannter Fehler bei der Erstellung der Statistik ist aufgetreten.");
        }
    }

    private DateAxis getDateAxis(final DateTimeFormatter dtf) {
        DateAxis xAxisTemporary = new DateAxis();
        xAxisTemporary.setAutoRanging(false);
        xAxisTemporary.setTickLabelFormatter(new StringConverter<LocalDateTime>() {
            @Override
            public String toString(LocalDateTime object) {
                return object.format(dtf);
            }

            @Override
            public LocalDateTime fromString(String string) {
                return null;
            }
        });
        xAxisTemporary.lowerBoundProperty().bindBidirectional(xAxis.lowerBoundProperty());
        xAxisTemporary.upperBoundProperty().bindBidirectional(xAxis.upperBoundProperty());
        return xAxisTemporary;
    }

    public void showDatumsstatistik(ActionEvent actionEvent) {
        if (this.dp_fromDate_datumsstatistik.getValue() != null && this.dp_toDate_datumsstatistik.getValue() != null) {
            if (this.dp_fromDate_datumsstatistik.getValue().isBefore(this.dp_toDate_datumsstatistik.getValue())) {

                p_statistic.getChildren().remove(0, p_statistic.getChildren().size());

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                Map<Patient, Double> patListEinnahmen = new HashMap<>();
                double gesamtWert = 0.0;

                ArrayList<Statistik_Daten> daten = dao.getStatistikByPatient(null, dp_fromDate_datumsstatistik.getValue(), dp_toDate_datumsstatistik.getValue());

                if (daten.size() != 0) {
                    for (Statistik_Daten d : daten) {
                        if (patListEinnahmen.containsKey(d.getP())) {
                            double wert = patListEinnahmen.get(d.getP());
                            patListEinnahmen.remove(d.getP());
                            patListEinnahmen.put(d.getP(), wert + d.getWert());
                        } else if (!patListEinnahmen.containsKey(d.getP())) {
                            patListEinnahmen.put(d.getP(), d.getWert());
                        }
                        gesamtWert += d.getWert();
                    }

                    Set<Patient> keySet = patListEinnahmen.keySet();

                    for (Patient p : keySet) {
                        pieChartData.add(new PieChart.Data(p.toString() + ": " + patListEinnahmen.get(p) + "€", patListEinnahmen.get(p)));
                    }

                    final PieChart piechart = new PieChart(pieChartData);
                    piechart.setTitle("Einnahmen im Zeitraum: " + dp_fromDate_datumsstatistik.getValue() + " bis " + dp_toDate_datumsstatistik.getValue());

                    p_statistic.getChildren().add(piechart);
                    lbl_gesamtWert.setText("Du hast in diesem Zeitraum " + gesamtWert + "€ eingenommen.");
                } else if (daten.size() == 0) {
                    final PieChart piechart = new PieChart(pieChartData);
                    piechart.setTitle("Keine Daten für diesen Zeitraum gefunden.");

                    p_statistic.getChildren().add(piechart);
                }


            } else {
                createDialog(Alert.AlertType.ERROR, null, null, "Von-Datum muss vor dem Bis-Datum liegen.");
            }
        } else {
            createDialog(Alert.AlertType.ERROR, null, null, "Ein unbekannter Fehler bei der Erstellung der Statistik ist aufgetreten.");
        }
    }
}