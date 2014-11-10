import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.diegorivas.db.MySQL;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author fer
 */
@SessionScoped
@ManagedBean(name= "tablebean3")

public class TableBean3 implements Serializable {
    private List<Usuario> usuarios;
    private List<Usuario> droppedUsuarios = new ArrayList<>();
    private Usuario selectedUsuario;
    private MySQL conexionMySQL;
   

   @PostConstruct
    public void init(){      
        usuarios= new ArrayList<>();     
       
        añadirUsuarios();
      
    }
        
    public void añadirUsuarios(){
        conexionMySQL = new MySQL("localhost", "root", "", 3306, "registro");
        conexionMySQL.conectar();
        ResultSet resultado = conexionMySQL.consulta("SELECT * FROM usuario");  
        try{    
            while(resultado.next()){
                usuarios.add(new Usuario(
                        resultado.getString("user_name"),
                        resultado.getString("password"),
                        resultado.getString("tipo")));      
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }
      
    public void onActivityDropped(DragDropEvent ddEvent){
        Usuario usuario= ((Usuario)ddEvent.getData());
        droppedUsuarios.add(usuario);
        usuarios.remove(usuario);             
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getDroppedUsuarios() {
        return droppedUsuarios;
    }

    public void setDroppedUsuarios(List<Usuario> droppedUsuarios) {
        this.droppedUsuarios = droppedUsuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    
    public void eliminarUsuarios(){
        for(Usuario u : droppedUsuarios){
                conexionMySQL.ejecutarSentencia("DELETE FROM usuario WHERE user_name= '" + u.getUserName() + "'");
        }
        
        
    }
   
    
}
