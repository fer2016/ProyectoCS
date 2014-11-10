import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.diegorivas.db.MySQL;

@ManagedBean(name="usuario")
@SessionScoped
public class Usuario implements Serializable{
    private String userName;
    private String password;
    private String tipo;
    private MySQL mySQL3;
    
    public Usuario() {   
         mySQL3= new MySQL("localhost","root","",3306,"registro");
        mySQL3.conectar();
    }

    public Usuario(String userName, String password, String tipo) {
        this.userName = userName;
        this.password = password;
        this.tipo = tipo;
    }
    
    public void guardarUsuario(){
        String agregarUsuario = "INSERT into usuario (user_name,password,tipo) "
            + "VALUES ('"+userName+"','"+password+"','"+tipo+"')";
        try {
            mySQL3.getSentencia().execute(agregarUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void showMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación de Usuario ", "¡Se guardó el usuario!"));
     
    }
     
      public void showMessage2(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar usuario  ", "¡Se eliminaron los usuarios!"));
     
    }
    
    
   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
