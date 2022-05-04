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
import entities.Categorie;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import service.CategorieServices;
import utils.MyConnexion;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestionCategorieController implements Initializable {
public static int  nh = 0 ,nf=0 , ne = 0 ;  
            ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme","Enfant");
    @FXML
    private PieChart pieHash;
    @FXML
    private PieChart pieKey;
    @FXML
    private Label lblClose;
    @FXML
    private TableView<Categorie> tablecat;
    @FXML
    private TableColumn<Categorie, String> noma;
    @FXML
    private TableColumn<Categorie, String> typea;
    @FXML
    private TableColumn<Categorie, String> sexea;
    @FXML
    private TableColumn<Categorie, String> cata;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfsexe;
    @FXML
    private TextField tfcat;

     int  index= -1; 
    @FXML
    private TextField id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         affiche();
        ObservableList<Categorie>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT nom_c , type, sexe, categorie_order FROM categorie");
            while(rs.next())
            {
                list.add(new Categorie(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

       noma.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom_c());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    typea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getType());
        });
    sexea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getSexe());
        });
         cata.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getCategorie_order());
        });
       
        // TODO
 tablecat.setItems(list);
 tablecat.refresh();
    // TODO
    }    

    @FXML
    private void selected(MouseEvent event) {
          index=tablecat.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfnom.setText(noma.getCellData(index).toString());
                tftype.setText(typea.getCellData(index).toString());
                tfsexe.setText(sexea.getCellData(index).toString());
                tfcat.setText(cata.getCellData(index).toString());
    }

    @FXML
    private void ajouter(ActionEvent event)  {
        if(verifUserChamps() ){ 
                        if ( controlSaisie()){

         CategorieServices ts = new CategorieServices();
                  CategorieServices ts1 = new CategorieServices();
       ts.AjouterCategorie(new Categorie(tfnom.getText(),tftype.getText(),tfsexe.getText(),tfcat.getText()));
                affiche();

          
 
       
        

      }} 
        
    }

    @FXML
    private void modifier(ActionEvent event) {
         String nom_c=tfnom.getText();
        String type=tftype.getText();
        String sexe=tfsexe.getText();

        String categorie_order=tfcat.getText();
       

        CategorieServices sp = new CategorieServices();
        Categorie c = new Categorie();
        c.setNom_c(nom_c);
 c.setType(type);
        c.setSexe(sexe);

        c.setCategorie_order(categorie_order);
               
                c.setNom_c(nom_c);

        
        sp.modifier(c);
                 affiche();

    }

    @FXML
    private void supprimer(ActionEvent event)   {
      CategorieServices ts = new CategorieServices();
        Categorie ls = new Categorie();
        ls = tablecat.getSelectionModel().getSelectedItem();
                

        ts.SupprimerCategorie(ls.getNom_c());
         affiche();
        
                      


                 
    }
     public ObservableList<Categorie> show1()
    { 
       

           try {
               ObservableList<Categorie> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select nom_c,type,sexe,categorie_order from categorie ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Categorie ls = new Categorie();
                 ls.setNom_c(rs.getString("nom_c"));

                 ls.setType(rs.getString("type"));
                 ls.setSexe(rs.getString("sexe"));
                 ls.setCategorie_order(rs.getString("categorie_order"));
                
             

                  
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
        
           
                         
      noma.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
      typea.setCellValueFactory(new PropertyValueFactory<>("type"));
      sexea.setCellValueFactory(new PropertyValueFactory<>("sexe"));
      cata.setCellValueFactory(new PropertyValueFactory<>("categorie_order"));
     
      ObservableList<Categorie> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tablecat.setItems(obl);
      System.out.println(""+obl);

                      
    }
 public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tfnom.setStyle(styledefault);
        tftype.setStyle(styledefault);
        tfsexe.setStyle(styledefault);
        tfcat.setStyle(styledefault);
        

     
       
 

        if (tfnom.getText().equals("")) {
            tfnom.setStyle(style);
            verif = 1;
        }
       
        if ( tftype.getText().equals("")) {
             tftype.setStyle(style);
            verif = 1;
        }
         
        if (tfsexe.getText().equals("")) {
            tfsexe.setStyle(style);
            verif = 1;
        }
       
        if (tfcat.getText().equals("")) {
            tfcat.setStyle(style);
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
         

        if(checkIfStringContainsNumber(tfnom.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(tftype.getText())){
            alert.setContentText("Le type ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber(tfsexe.getText())){
            alert.setContentText("Le sexe ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tfcat.getText())){
            alert.setContentText("Le categorie_order ne doit pas contenir des caracteres");
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
    private void statistique(ActionEvent event) 
        throws IOException{
        CategorieServices ser= new CategorieServices();
        
        List<Categorie> li =ser.afficher(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getSexe().equals("Homme"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getSexe().equals("Femme")){nf=nf+1 ; } 
        if (li.get(i).getSexe().equals("Enfant")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("CatStat.fxml"));
         Parent root = loader.load();
        tfnom.getScene().setRoot(root);
        
    }
private void populateTable(ObservableList<Categorie> branlist){
       tablecat.setItems(branlist);}
    @FXML
    private void search(KeyEvent event) {
    CategorieServices bs=new  CategorieServices (); 
        Categorie c= new Categorie();
        ObservableList<Categorie>filter= bs.chercherTitreplat(id.getText());
        populateTable(filter);
}
    }

    
   
   

 

