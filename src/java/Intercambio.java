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

@ManagedBean(name="intercambio")
@RequestScoped
public class Intercambio implements Serializable {
    private MySQL m;
    private String tipo;
    private String asistentes;
    private Date fecha;
    private String nombre;
    private String pais;
    private String sede;
    private String institucion;
    private String donante;
    private String detalle;
    private String imagen;
    private final String[] tipos= new String[] {"Curso nacional recibido por docentes e investigadores",
                                                "Curso internacional recibido por docentes e investigadores","Estudiante UVG de intercambio",
                                                "Docente / investigador UVG de intercambio","Graduado Becado para Estudios de Posgrado",
                                                "Docente / Investigador Becado para Estudios de Posgrado","Docente / Investigador Becado para Investigación"}; 
    private ArrayList<String> b = new ArrayList();

    public Intercambio() {
        m= new MySQL("localhost","root","",3306,"registro");
        m.conectar();
        Collections.addAll(b, tipos);
    }

    public Intercambio(String tipo, String asistentes, Date fecha, String nombre, String pais, String sede, String institucion, String donante, String detalle, String imagen) {
        this.tipo = tipo;
        this.asistentes = asistentes;
        this.fecha = fecha;
        this.nombre = nombre;
        this.pais = pais;
        this.sede = sede;
        this.institucion = institucion;
        this.donante = donante;
        this.detalle = detalle;
        this.imagen = imagen;
    }

 
    
    public void guardarIntercambio(){
        String agregarIntercambio = "INSERT into intercambio (tipo,asistentes,fecha,nombre,pais,sede,institucion,donante,detalle,imagen) "
            + "VALUES ('"+tipo+"','"+asistentes+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+nombre+"','"+pais+"','"+sede+"','"+institucion+"','"+donante+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarIntercambio);
        } catch (SQLException ex) {
            Logger.getLogger(Intercambio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Intercambio/Capacitación", "¡Se guardó el intercambio/capacitacion!"));
     
    }

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
    }

    public String getAsistentes() {
        return asistentes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getB() {
        return b;
    }

    public void setB(ArrayList<String> b) {
        this.b = b;
    }
    
    public void setAsistentes(String asistentes) {
        this.asistentes = asistentes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDonante() {
        return donante;
    }

    public void setDonante(String donante) {
        this.donante = donante;
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
