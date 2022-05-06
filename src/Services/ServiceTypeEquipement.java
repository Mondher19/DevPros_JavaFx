/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.ServiceEquipement.cn;
import entities.Equipement;
import entities.TypeEquipement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.Myconn;

/**
 *
 * @author Mondh
 */
public class ServiceTypeEquipement {
    
     
    static Connection cn = Myconn.getInstance().getConnection();
 
    
       
        public  void ajoutTypeEquipement(TypeEquipement  l) 
       {           
           
           try {
               String requete="INSERT INTO categorie2eq (name,image) values ('"+l.getName_et()+"','"+l.getImage_et()+"')";
              
              Statement st;
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        
  
        public int getIdCommentaire(String desc) {
        try {
            String req ="SELECT id from categorie2eq WHERE name ='"+desc+"'";
            Statement st;
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;        
    }
        
        
         
            public  void supprimerTypeEquipement(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from categorie2eq where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
                       
    public void updateTypeEquipement(TypeEquipement l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="UPDATE categorie2eq SET name='"+l.getName_et()+"' , image='"+l.getImage_et()+"'  where id='"+id+"' ";
          
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les categorie
    
     
        public List<TypeEquipement> listerCat(){
        List<TypeEquipement> ListR = new ArrayList();
        
        try {
            String req = "Select * from `categorie2eq`";
            Statement statement = cn.createStatement();
            ResultSet rst = statement.executeQuery(req);
             
            while(rst.next()){
                 
                  TypeEquipement p = new TypeEquipement(rst.getInt("id"),rst.getString("name"),rst.getString("image"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }
             
            
    public void afficherListCat()
            {
               List<TypeEquipement> arrayList = listerCat();
        for(TypeEquipement m : arrayList){
           System.out.println(m.getName_et()+","+m.getImage_et());
        }
            
            }  
    
}
