import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.joseflores.db.MySQL;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author fer
 */
@ViewScoped
@ManagedBean(name= "tablebean")

public class TableBean implements Serializable {
    private List<Actividad> activities;
    private List<Actividad> droppedActivities;
    private Actividad selectedActivity;
    private MySQL conexionMySQL;

    @PostConstruct
    public void init() {      
        activities= new ArrayList<>();     
        droppedActivities= new ArrayList<>(); 
        añadirActividades();
       
      
    }
        
    public void añadirActividades(){
        conexionMySQL = new MySQL("localhost", "root", "", 3306, "registro");
        conexionMySQL.conectar();
        ResultSet resultado = conexionMySQL.consulta("SELECT * FROM actividad");  
        try{    
            while(resultado.next()){
                activities.add(new Actividad(
                        resultado.getString("nombre"),
                        resultado.getString("tipo"),
                        resultado.getDate("fecha"),
                        resultado.getString("personas"),
                        resultado.getString("detalle"),
                        resultado.getString("imagen")));      
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }
      
    public void onActivityDropped(DragDropEvent ddEvent){
        Actividad actividad= ((Actividad)ddEvent.getData());
        droppedActivities.add(actividad);
        activities.remove(actividad);             
    }

    public List<Actividad> getActivities() {
        return activities;
    }

    public List<Actividad> getDroppedActivities() {
        return droppedActivities;
    }

    public Actividad getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(Actividad selectedActivity) {
        this.selectedActivity = selectedActivity;
    }
       
}
