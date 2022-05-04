/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi3;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.javaMail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entities.Livraison;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import org.controlsfx.control.Rating;
import service.LivraisonServices;
import utils.MyConnexion;
import utils.javaMail;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestionLivraisonController implements Initializable {

    @FXML
    private PieChart pieHash;
    @FXML
    private PieChart pieKey;
    @FXML
    private Label lblClose;
    @FXML
    private TableColumn<Livraison, String> numa;
    @FXML
    private TableColumn<Livraison, String> tela;
    @FXML
    private TableColumn<Livraison, String> datea;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfdate;
    @FXML
    private TableColumn<Livraison, String> noma1;
    @FXML
    private TextField tfnom1;
    @FXML
    private TableView<Livraison> tableliv;
    int  index= -1; 
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    @FXML
    private TextField objet;
    @FXML
    private TextField corps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          rating.ratingProperty().addListener(new ChangeListener<Number>() {
           
       
            @Override
            public void changed(ObservableValue<? extends Number> url, Number t, Number t1){
            msg.setText("Rating : "+t1.toString());
           
        }


       
        });
         affiche();
        ObservableList<Livraison>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT num_l, nom_livreur, tel_livreur, date_livraison FROM livraison");
            while(rs.next())
            {
                list.add(new Livraison(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }

       numa.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNum_l());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    noma1.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom_livreur());
        });
    tela.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getTel_livreur());
        });
         datea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDate_livraison());
        });
       
        // TODO
 tableliv.setItems(list);
 tableliv.refresh();
    // TODO// TODO
    }    

    @FXML
    private void selected(MouseEvent event) {
         index=tableliv.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfnum.setText(numa.getCellData(index).toString());
                tfnom1.setText( noma1.getCellData(index).toString());
                 tftel.setText(tela.getCellData(index).toString());
                tfdate.setText(datea.getCellData(index).toString());
    }

    @FXML
    private void ajouter(ActionEvent event) {
         if(verifUserChamps() ){ 
                        if ( controlSaisie()){

         LivraisonServices ts = new LivraisonServices();
                  LivraisonServices ts1 = new LivraisonServices();
       ts.AjouterLivraison(new Livraison(tfnum.getText(), tfnom1.getText(), tftel.getText(),tfdate.getText()));
        try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("hassou.salah@yahoo.com", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
                affiche();

          
 
       
        

      }} 
    }

    @FXML
    private void modifier(ActionEvent event) {
         String num_l=tfnum.getText();
        String nom_livreur=tfnom1.getText();
        String tel_livreur=tftel.getText();

        String date_livraison=tfdate.getText();
       

       LivraisonServices sp = new LivraisonServices();
       Livraison l = new Livraison();
       l.setNum_l(num_l);
 l.setNom_livreur(nom_livreur);
        l.setTel_livreur(tel_livreur);

        l.setDate_livraison(date_livraison);
               
                l.setNom_livreur(nom_livreur);

        
        sp.modifier(l);
                 affiche();
    }

    @FXML
    private void supprimer(ActionEvent event) {
         LivraisonServices ts = new LivraisonServices();
        Livraison ls = new Livraison();
        ls = tableliv.getSelectionModel().getSelectedItem();
                

        ts.SupprimerLivraison(ls.getNom_livreur());
         affiche();
    }
    public ObservableList<Livraison> show1()
    { 
       

           try {
               ObservableList<Livraison> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select num_l,nom_livreur,tel_livreur,date_livraison from livraison ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Livraison ls = new Livraison();
                 ls.setNum_l(rs.getString("num_l"));

                 ls.setNom_livreur(rs.getString("nom_livreur"));
                 ls.setTel_livreur(rs.getString("tel_livreur"));
                 ls.setDate_livraison(rs.getString("date_livraison"));
                
             

                  
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    }  
    public void affiche() {
        
           
                         
      numa.setCellValueFactory(new PropertyValueFactory<>("num_l"));
      noma1.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
      tela.setCellValueFactory(new PropertyValueFactory<>("tel_livreur"));
      datea.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
     
      ObservableList<Livraison> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableliv.setItems(obl);
      System.out.println(""+obl);

                      
    }
     public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tfnum.setStyle(styledefault);
        tfnom1.setStyle(styledefault);
        tftel.setStyle(styledefault);
        tfdate.setStyle(styledefault);
        

     
       
 

        if ( tfnum.getText().equals("")) {
            tfnum.setStyle(style);
            verif = 1;
        }
       
        if (  tfnom1.getText().equals("")) {
              tfnom1.setStyle(style);
            verif = 1;
        }
         
        if (tftel.getText().equals("")) {
            tftel.setStyle(style);
            verif = 1;
        }
       
        if (tfdate.getText().equals("")) {
            tfdate.setStyle(style);
            verif = 1;
        }
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
      public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber2(tfnum.getText())){
            alert.setContentText("Le num_l ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(tfnom1.getText())){
            alert.setContentText("Le nom_livreur ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tftel.getText())){
            alert.setContentText("Le tel_livreur ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tfdate.getText())){
            alert.setContentText("La date_livraison ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
             
             

        
        return true;
    }
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
        public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }

    @FXML
    private void allerverscat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pi3/GestionCategorie.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();

    }

    @FXML
    private void allerverspr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pi3/GestionLivraison.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();

    }

    @FXML
    private void pdf(ActionEvent event) {
        Printer printer = Printer.getDefaultPrinter();
     Node node = new Circle(100, 200, 200);
 PrinterJob job = PrinterJob.createPrinterJob(printer);
 if (job != null) {
    boolean success = job.printPage(tableliv);
    if (success) {
        job.endJob();
    }
 }
    
    }
    public void mailfonction(MouseEvent event) {
        try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("mehdi.jegham@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

