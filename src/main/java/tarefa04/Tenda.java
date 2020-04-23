package tarefa04;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Tenda")
public class Tenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int idTenda;
    @Column(name = "nome")
    protected String nome;
    @Column(name = "cidade")
    protected String cidade;
    @ManyToOne
    @JoinColumn(name = "idProvincia")
    protected Provincia provincia;
    @OneToMany(mappedBy = "tenda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<HorasEmpregado> horasEmpregado;
    @OneToMany(mappedBy = "tenda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<StockTenda> stockTenda;

    public Tenda() {
    }
    
    public Tenda(String nome, String cidade, Provincia provincia) {
        this.nome = nome;
        this.cidade = cidade;
        this.provincia = provincia;
        this.horasEmpregado = new HashSet<>();
        this.stockTenda = new HashSet<>();
    }

    public void addHoras(HorasEmpregado e) {
        this.horasEmpregado.add(e);
    }

    public void addUnds(StockTenda e) {
        this.stockTenda.add(e);
    }

    @Override
    public String toString() {
        return "(" + this.idTenda + ") " + this.nome + " " + this.cidade + " " + this.provincia;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdtenda() {
        return this.idTenda;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public Provincia getProvincia(Provincia p){
        p.lerProvincias();
        p.selProvincia();
        return this.provincia;
    }
    
    public void datosTenda(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome da tenda: ");
        this.nome = teclado.nextLine();
        System.out.println("Cidade da tenda: ");
        this.cidade = teclado.nextLine();     
    }
    
   /* public void addTenda(){
        datosTenda();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();

            Transaction tran = null;

            Tenda t1 = new Tenda(getNome(),getCidade(),getProvincia());
            tran = session.beginTransaction();
            session.save(t1);
            tran.commit();
            session.close();
                

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/
    
    public void listTendas(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT t FROM Tenda t");
            List<Tenda> tendas = q.list();

            for (Tenda tendaAux : tendas) {
                 System.out.println(tendas.toString());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    

}
