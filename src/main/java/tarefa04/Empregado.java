package tarefa04;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Empregado")
public class Empregado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpregado;
    @Column(name = "nome")
    private String nome;
    @Column(name = "Apellidos")
    private String apellidos;
    @OneToMany(mappedBy = "empregado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HorasEmpregado> horasEmpregado;
    

    public Empregado (String nome, String Apellidos) {
        this.nome = nome;
        this.apellidos = Apellidos;
        this.horasEmpregado = new HashSet<>();
    }

    public Empregado() {

    }

    public void addHoras(HorasEmpregado horas) {
      this.horasEmpregado.add(horas);
    }

    public String getName() {
        return this.nome + " " + this.apellidos;
    }
     
    public int getIdempregado() {
        return this.idEmpregado;
    }

    @Override
    public String toString() {
        return "(" + this.idEmpregado + ") " + this.nome +  " " + this.apellidos ; 
    } 
 
}
