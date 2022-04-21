/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import app.controllers.GestionEvController;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import service.EvenementService;
import static entities.Evenement.filename;
import entities.Reservation;
import java.awt.SystemTray;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class GestionResController implements Initializable {
    public int id;
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
    private TextField nbrPerRes;
    @FXML
    private TableView<Reservation> affichageRes;
    @FXML
    private TableColumn<Reservation, String> idRestab;
    @FXML
    private TableColumn<Reservation, String> nomEvRestab;
    @FXML
    private TableColumn<Reservation, String> dateEvRestab;
    @FXML
    private TableColumn<Reservation, String> nbrPerRestab;
    @FXML
    private Button ajouterResbtn;
    @FXML
    private DatePicker dateRes;
    @FXML
    private Button modifierResbtn;
    @FXML
    private ComboBox<String> idEvRes;
    @FXML
    private Label lblClose;
    @FXML
    private Button supprimerResbtn;
    Random r = new Random();
    ObservableList <String>list_nom_Evenement;
    
    List <Evenement>list_Evenement;
    @FXML
    private TextField emailRes;
    @FXML
    private TableColumn<Evenement,String> emailRestab;
    private Date date_res;
    private int  event_id;
    private String nomEv;
    private int  id_event_select;
    @FXML
    private Button evicon;
    @FXML
    private Button resicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
         ReservationService rs = new ReservationService();
       //  EvenementService sp= new  EvenementService();

       /*
         
       try {
           
            idEvRes.setItems(FXCollections.observableArrayList(rs.listidEvRes()));
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionResController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        
      int i;
        EvenementService sp= new  EvenementService();
         list_Evenement = sp.recupererEvenement();       
       List<String>  col = new ArrayList<String>();
        for(Evenement si : list_Evenement ){
        col.add(si.getNom());
        }
        list_nom_Evenement = FXCollections.observableArrayList(col);
        idEvRes.setItems(list_nom_Evenement);
        
         affichageRes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                id = rs.recupererReservation()
                        .get(affichageRes.getSelectionModel().getSelectedIndex())
                        .getId();
                
                dateRes.setValue(LocalDate.parse(rs.recupererReservation()
                    .get(affichageRes.getSelectionModel()
                            .getSelectedIndex())
                    .getDate_res()

                ));
                idEvRes.setValue(String.valueOf(rs.recupererReservation()
                        .get(affichageRes.getSelectionModel().
                                getSelectedIndex())
                        .getNom()));
                
                
                 nbrPerRes.setText(String.valueOf(rs.recupererReservation()
                        .get(affichageRes.getSelectionModel().
                                getSelectedIndex())
                        .getNbr_personnes()));
                
                
                 emailRes.setText(rs.recupererReservation()
                        .get(affichageRes.getSelectionModel()
                                .getSelectedIndex())
                        .getEmail()
                ); 
    
               
                };
    
         }); 
       
        ObservableList<Reservation> recupererReservation ; 
        

            try {
                
                 recupererReservation =rs.getReservationliste();
            idRestab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomEvRestab.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            dateEvRestab.setCellValueFactory(new PropertyValueFactory<>("date_res"));
            nbrPerRestab.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
            
            emailRestab.setCellValueFactory(new PropertyValueFactory<>("email"));
            affichageRes.setItems(recupererReservation);
    
        } catch (SQLException ex) {
            Logger.getLogger(GestionResController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        // TODO

        //loadHash(pieHash);
       // loadHash(pieKey);
        lblClose.setOnMouseClicked(e -> System.exit(0));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
          //  loadHash(pieHash);
           // loadHash(pieKey);
            lblExpired.setText(String.valueOf(r.nextInt(11)));
            lbl7.setText(String.valueOf(r.nextInt(10)));
            lbl30.setText(String.valueOf(r.nextInt(15)));
            lbl90.setText(String.valueOf(r.nextInt(20)));
            lblExp.setText(r.nextInt(2)+ " Expired");
            lblActive.setText(r.nextInt(15)+ " Active");
            lblOffline.setText(r.nextInt(3) + " Offline");
            shpActive.setFill(Color.LIMEGREEN);

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        shpActive.setFill(Color.WHITE);
        double x, y = 0;


    
 
   }
        
        
    
    

    @FXML
    private void ajouterResbtn(ActionEvent event) throws SQLException {
        
         if (emailRes.getText().contains("@")==false){
             Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("@ is missing").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        }
            else if(emailRes.getText().contains(".")==false || emailRes.getText().isEmpty()){
             Notifications notificationBuilder=Notifications.create()
              .title("Alert").text(" . is missing").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        }
            else if(nbrPerRes.getText().contains("[a-z]")==true || nbrPerRes.getText().isEmpty()|| nbrPerRes.getText().matches("[a-z]")){
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ecrits").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        }
            else {
         Reservation res  = new Reservation();
          ReservationService pcd = new ReservationService();
           // String event_id = idEvRes.getValue();
          res.setEvent_id(id_event_select);
         String nbr_personnes= nbrPerRes.getText();
         res.setNbr_personnes( Integer.parseInt(nbr_personnes));
         String email  = emailRes.getText();
         res.setEmail(email);
          String date_res = dateRes.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
         res.setDate_res(date_res);
     
         pcd.ajouterReservation(res);
         try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Résevation Ajouté!");
                alert.show();
                 affichageRes.setItems(pcd.getReservationliste());
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
        }
            
     }

    

        
        /*
            String Nom = idEvRes.getValue();
            String date_res = dateRes.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String nbr_personnes = nbrPerRes.getText();
            String email  = emailRes.getText();
            Reservation res  = new Reservation (date_res,Nom,Integer.parseInt(nbr_personnes),email);
            ReservationService pcd = new ReservationService();
           
               try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Réservation Ajouté!");
                alert.show();
                affichageRes.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            //affichagecommande.setItems(cs.getCommandeList());
            
    if (nbr_personnes.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
    pcd.ajouterReservation(res);
    }
            
            
            
            
     */       
            
    

    @FXML
    private void dateRes(ActionEvent event) {
        /* LocalDate mydate =dateRes.getValue();
        //Date d = mydate.todate;
        ZoneId defaultZoneId = ZoneId.systemDefault();
         date_res = java.sql.Date.valueOf(mydate);
        System.out.print(date_res);
*/
    }

    @FXML
    private void modifierResbtn(ActionEvent event)  throws SQLException {
       
         Reservation res = affichageRes.getSelectionModel().getSelectedItem();
          res.setEvent_id(id_event_select);
         String nbr_personnes= nbrPerRes.getText();
         res.setNbr_personnes( Integer.parseInt(nbr_personnes));
         String email  = emailRes.getText();
         res.setEmail(email);
          String date_res = dateRes.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
          res.setDate_res(date_res);
        ReservationService rvs = new ReservationService();
          try {
                       
        rvs.modifierReservation(res,id);
                         Notifications notificationBuilder = Notifications.create().title("Modifier").text("Modifier avec succées ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
         notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       // int id, String nom, String description, int price, String category, String adresse, String img
      affichageRes.setItems(rvs.getReservationliste());
    }

    @FXML
    private void supprimerResbtn(ActionEvent event) throws SQLException {

          ReservationService res = new ReservationService();
            res.supprimerReservation(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             Notifications notificationBuilder = Notifications.create().title("suppression").text("suppression avec succées ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
         notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
            
         affichageRes.setItems(res.getReservationliste());
        }

    @FXML
    private void getEv(ActionEvent event) {
         String nomEv= idEvRes.getSelectionModel().getSelectedItem();
         int event_id=idEvRes.getSelectionModel().getSelectedIndex();
          id_event_select=list_Evenement.get(event_id).getId();
        
    }

    @FXML
    private void goToEv(ActionEvent event) throws Exception {
        
              Parent root = FXMLLoader.load(getClass().getResource("GestionEv.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   
    }

    @FXML
    private void goToRes(ActionEvent event) throws Exception {
              Parent root = FXMLLoader.load(getClass().getResource("GestionRes.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   
    }
  
}
