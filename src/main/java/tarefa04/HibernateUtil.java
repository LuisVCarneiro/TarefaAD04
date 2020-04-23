package tarefa04;


import java.util.Properties;
import static tarefa04.ManejoJson.leerdbConnection;
import static tarefa04.ManejoJson.leerhibernate;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        String confHoraria = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
         if(sessionFactory == null){
            try{
                Configuration conf = new Configuration();
                
                Properties settings = new Properties();
                
               /* settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,"jdbc:mysql://192.168.56.101:3306/hibernate?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                settings.put(Environment.USER,"userhibernate");
                settings.put(Environment.PASS,"abc123.");
                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.HBM2DDL_AUTO,"create");
                settings.put(Environment.SHOW_SQL,"true");*/
               
                settings.put(Environment.DRIVER, leerhibernate().get("driver"));
                settings.put(Environment.URL, "jdbc:mysql://" + leerdbConnection().get("address") + ":" + leerdbConnection().get("port") + "/" + leerdbConnection().get("name") +  confHoraria);
                settings.put(Environment.USER, leerdbConnection().get("user"));
                settings.put(Environment.PASS, leerdbConnection().get("password"));
                settings.put(Environment.DIALECT, leerhibernate().get("dialect"));
                settings.put(Environment.HBM2DDL_AUTO, leerhibernate().get("HBM2DDL_AUTO"));
                settings.put(Environment.SHOW_SQL, leerhibernate().get("SHOW_SQL"));
                conf.setProperties(settings);
                
                conf.addAnnotatedClass(Provincia.class);
                conf.addAnnotatedClass(Tenda.class);
                conf.addAnnotatedClass(Cliente.class);
                conf.addAnnotatedClass(Empregado.class);
                conf.addAnnotatedClass(HorasEmpregado.class);
                conf.addAnnotatedClass(StockTenda.class);
                conf.addAnnotatedClass(Producto.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
