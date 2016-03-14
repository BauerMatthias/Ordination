package gui;

import dao.DAO;
import dao.DAO_Impl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Behandlung_Beschreibung;

import java.net.URL;
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
        // Initialize choicebox to show all patients for the current behandlung
        initializePatientenChoicebox();
        initializeBeschreibungChoicebox();
    }

    private void initializeBeschreibungChoicebox() {
        if(this.cb_beschreibung.getItems().size() > 0){
            this.cb_beschreibung.getItems().remove(0, cb_beschreibung.getItems().size());
        }
        this.cb_beschreibung.setItems(FXCollections.observableArrayList(Behandlung_Beschreibung.values()));

        ChangeListener beschreibung_listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("im change listener");
                if (cb_beschreibung.getSelectionModel().getSelectedItem().equals("sonstiges")) {
                    tf_beschreibung_sonstiges.setVisible(true);
                } else {
                    tf_beschreibung_sonstiges.setVisible(false);
                }
            }
        };

        // TODO change listener
        this.cb_beschreibung.selectionModelProperty().addListener(beschreibung_listener);
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
}