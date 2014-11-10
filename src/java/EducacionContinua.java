import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

@ManagedBean(name="educacionContinua")
@RequestScoped
public class EducacionContinua implements Serializable {
    private MySQL m;
    private String nombre;
    private Date fecha;
    private String docente;
    private int estudiantes;
    private int ingresos;
    private String detalle;
    private String imagen;
   
    public EducacionContinua() {
         m= new MySQL("localhost","root","",3306,"registro");
         m.conectar();
    }

    public EducacionContinua(String nombre, Date fecha, String docente, int estudiantes, int ingresos, String detalle, String imagen) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.docente = docente;
        this.estudiantes = estudiantes;
        this.ingresos = ingresos;
        this.detalle = detalle;
        this.imagen = imagen;
    }
    
    
    public void guardarEducacionContinua(){
        String agregarEducacionContinua = "INSERT into educacioncontinua (nombre,fecha,docente,estudiantes,ingresos,detalle,imagen)"
            + "VALUES ('"+nombre+"','"+(fecha.getYear()+1900)+"/"+fecha.getMonth()+"/"+fecha.getDay()+"','"+docente+"','"+estudiantes+"','"+ingresos+"','"+detalle+"','"+imagen+"')";
        try {
            m.getSentencia().execute(agregarEducacionContinua);
        } catch (SQLException ex) {
            Logger.getLogger(EducacionContinua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de Educacion Continua", "¡Se guardó Educacion Continua!"));
     
    }
    

    public MySQL getM() {
        return m;
    }

    public void setM(MySQL m) {
        this.m = m;
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

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(int estudiantes) {
        this.estudiantes = estudiantes;
    }

    public int getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
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
