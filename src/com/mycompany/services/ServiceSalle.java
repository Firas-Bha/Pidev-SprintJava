
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Salle;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeArray.map;
/**
 *
 * @author ASUS
 */
public class ServiceSalle {
    public static ServiceSalle instance=null;
    
//initialise connection request 
    private ConnectionRequest req;
    
    public static ServiceSalle getInstance(){
        if(instance==null)
            instance = new ServiceSalle();
            return instance;
    }
    
    public ServiceSalle () {
        req= new ConnectionRequest();          
    }
        
    public void AjouterSalle(Salle salle){
        String url= Statics.BASE_URL+"/addjson?Id="+salle.getId()+"&Surface="+salle.getSurface()+
                "&NomS="+salle.getNomS()+"&CapaciteS="+salle.getCapaciteS()+"&nbCoursMaxS="+salle.getNbCoursMaxS()+
                "&description="+salle.getDescription();
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());//reponse  json
            System.out.println("data =="+str);
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req); //execution de req
        
    }
    
    public ArrayList<Salle>AfficherSalle(){
        ArrayList<Salle> result =new ArrayList<>();
        String url= Statics.BASE_URL+"/get";
       req.setUrl(url);
       
      req.addResponseListener(new ActionListener<NetworkEvent> (){
            @Override
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp= new JSONParser();
                try{
                    Map<String,Object> mapSalle=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listsOfMaps= (List<Map<String,Object>>) mapSalle.get("root");
                    for( Map<String,Object> obj: listsOfMaps ){
                        Salle sa= new Salle();
                        float Id=Float.parseFloat(obj.get("Id").toString());
                        float Surface=Float.parseFloat(obj.get("Surface").toString());
                        String NomS=obj.get("NomS").toString();
                        float CapaciteS=Float.parseFloat(obj.get("CapaciteS").toString());
                        float nbCoursMaxS=Float.parseFloat(obj.get("nbCoursMaxS").toString());
                        String description=obj.get("description").toString();
                        
                        sa.setId((int)Id);
                        sa.setSurface((float)Surface);
                        sa.setNomS(NomS);
                        sa.setCapaciteS((int)CapaciteS);
                        sa.setNbCoursMaxS((int)nbCoursMaxS);
                        sa.setDescription(description);
                        
                        //date
                        //String DateConverter= obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10 , obj.get("obj").toString().lastIndexOf("}"));
                        //Date currenteTime =new Date(Double.valueOf(DateConverter).longValue()*1000);
                        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //String dateString = formatter.format(currenteTime);
                        //re.setDateC(dateString);
                        
                           // insert data into aarraylist result
                           result.add(sa);
                    }
                
                }
                catch(Exception ex){
                  
                    ex.printStackTrace();
                }
              //  throw new UnsupportedOperationException("Not supported yet.");
            }
      });    
     NetworkManager.getInstance().addToQueueAndWait(req); //execution de req
     return result;
    }

    
    
}

