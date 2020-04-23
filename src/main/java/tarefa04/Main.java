package tarefa04;

import java.util.Scanner;
import LecturaXML.LeerXML;

public class Main {
    
    public static void main (String [] args){
        Scanner teclado = new Scanner (System.in);
        Cliente cliente = new Cliente();
        Provincia provincia = new Provincia();
        Tenda t = new Tenda();
        Producto p = new Producto();
        Empregado e = new Empregado();
        provincia.insertProvincias();

        boolean salir = false;
        while (!salir){
            System.out.println("------ MENU PRINCIPAL ------");
            System.out.println("1: Engadir tenda. \n2: Mostrar tendas. \n3: Eliminar tenda"
                    + "\n4: Engadir un producto. \n5: Mostrar productos da fanquicia. \n6: Mostrar productos da tenda."
                    + "\n7: Engadir un producto a unha tenda. \n8: Actualizar stock de productos da tenda. "
                    + "\n9: Mostrar o stock de productos dunha tenda. \n10: Eliminar un producto dunha tenda. "
                    + "\n11: Eliminar un producto. \n12: Engadir un cliente. \n13: Mostrar os clientes."
                    + "\n14: Eliminar un cliente. \n15: Xerar un informe json co stock de todos os productos."
                    + "\n16: Leer o periódico. \n17: Sair da aplicación." );
            int opcion = teclado.nextInt();
            switch(opcion){
                case 1:
                    //t.addTenda();
                    break;
                case 2:
                    int codProvincia = provincia.selProvincia();
                    Provincia prov = provincia.getProvinciaSeleccionada(codProvincia);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    cliente.addCliente();
                    break;
                case 13:
                    cliente.listClientes();
                    break;
                case 14:
                    String nomeClienteSeleccionado = cliente.selectCliente();
                    cliente.deleteCliente(cliente.getClienteSeleccionado(nomeClienteSeleccionado));
                    //cliente.listClientes();
                    System.out.println(cliente.getClienteSeleccionado(nomeClienteSeleccionado));
                    break;
                case 15:
                    ManejoJson json = new ManejoJson();
                    json.escribirJson();
                    break;
                case 16:
                    LeerXML lectura = new LeerXML();
                    lectura.leerXml();
                    break;
                case 17:
                    System.exit(0);
                    break;
            }
        }
    }
}
