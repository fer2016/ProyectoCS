import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
@ManagedBean(name="eventos")
@RequestScoped
public class Eventos implements Serializable{
    private MySQL m;
    private String organizadorPrincipal;
    private String otrosOrganizadores;
    private String nombre;
    private String tipo;
    private Date fecha;
    private String lugar;
    private String universidades;
    private String detalle;
    private String imagen;  
    private final String[] tipos= new String[] {"Internos UVG","Nacionales","Internacionales"}; 
    private ArrayList<String> b = new ArrayList();

    public Eventos() {
         m= new MySQL("localhost","root","",3306,"registro");
         m.conectar();
         Collections.addAll(b, tipos);
        
    }

    public Eventos(String organizadorPrincipal, String otrosOrganizadores, String nombre, String tipo, Date fecha, String lugar, String universidades, String detalle, String imagen) {
        this.organizadorPrincipal = organizadorPrincipal;
        this.otrosOrganizadores = otrosOrganizadores;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.universidades = universidades;
        this.detalle = detalle;
        this.imagen = imagen;
    }

   
    
     public void guardarEvento(){
        String agregarEvento = "INSERT into evento (organizadorPrincipal,otrosOrganizadores,tipo,nombre,fecha,lugar,universidades,detalle,imagen) "
            + "VALUES ('"+organizadorPrincipal+"','"+otrosOrganizadores+"','"+tipo+"','"+nombre+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"
            +lugar+"','"+universidades+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarEvento);
        } catch (SQLException ex) {
            Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de evento organizado", "¡Se guardó el evento!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    public void setM(MySQL m) {
        this.m = m;
    }

    public ArrayList<String> getB() {
        return b;
    }

    public void setB(ArrayList<String> b) {
        this.b = b;
    }
    
    
    

    public String getOrganizadorPrincipal() {
        return organizadorPrincipal;
    }

    public void setOrganizadorPrincipal(String organizadorPrincipal) {
        this.organizadorPrincipal = organizadorPrincipal;
    }

    public String getOtrosOrganizadores() {
        return otrosOrganizadores;
    }

    public void setOtrosOrganizadores(String otrosOrganizadores) {
        this.otrosOrganizadores = otrosOrganizadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
