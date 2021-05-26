/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class UploadBean {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws ClassNotFoundException, SQLException {
        try {

            if (file != null) {
                Class.forName("com.mysql.jdbc.Driver");
                //Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=admin");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?user=root&password=admin");
              
                PreparedStatement st = cn.prepareStatement("INSERT INTO Imagen (img) VALUES(?)");
                //st.setBinaryStream(1, file.getInputstream());
                
                //st.setBlob(1, file.getInputstream());
                st.setBinaryStream(1, file.getInputstream(), 1000000);
                st.executeUpdate(); 
                cn.close();
                
                FacesMessage message = new FacesMessage("Exito", file.getFileName() + " fue subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e){
            FacesMessage message = new FacesMessage("Error de conexion."); 
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void uploadAdvanced(FileUploadEvent even){
        try {
            
        } catch (Exception e){
            FacesMessage message = new FacesMessage("Error de conexion.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
