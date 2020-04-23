package tarefa04;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Provincia")
public class Provincia implements Serializable {
    
    ArrayList <Provincia> provincias = new ArrayList<>();
    Provincia provinciaAux;
    String FILENAME = new String ("provincias.json"); 
    File arquivo = new File (FILENAME);

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

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = provincias;
    }
    
    public void lerProvincias(){    
        try{
            FileReader fluxoDatos = new FileReader(arquivo);
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            buferEntrada.close();

            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            Provincia p = gson.fromJson(json, Provincia.class);
            System.out.println("Provincias: " );
            for (int i = 0; i < p.provincias.size(); i++){
                provinciaAux = p.getProvincias().get(i);
                System.out.println(provinciaAux.getId() + " " + provinciaAux.getNome());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void insertProvincias(){
            try{
            FileReader fluxoDatos = new FileReader(arquivo);
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            buferEntrada.close();

            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            Provincia p = gson.fromJson(json, Provincia.class);
            //System.out.println("Provincias: " );
            for (int i = 0; i < p.provincias.size(); i++){
                provinciaAux = p.getProvincias().get(i);
                Transaction tran = null;
                try{
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    tran = session.beginTransaction();
                    session.save(provinciaAux);
                    tran.commit();
                    session.close();
                }catch (HibernateException e){
                    e.printStackTrace();
                }
            }
            }catch(Exception e){
                System.out.println(e.getMessage());
        }       
    }    
    
    public int selProvincia(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o código da provincia que desexas seleccionar: ");
        Transaction tran = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("SELECT p FROM Provincia p");
            List<Provincia> provincias = q.list();

            for (Provincia provinciaAux : provincias) {
                 System.out.println(provinciaAux);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return teclado.nextInt();
    }
    
    public Provincia getProvinciaSeleccionada(int codigoProvincia){
        if (provincias.contains(id)){
            return new Provincia();
        }
        return null;
    }
}
