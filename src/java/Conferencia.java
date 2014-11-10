import java.io.Serializable;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

/**
 *
 * @author fer
 */
@ManagedBean(name="conferencia")
@RequestScoped
public class Conferencia implements Serializable {
    private MySQL m;
    private String tipo;
    private String autor;   
    private Date fecha;
    private String titulo;
    private String nombreEvento;
    private String lugar;
    private String universidades;
    private String detalle;
    private String imagen;
      private final String[] tipos= new String[] {"Lección Inaugural de Programa Académico","Lección Inaugural de Evento",
                                                    "Conferencia como Invitado Especial","Presentación de Libro","Cine-foro","Orador Invitado en Clase",
                                                    "Ponencia Evento Nacional","Ponencia Evento Internacional","Mesa de Discusión","Póster Académico",
                                                    "Conferencia de Prensa"}; 
    private ArrayList<String> b = new ArrayList();
    

    public Conferencia() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
        Collections.addAll(b, tipos);
    }

    public Conferencia(String tipo, String autor, Date fecha, String titulo, String nombreEvento, String lugar, String universidades, String detalle, String imagen) {
        this.tipo = tipo;
        this.autor = autor;
        this.fecha = fecha;
        this.titulo = titulo;
        this.nombreEvento = nombreEvento;
        this.lugar = lugar;
        this.universidades = universidades;
        this.detalle = detalle;
        this.imagen = imagen;
    }
 
         
    public void guardarConferencia(){
        String agregarConferencia = "INSERT into conferencia (tipo,autor,fecha,titulo,nombreEvento,lugar,universidades,detalle,imagen) "
            + "VALUES ('"+tipo+"','"+autor+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+titulo+"','"+nombreEvento+"','"+lugar+"','"+universidades+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarConferencia);
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Conferencia", "¡Se guardó la conferencia!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public ArrayList<String> getB() {
        return b;
    }

    public void setB(ArrayList<String> b) {
        this.b = b;
    }
    
   
    public void setM(MySQL m) {
        this.m = m;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getUniversidades() {
        return universidades;
    }

    public void setUniversidades(String universidades) {
        this.universidades = universidades;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    

    
}
