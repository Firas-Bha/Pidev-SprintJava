/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.controllers.GestionEvController;
import app.controllers.GestionResController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class IMCController implements Initializable {

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
    private TextField taille;
    @FXML
    private TextField poids;
    @FXML
    private Button calculer;
    @FXML
    private Label lblClose;
    @FXML
    private Label imc;
    @FXML
    private Label etat_t;
    private float imc_C;
     private String etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calculer_imc(ActionEvent event) {
        if (poids.getText().isEmpty() || taille.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
   float t=Float.parseFloat(taille.getText());
   float m=Float.parseFloat(poids.getText());
   float x= m /(t*t);
   imc_C=roundfloat(x,2);
   if (imc_C<18.5){
   etat="Insuffisance pondérale:maigreur";
   }else if((18.5<imc_C)&&(imc_C<25)){
   etat="Corpulence normale";
   }else if((25<imc_C)&&(imc_C<30)){
   etat="Surpoids";
   }else if((30<imc_C)&&(imc_C<35)){
   etat="Obésité modérée";
   }else if((35<imc_C)&&(imc_C<40)){
   etat="Obésité sévère";
   }else if(40<imc_C){
   etat="Obésité morbide ou massive";}
    imc.setText("votre imc est: "+String.valueOf(imc_C));
    etat_t.setText(etat);
   System.out.println(imc_C);
        }
    }
    public static float roundfloat(float d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
    return bd.floatValue();
}

    @FXML
    private void taille_only_n(KeyEvent event) {
        return_number(taille);
    }

    @FXML
    private void poids_only_n(KeyEvent event) {
        return_number(poids);
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
            taille.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionResController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
