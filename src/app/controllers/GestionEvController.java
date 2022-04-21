/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;


import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
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
import org.controlsfx.control.Notifications;
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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
import javafx.util.Callback;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;



/**
 * FXML Controller class
 *
 * @author firas
 */
public class GestionEvController implements Initializable {
    public String imagecomp; 
    public String img;
  Connection con;
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
    private Label lblClose;
    @FXML
    private TextField nomEv;
    @FXML
    private DatePicker dateEv;
    @FXML
    private TextField capaciteEv;
    @FXML
    private TextField descriptionEv;
    @FXML
    private Button imageEv;
    @FXML
    private TextField adresseEv;
    @FXML
    private ImageView imagefield;
    String Filename;
      Random r = new Random();
    @FXML
    private TableView<Evenement> affichageEv;
    @FXML
    private TableColumn<Evenement,String> idEvtab;
    @FXML
    private TableColumn<Evenement,String> nomEvtab;
    @FXML
    private TableColumn<Evenement,String> capaciteEvtab;
    @FXML
    private TableColumn<Evenement,String> dateEvtab;
    @FXML
    private TableColumn<Evenement,String> descriptionEvtab;
    @FXML
    private TableColumn<Evenement,Image> imageEvtab;
    @FXML
    private TableColumn<Evenement,String> adresseEvtab;
     ValidationSupport validationSupport = new ValidationSupport();
     
    
    private ImageView immm;
    public int id;
        public ImageView photo;
    @FXML
    private Button ajouterEvbtn;
    @FXML
    private Button modifierEvbtn;
    @FXML
    private Button supprimerEvbtn;
    ObservableList list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        EvenementService evs = new EvenementService();
        validationSupport.registerValidator(nomEv, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(capaciteEv, Validator.createEmptyValidator("Number is required"));
         validationSupport.registerValidator(dateEv, Validator.createEmptyValidator("Date is required"));
          validationSupport.registerValidator(descriptionEv, Validator.createEmptyValidator("text is required"));
          //validationSupport.registerValidator(imageEv, Validator.createEmptyValidator("Image is required"));
          validationSupport.registerValidator(adresseEv, Validator.createEmptyValidator("text Selection Required"));
           
        
         affichageEv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                id = evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel().getSelectedIndex())
                        .getId();
                
                //System.out.println(iddd);
                
                
                 nomEv.setText(String.valueOf(evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel()
                            .getSelectedIndex()
                        ).getNom()
                          ));
   
                capaciteEv.setText(String.valueOf(evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel().
                                getSelectedIndex())
                        .getCapacite()));
                
                
               
                
                dateEv.setValue(LocalDate.parse(evs.recupererEvenement()
                    .get(affichageEv.getSelectionModel()
                            .getSelectedIndex())
                    .getDate()

                ));
                
                descriptionEv.setText(evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel()
                                .getSelectedIndex())
                        .getDescription()
                ); 
  
                imageEv.setText(evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel()
                                .getSelectedIndex())
                        .getImage()
                ); 
                
                
                adresseEv.setText(evs.recupererEvenement()
                        .get(affichageEv.getSelectionModel()
                            .getSelectedIndex()
                        ).getAdresse()
                          
                );
                
          
                
                };
            
               
         }); 
       
        Evenement ev = new Evenement();
         ObservableList<Evenement> recupererEvenement ;
          List Evenement = evs.recupererEvenement();
        list = FXCollections.observableArrayList(Evenement);
        
       
       int  listNum = 1;        
   Evenement = FXCollections.observableArrayList();
            try {
                  
            recupererEvenement =evs.getEvenementliste();
            idEvtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomEvtab.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            capaciteEvtab.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
            dateEvtab.setCellValueFactory(new PropertyValueFactory<>("Date"));
            descriptionEvtab.setCellValueFactory(new PropertyValueFactory<>("Description"));
             
          //  imageEvtab.setCellValueFactory(new PropertyValueFactory<>("image"));
             imageEvtab.setCellFactory(param -> {
       //Set up the ImageView
      final ImageView imageview = new ImageView();
       imageview.setFitHeight(70);
       imageview.setFitWidth(132);

       //Set up the Table
       TableCell<Evenement, Image> cell = new TableCell<Evenement, Image>() {
           public void updateItem(Image item, boolean empty) {
             if (item != null) {
                  imageview.setImage(item);
             }
           }
        };
        // Attach the imageview to the cell
        cell.setGraphic(imageview);
        return cell;
   });
            imageEvtab.setCellValueFactory(new PropertyValueFactory<Evenement, Image>("imageE"));
            adresseEvtab.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            affichageEv.setItems(recupererEvenement);
           
            } catch (SQLException ex) {
            Logger.getLogger(GestionEvController.class.getName()).log(Level.SEVERE, null, ex);
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

    }
    public SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
public Object getImage() {
    return image.get();
}
    
     public void afficherEv(Evenement ev) throws IOException{
      /*    Evenement ev1 = affichageEv.getSelectionModel().getSelectedItem();
            String Description = descriptionEv.getText();
                  nomEvtab.setText(ev.getNom());
                 capaciteEvtab.setText(String.valueOf(ev.getCapacite()));
                  dateEvtab.setText(ev.getDate());
                   descriptionEvtab.setText(ev.getDescription());
           // javafx.scene.image.Image im =new javafx.scene.image.Image(ev.getImageProgramme());
           //image_l.setImage(im); 
            BufferedImage bf;
            bf = ImageIO.read(new File(ev.getImage()));
       //ImageIcon icon = new ImageIcon (Filename);
          Image image = SwingFXUtils.toFXImage(bf, null);
         imagefield .setImage(image);      
  */
}
       
      
/*
    private void loadHash(PieChart pieChart) {

        ObservableList<PieChart.Data> data =
                FXCollections.observableArrayList(
                        new PieChart.Data("Hash", r.nextInt(50)),
                        new PieChart.Data("Key", r.nextInt(50))
                );
        pieChart.setData(data);

    }

*/ 
    /////////////////////////////CRUD////////////////////////////////////
    @FXML
    private void dateEv(ActionEvent event) {
    }
    
      @FXML
    private void ajouterEv(ActionEvent event ) throws SQLException {
        
      
         
           if ( nomEv.getText().isEmpty() || nomEv.getText().matches("[0-9]") || descriptionEv.getText().isEmpty() || capaciteEv.getText().matches("[a-z]") || capaciteEv.getText().isEmpty() || adresseEv.getText().isEmpty() || adresseEv.getText().matches("[0-9]") 
            ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
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
    
          
     ////
          String Nom  = nomEv.getText();
            String Capacite = capaciteEv.getText();
            String Date = dateEv.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String Description = descriptionEv.getText();
            String Adresse = adresseEv.getText();
            Evenement ev = new Evenement (Nom,Integer.parseInt(Capacite),Date,Description,filename,Adresse);
             EvenementService pcd = new EvenementService();

                      pcd.ajouterEvenement(ev);
                       affichageEv.setItems(pcd.getEvenementliste());
      /*
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Evenementajouté avec Sucees!");
            alert.show();*/
      Notifications notificationBuilder=Notifications.create()
              .title("Notifications").text("Evenement ajouté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
       
           
           
       
        
            
       
            
     }
    
    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException, IOException {
    
       FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imagefield.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }
             try{    
         InputStream stream = new FileInputStream("C:/Users/firas/OneDrive/Desktop/ProjetDev/Intégration/public/uploads/" + filename);
         Image image = new Image(stream);
         stream.read();    
         //Creating the image view
         ImageView imageView = new ImageView();
         //Setting image to the image view
         imageView.setImage(image);
      //Setting the image view parameters
       imagefield.setFitHeight(215);
       imagefield.setFitWidth(265);
       imageView.setPreserveRatio(true);
          }catch(Exception e){System.out.println(e);}    
        }   
      
/*
  FileChooser chooser=new FileChooser();
       chooser.showOpenDialog(null);
       chooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
       File SelectedFile = chooser.showOpenDialog(null);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
         if (SelectedFile != null) {
             img = SelectedFile.toString();
            int index = img.lastIndexOf('\\');
            if (index > 0) {
                filename = img.substring(index + 1);
                //System.out.println(fileName);
            }
            String key = UUID.randomUUID().toString();

        img = SelectedFile.getAbsolutePath();
        File source = new File(img);
        File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" + key + filename);
        String url = "/image/" + key + filename;
        imageEv.setText(url);
        }  
*/
    }
    @FXML
    private void supprimerEvbtn(ActionEvent event) throws SQLException {
          if ( nomEv.getText().isEmpty() || nomEv.getText().matches("[0-9]") || descriptionEv.getText().isEmpty() || capaciteEv.getText().matches("[a-z]") || capaciteEv.getText().isEmpty() || adresseEv.getText().isEmpty() || adresseEv.getText().matches("[0-9]") 
            ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
          Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer l'evenement  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            EvenementService evs = new EvenementService();
            evs.supprimerEvenement(id);
         affichageEv.setItems(evs.getEvenementliste());
         
        Notifications notificationBuilder = Notifications.create().title("suppression").text("suppression avec succées ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
         notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
        
        }
          }  
    }

    @FXML
    private void modifierEvbtn(ActionEvent event) throws SQLException {
        
          if ( nomEv.getText().isEmpty() || nomEv.getText().matches("[0-9]") || descriptionEv.getText().isEmpty() || capaciteEv.getText().matches("[a-z]") || capaciteEv.getText().isEmpty() || adresseEv.getText().isEmpty() || adresseEv.getText().matches("[0-9]") 
            ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
         Evenement ev = affichageEv.getSelectionModel().getSelectedItem();
        String cp = capaciteEv.getText();
         int capacite = Integer.parseInt(cp);

       // r.setNom(repasnom.getText());
       //  String memid = select1.getValue();
       // int id = Integer.parseInt(idd);
        ev.setNom(nomEv.getText());
        ev.setDescription(descriptionEv.getText()); 
        ev.setAdresse(adresseEv.getText()); 
        ev.setCapacite(capacite);
        String Date = dateEv.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        ev.setDate(Date);
        ev.setImage(imagecomp);
        System.out.println(imagecomp);
        EvenementService evs = new EvenementService();
          try {              
        evs.modifierEvenement(id, ev.getNom(),capacite,ev.getDate(),ev.getDescription(), ev.filename ,ev.getAdresse());
                         Notifications notificationBuilder = Notifications.create().title("Modification").text("Modifier avec succées ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
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
      affichageEv.setItems(evs.getEvenementliste());
}
    }   

    
    @FXML
    private void goToRes(ActionEvent event) throws Exception {
 
        
        
              Parent root = FXMLLoader.load(getClass().getResource("GestionRes.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   
    
       
               
    }

    @FXML
    private void geToEv(ActionEvent event) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("GestionEv.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   }


}


            
           
            
    
        
    
    
 
