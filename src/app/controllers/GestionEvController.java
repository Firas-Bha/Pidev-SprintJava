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
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import java.sql.PreparedStatement;
//import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
//import javafx.scene.image.Image;
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
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
import util.MyDB;
import java.sql.Statement;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.util.Base64;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert.AlertType;
import service.EvenementService;
import static service.EvenementService.decodeToImage;
import sun.misc.BASE64Decoder;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


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
    private TextField filter;
    @FXML
    private DatePicker dateEv;
    @FXML
    private TextField capaciteEv;
    @FXML
    private TextField descriptionEv;
    @FXML
    private Button imageEv;
     @FXML
    private Button send;
    @FXML
    private TextField adresseEv;
    @FXML
    private ImageView imagefield;
    String Filename;
      Random r = new Random();
      EvenementService evs = new EvenementService();
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
    private TableColumn<Evenement,ImageView> imageEvtab;
    @FXML
    private TableColumn<Evenement,String> adresseEvtab;
     ValidationSupport validationSupport = new ValidationSupport();
     static Evenement selectionedEvenement;
    
    private ImageView immm;
    public int id;
      public ImageView photo;
        String img64,path="";
    @FXML
    private Button ajouterEvbtn;
    @FXML
    private Button modifierEvbtn;
    @FXML
    private Button supprimerEvbtn;
    ObservableList list;
    
    @FXML
    private Button pdf;
    
    @FXML
    private Button excel;
   
Connection cnx =MyDB.getInstance().getConnection();
    
    
    

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
       
      /*  Evenement ev = new Evenement();
         ObservableList<Evenement> recupererEvenement ;
          List Evenement = evs.recupererEvenement();
        list = FXCollections.observableArrayList(Evenement);
        
       
       int  listNum = 1;        
   Evenement = FXCollections.observableArrayList();
         */
          
       
           // try {
             List<Evenement> le=evs.recupererEvenement();  
        ObservableList<Evenement> datalist = FXCollections.observableArrayList(le);
           // recupererEvenement =evs.getEvenementliste();
            idEvtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomEvtab.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            capaciteEvtab.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
            dateEvtab.setCellValueFactory(new PropertyValueFactory<>("Date"));
            descriptionEvtab.setCellValueFactory(new PropertyValueFactory<>("Description"));
            imageEvtab.setCellValueFactory(new PropertyValueFactory<>("img"));       
            adresseEvtab.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            affichageEv.setItems(datalist);
      
            affichageEv.refresh();
            
             try{
         affichageEv.setItems(datalist);
        //recherche avec filtre
         FilteredList<Evenement> filtredData = new FilteredList<>(datalist, b -> true);
         filter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(p-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(p.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(p.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(p.getCapacite()).indexOf(lowerCaseFilter)!=-1){
                     
                     return true;
                 }else if(String.valueOf(p.getAdresse()).indexOf(lowerCaseFilter)!=-1){
                 return true;
                 }else if(String.valueOf(p.getDate()).indexOf(lowerCaseFilter)!=-1){
                 return true;
                 }
                     else
                     return false;
                 });
             });
         SortedList<Evenement> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(affichageEv.comparatorProperty());
         affichageEv.setItems(sortedData);
         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }
            
            
            
            
            
            
     /*        
          
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
           */
           //} catch (SQLException ex) {
            //Logger.getLogger(GestionEvController.class.getName()).log(Level.SEVERE, null, ex);
    
                
        
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
    /////////////////////////////CRUD//////////////////////@FXML
    @FXML
    private void dateEv(ActionEvent event) {
    }
    
      public void Calendar(String Titre , String Datedeb , String Datefin) throws MalformedURLException, IOException{
        // Using Calendar api
          URL url = new URL("https://www.googleapis.com/calendar/v3/o3esqfud4k9qhcma6430ui8v2g@group.calendar.google.com/Events");
      HttpURLConnection http = (HttpURLConnection)url.openConnection();
      http.setRequestMethod("POST");
      http.setDoOutput(true);
      http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM-T_VM8jgMQwq1AqpGaOUn1d6EO2TFD3n60OUU_2FFFdgV_9qbpNikPt0MyT6Unb60xa079CbwOZ0pMoLb_GJzZKq5nEWZvinAwkwuWBAMspXWdoJ-0xzi2O1TDut9igeb2Se9uTl_p99CMmVIKlW9w");
      http.setRequestProperty("Content-Type", "application/json");

      String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"T10:00:00.000-06:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"T10:25:00.000-06:00\"\n    }\n\n}";
//T10:00:00.000-06:00 heya 16h yaani 4pm
//String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"\"\n    }\n\n}";
//String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
     byte[] out = data.getBytes(StandardCharsets.UTF_8);

     OutputStream stream = http.getOutputStream();
     stream.write(out);

     System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + "Cours added to Calendar Successfully");
      http.disconnect();
        
        // end Calendar 
}
      @FXML
    private void ajouterEv(ActionEvent event ) throws SQLException ,IOException {
        
      
         
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
          String Nom  = nomEv.getText();
            String Capacite = capaciteEv.getText();
            String Date = dateEv.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String Description = descriptionEv.getText();
            String Adresse = adresseEv.getText();
            Evenement ev = new Evenement (Nom,Integer.parseInt(Capacite),Date,Description,encodeb64(path),Adresse);
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
      notificationBuilder.showConfirm();
      Calendar(nomEv.getText(),dateEv.getValue().format(DateTimeFormatter.ISO_DATE),dateEv.getValue().format(DateTimeFormatter.ISO_DATE));

       }
    
     }
    
    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException, IOException {
    
          FileChooser chooser = new FileChooser();
    chooser.setTitle("choisir une image");
     FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("image file","*.png","*.jpg","*.jpeg");
   chooser.getExtensionFilters().add(filter);
    File file = chooser.showOpenDialog(imageEv.getScene().getWindow());
   if(file!=null){
       javafx.scene.image.Image img= new javafx.scene.image.Image(file.toURI().toString());
   path= file.getPath();
  imagefield.setImage(img);
  imagefield.setFitHeight(215);
  imagefield.setFitWidth(265);
   Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Image insérèe ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        
   } 
       
        
        /*
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
    
     private String encodeb64(String path) throws FileNotFoundException, IOException
    {
    File file = new File(path);
byte[] bytes = new byte[(int)file.length()];
FileInputStream fis = new FileInputStream(file);
fis.read(bytes); 
fis.close();
String ef = Base64.getEncoder().encodeToString(bytes);
return ef;
    }
    public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
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
    
    @FXML
    private void excel(ActionEvent event) throws  SQLException, FileNotFoundException, IOException  {
        
         Connection cnx = MyDB.getInstance().getConnection();
        String query = "Select * from Evenement";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
        
       
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détail Evenement");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("Nom");
            header.createCell(2).setCellValue("Capacite");
            header.createCell(3).setCellValue("Date");
             header.createCell(4).setCellValue("Description");
              header.createCell(5).setCellValue("Adresse");

            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                
                row.createCell(0).setCellValue(rs.getString("id"));
            row.createCell(1).setCellValue(rs.getString("Nom"));
            row.createCell(2).setCellValue(rs.getString("Capacite"));
            row.createCell(3).setCellValue(rs.getString("Date"));
             row.createCell(4).setCellValue(rs.getString("Description"));
              row.createCell(5).setCellValue(rs.getString("Adresse"));
              
                index++;
            }
             
           
            FileOutputStream file = new FileOutputStream("Evenement.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            File myFile = new File("C:\\Users\\firas\\OneDrive\\Desktop\\PidevJava\\Evenement.xlsx");
             Desktop.getDesktop().open(myFile);
         }
    
    
    
    @FXML
    private void pdf(ActionEvent event)  throws  SQLException,FileNotFoundException, DocumentException, IOException {
       
        
        
      try
      {Evenement  ev=evs.afficher_ById(affichageEv.getSelectionModel().getSelectedItem().getId());
        

          Document document = new Document();
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(""+ev.getNom()+".pdf"));
         document.open();
          Paragraph TitleAct = new Paragraph(ev.getNom());
          TitleAct.setAlignment(1);
         document.add(TitleAct);
         
//Add Image
javafx.scene.image.Image img=SwingFXUtils.toFXImage(decodeToImage(ev.getImage()), null);
//Add Image
BASE64Decoder decoder = new BASE64Decoder();
           byte[] imgb = decoder.decodeBuffer(ev.getImage());
	Image image1 = Image.getInstance(imgb);
			//For Fixed Positioning
			//image1.setAbsolutePosition(100f, 550f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(250, 250);
                        image1.setAlignment(1);
			//Add to document
			document.add(image1);
                        Paragraph DateAct = new Paragraph("Date de l'actualité : "+ev.getDate().toString());
    DateAct.setAlignment(0);
         document.add(DateAct);
Paragraph DescAct = new Paragraph("Description : "+ev.getDescription());
    DescAct.setAlignment(0);
         document.add(DescAct);
  
    
    //Add to document
    //document.add((Element) img);
        
         document.close();
         writer.close();
         Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Actualité Exportée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
         File myFile = new File("C:\\Users\\firas\\OneDrive\\Desktop\\PidevJava\\evenement.pdf");
             Desktop.getDesktop().open(myFile);
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        
    
       
       
       
     /*   String path="";
JFileChooser j=new JFileChooser ();
j.setFileSelection

int x=j.showSaveDialog (j);
if (x==JFileChooser. APPROVE_OPTION)
    path=j.getSelectedFile ().getPath ();
Document doc=new Document ();
try{
   PdfWriter.getInstance (doc, new FileOutputStream (path+".pdf"));
   doc.open();
PdfPTable tbl=new PdfPTable(6);

//adding header
tbl.addCell ("nom");
tbl.addCell ("capacite");
tbl.addCell ("date");
tbl.addCell ("description");
tbl.addCell ("Image");
tbl.addCell ("Adresse");

for (int i = 0; i < affichageEv.getItems().size(); i++) {
    

   String nom=nomEvtab.getCellData(i);
//   String capacite=capaciteEvtab.getCellData(i).toString();
   String date=dateEvtab.getCellData(i).toString();
   String description=descriptionEvtab.getCellData(i);
   
   
   
   Evenement ev = new Evenement();
   //Add Image
javafx.scene.image.Image img=SwingFXUtils.toFXImage(decodeToImage(ev.getImage()), null);
//Add Image
BASE64Decoder decoder = new BASE64Decoder();
           byte[] imgb = decoder.decodeBuffer(ev.getImage());
	Image image1 = Image.getInstance(imgb);
			//For Fixed Positioning
			//image1.setAbsolutePosition(100f, 550f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(250, 250);
                        image1.setAlignment(1);
			//Add to document
			doc.add(image1);
     
         
         
         
  // javafx.scene.image Image=imageEvtab.getCellData(i);
   String adresse=adresseEvtab.getCellData(i);
   tbl.addCell (nom);
   //tbl.addCell (capacite);
   tbl.addCell (date);
   tbl.addCell (description);
   tbl.addCell (adresse);
   
}
    doc.add(new Phrase(16, "\n\n\n"));
    doc.add(new Phrase(-16,"la liste de tes evenements"));	
    doc.add(new Phrase(16, "\n\n\n")); 
    doc.add(tbl);
     
}catch (FileNotFoundException ex) {
    Logger.getLogger (GestionEvController.class.getName ()).log (Level.SEVERE, null, ex);
    }
doc.close();
       
    */   
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    }

   
    
    
    
    
    
    
    
    
    
    
    /*   
       
       String FILE_NAME = "C:\\Users\\firas\\OneDrive\\Desktop\\PidevJava\\Evenement.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            //Image imag = Image.getInstance("checkMark.jpg");
            //imag.scaleAbsoluteWidth(100);
            //imag.scaleAbsoluteHeight(92);
           // imag.setAlignment(Image.ALIGN_LEFT);
           // imag.setAlignment(Image.ALIGN_TOP);
           // document.add(imag);
            Paragraph p = new Paragraph();
            p.add("Liste Evenements");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            
            //Image img=Image.getInstance("C:\\Users\\malak_6\\Desktop\\integration\\src\\GUI\\img\\checkMark.jpg");
           // img.scaleAbsoluteWidth(400);
            //img.scaleAbsoluteHeight(92);
           // imag.setAlignment(Image.ALIGN_LEFT);
           // img.setAlignment(Image.ALIGN_CENTER);
            //document.add(img);
            Connection cnx =MyDB.getInstance().getConnection();
            String query = "select nom,id from reservation where id=(select max(id) from reservation) ";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
                p3 = new Paragraph();
                
                
              
                p3.add(rs.getString("nom"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
        } catch 
   
(Exception e) {
            e.printStackTrace();
        }
*/
    
     @FXML
    private void sendmail(ActionEvent event) throws IOException,Exception {
        
       
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../gui/Send_Email.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(GestionEvController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    }



            
           
            
    
        
    
    
 
