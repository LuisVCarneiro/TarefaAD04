package tarefa04;


import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.query.Query;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "apelidos")
    private String apelidos;
    @Column(name = "email")
    private String email;

    
    public Cliente(String nome, String apelidos, String email) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.email = email;
    }

    public Cliente() {

    }
    
     @Override
    public String toString() {
        String tenda = "(" + this.id  + ") " + this.nome  + " " + this.apelidos+ " " + this.email ;
        return tenda;
    }

    public String getNome() {
        return this.nome;
    }
    
    public String getApelido(){
        return this.apelidos;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void datosCliente(){  
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome do cliente: ");
        this.nome = teclado.nextLine();
        System.out.println("Apelidos do cliente: ");
        this.apelidos = teclado.nextLine();
        System.out.println("Email do cliente: ");
        this.email = teclado.nextLine();
    }
    
    public void addCliente(){
        datosCliente();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();

            Transaction tran = null;

            Cliente c1 = new Cliente(getNome(),getApelido(),getEmail());
            tran = session.beginTransaction();
            session.save(c1);
            tran.commit();
            session.close();
                

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
    public void listClientes(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT c FROM Cliente c");
            List<Cliente> clientes = q.list();

            for (Cliente cliente : clientes) {
                 System.out.println(cliente.toString());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
    public String selectCliente(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o nome do cliente que desexas seleccionar");
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT x FROM Cliente x");
            List<Cliente> clientes = q.list();

            for (Cliente cliente : clientes) {
                 System.out.println(cliente.getNome());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return teclado.nextLine();
     }
    
    public Cliente getClienteSeleccionado(String nomeCliente){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT x FROM Cliente x");
            List <Cliente> clientes = q.list();
            }catch (HibernateException e){
            e.printStackTrace();
        }
        return new Cliente();
    }
    
    
    public void deleteCliente(Cliente c1){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();   
            Transaction tran = null;           
            tran = session.beginTransaction();
            session.delete(c1);
            tran.commit();
            session.close();
            
        }catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
