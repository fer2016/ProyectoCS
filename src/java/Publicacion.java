import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

/**
 *
 * @author fer
 */
@ManagedBean(name="publicacion")
@SessionScoped
public class Publicacion implements Serializable{
    private MySQL mySQL2;
    private String nombre;
    private String tipo;
    private Date fecha;
    private String personas;  
    private String detalle;
    private String archivo;
    private final String[] tipos= new String[] {"Revisión por pares Guatemala","Revisión por pares Centroamericana",
                                                "Revisión por pares Internacional","Artículo de opinión en Revista Académica","Libro","Capítulo de Libro",
                                                "Ponencia publicada en Memoria Oficial de Evento","Tesis","Reseña","Columna de opinión o artículo periodístico",
                                                "Entrevista dada a medios de comunicación","Nota de prensa escrito por terceros sobre actividad UVG","Otro"}; 
    private ArrayList<String> b = new ArrayList();

 

    public Publicacion() {
        mySQL2= new MySQL("localhost","root","",3306,"registro");
        mySQL2.conectar();
        Collections.addAll(b, tipos);
        
    }

    public Publicacion(String nombre, String tipo, Date fecha, String personas, String detalle, String archivo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.personas = personas;
        this.detalle = detalle;
        this.archivo = archivo;
    }

    
    
    public void guardarPublicacion(){
        String agregarPublicacion = "INSERT into publicacion (nombre,tipo,fecha,personas,detalle,archivo) "
            + "VALUES ('"+nombre+"','"+tipo+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+personas+"','"+detalle+"','"+archivo+"')";
        try {
            mySQL2.getSentencia().execute(agregarPublicacion);
        } catch (SQLException ex) {
            Logger.getLogger(Publicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     public void showMessage(){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardó la publicación", "¡Se guardó correctamente!!"));
    
    }

   
    public ArrayList<String> getB() {
        return b;
    }

    public void setB(ArrayList<String> b) {
        this.b = b;
    }
   

    public MySQL getMySQL() {
        return mySQL2;
    }

    public void setMySQL(MySQL mySQL) {
        this.mySQL2 = mySQL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
}
