package cr.ucenfotec.bl.Cliente;

import cr.ucenfotec.bl.Productos.Producto;
import cr.ucenfotec.bl.Tienda.Tienda;

import java.util.ArrayList;

public class ColaClientes {

    private ArrayList<Cliente> cola;

    public ColaClientes(){
        cola = new ArrayList<>();
    }

    public void encolar(Cliente nuevoCliente){

        int posicion = cola.size();

        for (int i = 0; i < cola.size(); i++){

            int prioridadActual = cola.get(i).getPrioridad();

            if(prioridadActual < nuevoCliente.getPrioridad()){
                posicion = i;
                break;
            }
        }

        cola.add(posicion, nuevoCliente);
    }

    public Cliente desencolar(){
        if(estaVacia()){
            return  null;
        }

        return cola.removeFirst();
    }

    public Cliente verPrimero(){
        if(estaVacia()){
            return null;
        }

        return cola.getFirst();
    }

    private boolean estaVacia(){
        return cola.isEmpty();
    }

    public void atenderCliente(Tienda tienda){

        Cliente cliente = desencolar();

        if(cliente == null){
            System.out.println("No hay clientes en la cola");
            return;
        }

        cliente.generarFactura();

        Producto temp = cliente.getCarrito().getPrimero();

        while(temp != null){
            tienda.reducirStock(temp.getNombre(), temp.getCantidad());
            temp = temp.getSiguiente();
        }

        System.out.println("Cliente atendido correctamente.");
    }
}
