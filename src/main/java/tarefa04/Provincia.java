package tarefa04;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Provincia")
public class Provincia implements Serializable {
    
    String FILENAME = new String ("provincias.json"); 
    File arquivo = new File (FILENAME);
    ArrayList <Provincia> provincias = null;
    Provincia provinciaAux;

    @Id
    @Column(name = "idProvincia")
    protected int id;
    @Column(name = "nome")
    protected String nome;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Set<Tenda> tendas;

    public Provincia(int id, String nome) {
        this.id = id;
        this.nome = nome;
        
    }

    public Provincia() {
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String provincia = id + "\t" + nome;
        return provincia;
    }

    public String getNome() {
        return this.nome;
    }
    
      public ArrayList<Provincia> getProvincias() {
        return provincias;
    }
}
