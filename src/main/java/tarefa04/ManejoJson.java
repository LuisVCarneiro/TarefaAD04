package tarefa04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import static java.io.File.separatorChar;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManejoJson {

    private static final String rutaJsonProvincias = System.getProperty("user.dir") + separatorChar + "Provincias.json";
    private static final String rutaJsonConfig = System.getProperty("user.dir") + separatorChar + "config.json";
    private static final String rutaJsonStock = System.getProperty("user.dir") + separatorChar + "stock.json";

    public static void leerJsonProvincias() {

        //leemos el json
        Gson gson = new Gson();
        java.lang.reflect.Type tipoProvincias = new TypeToken<ArrayList<Provincia>>() {
        }.getType();

        // serializamos el archivo en un array de objetos provincia
        if (new File(rutaJsonProvincias).exists()) {

            ArrayList<Provincia> provincias = gson.fromJson(leerString(new File(rutaJsonProvincias)), tipoProvincias);

            // introducimos las provincias en la BD
            Transaction tran = null;
            try {
                //Collemos a sesión de Hibernate
                Session session = HibernateUtil.getSessionFactory().openSession();

                //Comenzamos unha transacción
                tran = session.beginTransaction();

                for (Provincia e : provincias) {
                    session.saveOrUpdate(e);
                }

                //Facemos un commit da transacción
                tran.commit();

            } catch (HibernateException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    public static LinkedTreeMap leerdbConnection() {
        Gson gson = new Gson();
        LinkedTreeMap configuracion = null;
        if (new File(rutaJsonConfig).exists()) {
            Map jsonJavaRootObject = gson.fromJson(leerString(new File(rutaJsonConfig)), Map.class);
            configuracion = (LinkedTreeMap) jsonJavaRootObject.get("dbConnection");
        }
        return configuracion;
    }

    public static LinkedTreeMap leerhibernate() {
        Gson gson = new Gson();
        LinkedTreeMap configuracion = null;
        if (new File(rutaJsonConfig).exists()) {
            Map jsonJavaRootObject = gson.fromJson(leerString(new File(rutaJsonConfig)), Map.class);
            configuracion = (LinkedTreeMap) jsonJavaRootObject.get("hibernate");
        }
        return configuracion;
    }

    public static String leerString(File archivo) {
        String entrada = "";
        try {
            InputStream stream = new FileInputStream(archivo);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");

            int caracter;
            while ((caracter = reader.read()) != -1) {
                entrada += (char) caracter;
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero");
        }
        return entrada;
    }

    // ToJSon
    public static void escribirJson() {

        FileWriter fluxoDatos = null;
        try {
            File fileStock = new File(rutaJsonStock);
            
            if (!fileStock.exists()){
                fileStock.createNewFile();
            }
            
            String texto = "";
            String qHQL = "SELECT new Map(p.idProducto, t.idTenda, st.unidades) FROM Producto as p "
                    + "INNER JOIN StockTenda as st ON st.producto = p.idProducto "
                    + " INNER JOIN Tenda as t ON t.idTenda = st.tenda";
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query consultaHQL = session.createQuery(qHQL);
            ArrayList<Map> respuestaHQL = (ArrayList<Map>) consultaHQL.getResultList();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            texto += gson.toJson(respuestaHQL);

            //Creamos o fluxo de saida
            fluxoDatos = new FileWriter(fileStock);

            //Transformamos a cadea nun array de caracteres
            char[] caracteres = texto.toCharArray();
            //Imos escribindo caracter a caracter
            for (int i = 0; i < caracteres.length; i++) {
                fluxoDatos.write(caracteres[i]);
            }
            //Cerramos o arquivo
            fluxoDatos.close();

        } catch (IOException ex) {
            Logger.getLogger(ManejoJson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fluxoDatos.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
