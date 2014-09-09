import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.joseflores.db.MySQL;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author fer
 */
@ViewScoped
@ManagedBean(name= "tablebean2")

public class TableBean2 implements Serializable {
    private List<Publicacion> publicaciones;
    private List<Publicacion> droppedPublicaciones;
    private Publicacion selectedPublicacion;
    private MySQL conexionMySQL;

    @PostConstruct
    public void init() {      
        publicaciones= new ArrayList<>();     
        droppedPublicaciones= new ArrayList<>(); 
        añadirPublicaciones();
       
      
    }
        
    public void añadirPublicaciones(){
        conexionMySQL = new MySQL("localhost", "root", "", 3306, "registro");
        conexionMySQL.conectar();
        ResultSet resultado = conexionMySQL.consulta("SELECT * FROM publicacion");  
        try{    
            while(resultado.next()){
                publicaciones.add(new Publicacion(
                        resultado.getString("nombre"),
                        resultado.getString("tipo"),
                        resultado.getDate("fecha"),
                        resultado.getString("personas"),
                        resultado.getString("detalle"),
                        resultado.getString("archivo")));      
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }
      
    public void onActivityDropped(DragDropEvent ddEvent){
        Publicacion publicacion= ((Publicacion)ddEvent.getData());
        droppedPublicaciones.add(publicacion);
        publicaciones.remove(publicacion);             
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public List<Publicacion> getDroppedPublicaciones() {
        return droppedPublicaciones;
    }

    public Publicacion getSelectedPublicacion() {
        return selectedPublicacion;
    }

    public void setSelectedPublicacion(Publicacion selectedPublicacion) {
        this.selectedPublicacion = selectedPublicacion;
    }

    
}
