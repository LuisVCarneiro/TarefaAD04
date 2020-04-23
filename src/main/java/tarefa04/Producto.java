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
@Table(name = "Producto")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProducto;

   
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<StockTenda> stockTenda;

    public Producto(String nome) {
        this.nome = nome;
        this.stockTenda = new HashSet<>();
    }

    public Producto() {
    }

    public void addHoras(StockTenda t) {
        this.stockTenda.add(t);
    }

    public String getName() {
       return this.nome;
    }
     public int getIdProducto() {
        return idProducto;
    }
}

