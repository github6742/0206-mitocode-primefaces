/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Persona;

@ManagedBean
@ApplicationScoped
public class PersonaBean {
    
    private List<Persona> lista = new ArrayList();

    public List<Persona> getLista() {
        return lista;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }
    
    public void listar(){
        Persona p = new Persona();
        p.setNombre("Sergio");
        p.setEdad(49);
        lista.add(p);
        p = new Persona();
        p.setNombre("Gustavo");
        p.setEdad(52);
        lista.add(p);
    }
    
}
