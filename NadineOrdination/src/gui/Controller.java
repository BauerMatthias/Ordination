package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Erstellt von Matthias Bauer am 03.03.2016.
 */

public class Controller {

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
}
