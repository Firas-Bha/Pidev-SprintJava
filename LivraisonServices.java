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
import entities.Livraison;
import utils.MyConnexion;
/**
 *
 * @author MSI
 */
public class LivraisonServices implements ILivraisonServices{
    Connection cnx = MyConnexion.getInstance().getCnx();
    
    @Override
    public void AjouterLivraison(Livraison l) {
        try {
             String req = "INSERT INTO livraison (num_l,nom_livreur ,tel_livreur, date_livraison  ) VALUES ('" + l.getNum_l() + "','" + l.getNom_livreur() + "','" + l.getTel_livreur() + "','" + l.getDate_livraison () + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("livraison ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerLivraison(String nom_livreur){
       
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
       String requete = "delete from livraison where nom_livreur=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,nom_livreur);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(LivraisonServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierLivraison(Livraison l, String nom_livreur) {
        	 
		 try{
            
        String req2 = "UPDATE `livraison` SET ``num_l`=?,`tel_livreur`=?,`date_livraison`=?  WHERE nom_livreur=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           st.setString(1, l.getNum_l());
            st.setString(2, l.getNom_livreur());
            st.setString(3,l.getTel_livreur());
             st.setString(4,l.getDate_livraison());
            

            st.setString(5,nom_livreur);
                st.executeUpdate();
                
                 System.out.println("livraison mis à jour avec succès !");
                 System.out.println(l.toString());
        }catch (SQLException ex) {
            System.out.println(l.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(LivraisonServices.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Livraison> afficher() {
        List<Livraison> list = new ArrayList<>();
        
        try {
            String req = "SELECT num_l , nom_livreur, tel_livreur, date_livraison FROM livraison ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Livraison( rs.getString("num_l"),rs.getString("nom_livreur"),rs.getString("tel_livreur"),rs.getString("date_livraison")));
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
   public int modifier (Livraison l){
String sq13="UPDATE `livraison` SET `num_l`=?,`nom_livreur`=?,`tel_livreur`=?,`date_livraison`=?WHERE nom_livreur =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, l.getNum_l());
            pst.setString(2, l.getNom_livreur());
                        pst.setString(3, l.getTel_livreur());

            pst.setString(4, l.getDate_livraison());
                      

                        pst.setString(5, l.getNom_livreur());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(LivraisonServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
   public List<Livraison> ListClasse1(String l ) {
        List<Livraison> Mylist = new ArrayList<>();
        try {
            String requete = "select num_l,nom_livreur,tel_livreur,date_livraison from livraison where nom_livreur LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, l);
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Livraison pre = new Livraison();
              
            pre.setNum_l(e.getString("num_l"));
            pre.setNom_livreur(e.getString("nom_livreur"));
            pre.setTel_livreur(e.getString("tel_livreur"));
            pre.setDate_livraison(e.getString("date_livraison"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

   

  
}
