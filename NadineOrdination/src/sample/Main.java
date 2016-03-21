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
        launch(args);
    }
}
