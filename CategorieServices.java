/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnexion;
/**
 *
 * @author MSI
 */
public class CategorieServices implements ICategorieServices{
    Connection cnx = MyConnexion.getInstance().getCnx();
    
    @Override
    public void AjouterCategorie(Categorie c) {
        try {
             String req = "INSERT INTO categorie (nom_c,type ,sexe, categorie_order  ) VALUES ('" + c.getNom_c() + "','" + c.getType() + "','" + c.getSexe() + "','" + c.getCategorie_order() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerCategorie(String nom_c){
       
//           try {
//            PreparedStatement statement = cnx.prepareStatement(
//    "DELETE FROM trip WHERE offre = ?"
//);
//   statement.setString(1, p.getOffre());
//    statement.executeUpdate();
////            
//          
//            System.out.println("Trip supprimé !");
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
// //      }
       String requete = "delete from categorie where nom_c=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,nom_c);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierCategorie(Categorie c, String nom_c) {
        	 
		 try{
            
        String req2 = "UPDATE `categorie` SET ``type`=?,`sexe`=?,`categorie_order`=?  WHERE nom_c=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           st.setString(1, c.getNom_c());
            st.setString(2, c.getType());
            st.setString(3,c.getSexe());
             st.setString(4,c.getCategorie_order());
            

            st.setString(5,nom_c);
                st.executeUpdate();
                
                 System.out.println("categorie mis à jour avec succès !");
                 System.out.println(c.toString());
        }catch (SQLException ex) {
            System.out.println(c.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
//        try {
//            String req = "UPDATE trip SET  ville_dest =? img=? ,description=? ,periode=? WHERE offre=?" ;
//           PreparedStatement pst = cnx.prepareStatement(req);
////           
//                       pst.setString(1, p.getVille_dest());
//
//            pst.setString(2, p.getImg());
//            pst.setString(3,p.getDescription());
//             pst.setString(4, p.getOffre());
//                       pst.setString(5, p.getPeriode());
//
//            pst.executeUpdate();
//            System.out.println("Trip modifié !");
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
//        }
//String sql = "UPDATE trip SET `ville_dest`=?,`img`=?,`description`=? ,`periode`=?  WHERE offre=" + p.getOffre();
//        PreparedStatement ste;
//        try {
//            ste = cnx.prepareStatement(sql);
//                        ste.setInt(1,p.getId_trip());
//
//            ste.setString(2,p.getVille_dest());
//            ste.setString(3, p.getImg());
//            ste.setString(4, p.getDescription());
//
//            ste.setString(5, p.getPeriode());
//
//
//            ste.executeUpdate();
//            int rowsUpdated = ste.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("La modification de la classe a été éffectuée avec succès ");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TripServices.class.getName()).log(Level.SEVERE, null, ex);
//        }

    
        
    

    @Override
    public List<Categorie> afficher() {
        List<Categorie> list = new ArrayList<>();
        
        try {
            String req = "SELECT nom_c , type, sexe, categorie_order FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Categorie( rs.getString("nom_c"),rs.getString("type"),rs.getString("sexe"),rs.getString("categorie_order")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
 

//    public void rechercher(String index){
//List<Trip> result = afficher().stream().filter(line -> index.equals(line.getOffre())).collect(Collectors.toList());
//                    System.out.println("----------");
//                    result.forEach(System.out::println);
////
//}
//   public void TrierParId (){
// TripServices sa = new TripServices();
//List<Trip> TrierParId = sa.afficher().stream().sorted(Comparator.comparing(Trip::getId_trip)).collect(Collectors.toList());
////                            TrierParId.forEach(System.out::println);
//}
@Override
   public int modifier (Categorie c){
String sq13="UPDATE `categorie` SET `nom_c`=?,`type`=?,`sexe`=?,`categorie_order`=?WHERE nom_c =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getNom_c());
            pst.setString(2, c.getType());
                        pst.setString(3, c.getSexe());

            pst.setString(4, c.getCategorie_order());
                      

                        pst.setString(5, c.getNom_c());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
   public List<Categorie> ListClasse1(String c ) {
        List<Categorie> Mylist = new ArrayList<>();
        try {
            String requete = "select nom_c,type,sexe,categorie_order from categorie where nom_c LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c);
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Categorie pre = new Categorie();
              
            pre.setNom_c(e.getString("nom_c"));
            pre.setType(e.getString("type"));
            pre.setSexe(e.getString("sexe"));
            pre.setCategorie_order(e.getString("categorie_order"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
public ObservableList<Categorie> chercherTitreplat(String chaine){
          String sql="SELECT * FROM categorie WHERE (nom_c LIKE ?or type LIKE ? )";
            
             Connection cnx= MyConnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<Categorie> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
          //  stee.setString(3, ch);
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
               
                Categorie c = new Categorie ();
                c.setNom_c(rs.getString(2));
                c.setType(rs.getString(3));
                c.setSexe(rs.getString(4));
                c.setCategorie_order(rs.getString(5));
             
                
                
                myList.add(c);
                System.out.println("type trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
   

  
}
