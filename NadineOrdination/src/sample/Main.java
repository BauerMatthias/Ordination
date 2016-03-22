package sample;

import dao.DAO;
import dao.DAO_Impl;
import dao.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Behandlung_Beschreibung;
import model.Krankheit;
import model.Patient;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();

        DBConnector.closeConnection();
    }


    public static void main(String[] args) {
//        System.out.println("size: ");
//        DAO_Impl d = new DAO_Impl();
//        Patient p = new Patient();
//        p.setVorname("Bettina");
//        p.setNachname("Bauer");
//        p.setId(d.getIdFromPatient(p));
//        ArrayList<Krankheit> k = d.getAllKrankheitenByPatient(p);
//
//        for(Krankheit kr : k){
//            System.out.println(kr);
//        }

        launch(args);
    }
}
