package gui;

import dao.DAO;
import dao.DAO_Impl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Behandlung;
import model.Behandlung_Beschreibung;
import model.Krankheit;
import model.Patient;

import javax.security.auth.callback.ConfirmationCallback;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Erstellt von Matthias Bauer am 03.03.2016.
 */

public class Controller implements Initializable{

    DAO dao = new DAO_Impl();

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
    public TextArea ta_bemerkung;

    @FXML
    public Button btn_beh_speichern;
    @FXML
    public Button btn_pat_anlegen_speichern;
    @FXML
    public Button btn_pat_bearb_speichern;

    @FXML
    public ListView lv_krankheiten_anlegen;
    @FXML
    public ListView lv_krankheiten_bearb;

    @FXML
    public TableColumn tv_anzeigeAllePatienten;

    @FXML
    public TableView tv_alleKrankheiten;


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
                    fillBearbeitenPatient(chosen);

                }
            }
        });

        // Initialize choicebox/listviews
        initializePatientenChoicebox();
        initializeBeschreibungChoicebox();
        initializeKrankheitenListView();
        initializePatientBearbeitenChoicebox();
    }

    private void fillBearbeitenPatient(Patient p){
        tf_vname_bearb.setText(p.getVorname());
        tf_nname_bearb.setText(p.getNachname());
        tf_adresse_bearb.setText(p.getAdresse());
        dc_gebdatum_bearb.setValue(p.getGebDatum());
        tf_telnr_bearb.setText(p.getTelNummer());
        tf_tarif_bearb.setText(Double.toString(p.getTarif()));
        selectKrankheitenbyPatient(p);
    }

    private void selectKrankheitenbyPatient(Patient p) {
        System.out.println("p: " + p);
        for(Krankheit k : p.getKrankheiten()){
            System.out.println(k);
        }

        for(Krankheit k : p.getKrankheiten()) {
            System.out.println("index: " + lv_krankheiten_bearb.getItems().indexOf(k));
        }
//        for(Krankheit k : p.getKrankheiten()){
//            lv_krankheiten_bearb.getSelectionModel().select(lv_krankheiten_bearb.getItems().indexOf(k));
//        }
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

        if(this.lv_krankheiten_bearb.getItems().size() > 0){
            this.lv_krankheiten_bearb.getItems().remove(0, lv_krankheiten_bearb.getItems().size());
        }
        this.lv_krankheiten_bearb.setItems(FXCollections.observableArrayList(dao.getAllKrankheiten()));
        this.lv_krankheiten_bearb.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lv_krankheiten_bearb.setTooltip(new Tooltip("Multiauswahl: STRG (halten) und mit Maus auswählen."));
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

        boolean ret = dao.createPatient(p);

        if(ret){
            createDialog(Alert.AlertType.INFORMATION, null, null, "Patient wurde erfolgreich gespeichert.");
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
}