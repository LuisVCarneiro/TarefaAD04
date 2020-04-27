package tarefa04;

import java.io.Serializable;
import java.util.HashMap;
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

    HashMap <Integer, Cliente> mapaClientes = new HashMap <>();
    
    public Cliente() {

    }
    
    public String toString(){
        return "Cliente: Id: " + id + ", nome: " + nome + ", apelidos: " + apelidos + ", email: " + email;
    }

    public String getNome() {
        return this.nome;
    }
    
    public int getId(){
    return this.id;
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
            List <Cliente> clientes = q.list();

            for (Cliente cliente : clientes) {
                 System.out.println(cliente.toString());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
    public int selectCliente(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o id do cliente que desexas seleccionar");
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT c FROM Cliente c");
            List <Cliente> clientes = q.list();
            for (Cliente cliente : clientes) {
                 System.out.println(cliente.toString());
                 int key = cliente.getId();
                 mapaClientes.put(key, cliente);        
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return teclado.nextInt();
     }
    
    public Cliente getClienteSeleccionado(int idClienteSeleccionado){
        return mapaClientes.get(idClienteSeleccionado);
    }
    
    
    public void deleteCliente(){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tran = null;
            int idClienteSeleccionado = selectCliente();
            Cliente clienteBorrar = getClienteSeleccionado(idClienteSeleccionado);
            
            tran = session.beginTransaction();
            session.remove(clienteBorrar);
            tran.commit();
            session.close();  
            System.out.println("O cliente " + idClienteSeleccionado + " foi eliminado.");
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Non se eliminou o cliente");
        }
    }
}
