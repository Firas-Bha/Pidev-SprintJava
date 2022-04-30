/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.controllers.GestionEvController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class Send_EmailController implements Initializable {

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
    private JFXTextArea content_TF;
    @FXML
    private JFXTextField destinataire_TF;
    @FXML
    private JFXTextField objet_TF;
    @FXML
    private Button Envoyer;
    @FXML
    private JFXButton retour;
    @FXML
    private Label lblClose;
    @FXML
    private JFXButton attach;
    String Filename;
    String destinataire;
    String objet;
    String content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToEv(ActionEvent event) {
    }

    @FXML
    private void goToRes(ActionEvent event) {
    }

    @FXML
    private void send(ActionEvent event) throws MessagingException, IOException {
        Evenement ev = new Evenement();
        //objet_TF.setText("Confirmation Réservation");
      // content_TF.setText("Bonjour Mr/Mme, votre réservation  le  " + GestionEvController.selectionedEvenement.+ " a été bien effectué Merci d'avoir participer à notre évenement,");
        
        if (destinataire_TF.getText().isEmpty() || objet_TF.getText().isEmpty()|| content_TF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }else if(destinataire_TF.getText().contains("@")==false){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("@ is messing");
            alert.showAndWait();
        }else if(destinataire_TF.getText().contains(".")==false){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(". is messing");
            alert.showAndWait();
        }else{
        
        destinataire=destinataire_TF.getText();
        objet=objet_TF.getText();
        content=content_TF.getText();
        
         Properties properties = new Properties();
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", 587);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.transport.protocl", "smtp");
    Session session = Session.getInstance(properties,new Authenticator(){
        
        @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("xtremegym69@gmail.com","Firas1997");

    }
    });
    Message message = new MimeMessage(session);
    message.setSubject(objet);
    Address addressTo=new InternetAddress(destinataire);
    message.setRecipient (Message.RecipientType. TO, addressTo);
    MimeMultipart multipart = new MimeMultipart();
    
    MimeBodyPart attachment = new MimeBodyPart();
    attachment.attachFile(new File(Filename));
    
    
    
    MimeBodyPart messageBodyPart=new MimeBodyPart();
    messageBodyPart.setContent(content,"text/html");
                              
    multipart.addBodyPart (messageBodyPart);
    multipart.addBodyPart(attachment);
    
    message.setContent(multipart);
    Transport.send(message);
    
        }    
    }

    @FXML
    private void attach(ActionEvent event) {
        JFileChooser chooser=new JFileChooser();
    
    chooser.showOpenDialog(null);
    File F=chooser.getSelectedFile();
     Filename=F.getAbsolutePath();
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../gui/GestionEv.fxml"));
            Parent root = loader.load();
            objet_TF.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionEvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
