/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class CaloriesController implements Initializable {

    @FXML
    private Button evicon;
    @FXML
    private Button resicon;
    @FXML
    private Circle shpActive;
    @FXML
    private Label lblActive;
    @FXML
    private Label lblOffline;
    @FXML
    private Label lblExp;
    @FXML
    private Label lblExpired;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl30;
    @FXML
    private Label lbl90;
    @FXML
    private JFXButton retour;
    @FXML
    private Button calculer;
    @FXML
    private Label imc;
    @FXML
    private JFXComboBox<String> niveaucomb;
    @FXML
    private JFXComboBox<String> genrecomb;
    @FXML
    private JFXTextField taille_t;
    @FXML
    private JFXTextField age_t;
    @FXML
    private JFXTextField poids_t;
    @FXML
    private Label lblClose;
    private String genre;
    private String niveau;
    private float poids;
    private float taille;
    private float age;
    private float calories;
    private float test;
    @FXML
    private Label resultat_l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ObservableList<String> list_g=FXCollections.observableArrayList ("Homme", "Femme"); 
        genrecomb.setItems (list_g) ;
        ObservableList<String> list_n=FXCollections.observableArrayList ("sédentaire", "activité physique légère","activité physique modérée","activité physique intense"); 
        niveaucomb.setItems (list_n) ;
    }    

    @FXML
    private void goToEv(ActionEvent event) {
    }

    @FXML
    private void goToRes(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../gui/GestionRes.fxml"));
            Parent root = loader.load();
            age_t.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionResController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void calcul_calories(ActionEvent event) {
        
         if (poids_t.getText().isEmpty() || taille_t.getText().isEmpty()|| age_t.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
    
    
    // d'apres la formule de Harris revue par Roza et Shizgall (1994) 
        poids= Float.parseFloat(poids_t.getText());
       taille=Float.parseFloat(taille_t.getText());
       age=Float.parseFloat(age_t.getText()); 
       if(genre=="Homme"){
       test=(float) ((float) 13.707*poids + 492.3*taille*0.01 - 6.673 * age + 77.607);
       //13,707 x Poids(kg) + 492,3 x Taille(m) – 6,673 x Age(années) + 77,607
       }else if(genre=="Femme"){
       test=(float) ((float) 9.740*poids + 172.9*taille*0.01 - 4.737 * age + 667.051);
       //9,740 x Poids(kg) + 172,9 x Taille(m) – 4,737 x Age(années) + 667,051
       }
       if(niveau=="sédentaire"){
           calories=(float) (test*1.375);
       }else if(niveau=="activité physique légère"){
           calories=(float) (test*1.56);
       }else if(niveau=="activité physique modérée"){
        calories=(float) (test*1.64);
       }else if(niveau=="activité physique intense"){
        calories=(float) (test*1.82);
       }
       resultat_l.setText("votre calories est: "+String.valueOf(round(calories)));
    System.out.println(test);
    System.out.println(round(calories));
    }
    }

    @FXML
    private void getniveau(ActionEvent event) {
        niveau=niveaucomb.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void getgenre(ActionEvent event) {
         genre=genrecomb.getSelectionModel().getSelectedItem();
    }
    
     @FXML
    private void taille_only_n(KeyEvent event) {
        return_number(taille_t);
    }

    @FXML
    private void poids_only_n(KeyEvent event) {
        return_number(poids_t);
    }

    @FXML
    private void age_only_n(KeyEvent event) {
        return_number(age_t);
    }
      private void return_number(TextField t){
    t.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            t.setText(newValue.replaceAll("\\D+", "."));
        }
    }
});
}
}
