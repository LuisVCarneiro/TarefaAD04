package tarefa04;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "StockTenda")
public class StockTenda implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private  Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "idTenda")
    private   Tenda tenda;

    @Column(name = "Unidades")
    private int unidades;

    public StockTenda(Producto producto, Tenda tenda, int unidades) {
        this.producto = producto;
        this.tenda = tenda;
        this.unidades = unidades;
    }

    public StockTenda() {
    }

    public Producto getProducto() {
        return producto;
    }

    public Tenda getTenda() {
        return tenda;
    }

    public int getUnidades() {
        return unidades;
    }   
}