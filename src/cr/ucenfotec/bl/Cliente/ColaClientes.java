package cr.ucenfotec.bl.Cliente;

import cr.ucenfotec.bl.Productos.Producto;
import cr.ucenfotec.bl.Tienda.Tienda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

        Cliente cliente = verPrimero();

        if(cliente == null){
            System.out.println("No hay clientes en la cola");
            return;
        }

        // --- Lógica para los grafos ---
        String origen = tienda.getUbicacionTienda();
        String destino = cliente.getUbicacion();

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();

        //Aquí se ejecuta Dijkstra:
        tienda.getMapaEntregas().algoritmoDijkstra(origen, distancias, predecesores);

        if (distancias.get(destino) == null || distancias.get(destino) == Integer.MAX_VALUE) {
            System.out.println("\n--- ERROR DE ENTREGA ---");
            System.out.println("La ubicación '" + destino + "' está desconectada del mapa.");
            System.out.println("No se puede atender al cliente hasta que haya una ruta.");
            return;
        }

        System.out.println("\n=== Cliente a atender ===");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Prioridad: " + cliente.getPrioridad());

        System.out.println("\n--- Carrito ---");
        cliente.mostrarCarrito();

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n1. Confirmar compra");
            System.out.println("2. Cancelar compra");
            System.out.print("Seleccione una opcion: ");
            int opcion = Integer.parseInt(entrada.readLine());

            if(opcion == 2){
                desencolar(); // eliminar cliente de la cola
                System.out.println("Compra cancelada. Cliente eliminado de la cola.");
                return;
            }

        } catch (Exception e){
            System.out.println("Error en la entrada. Cancelando operación.");
            return;
        }

        Producto temp = cliente.getCarrito().getPrimero();

        while(temp != null){
            if(!tienda.hayStock(temp.getNombre(), temp.getCantidad())){
                System.out.println("Error en inventario. No hay suficiente stock.");
                return;
            }
            temp = temp.getSiguiente();
        }

        cliente = desencolar();
        cliente.generarFactura();

        List<String> rutaOptima = tienda.getMapaEntregas().reconstruirCamino(origen, destino, predecesores);
        System.out.println(">>> RUTA DE ENTREGA ÓPTIMA: " + rutaOptima);
        System.out.println(">>> DISTANCIA TOTAL: " + distancias.get(destino) + " km");

        temp = cliente.getCarrito().getPrimero();

        while(temp != null){
            tienda.reducirStock(temp.getNombre(), temp.getCantidad());
            temp = temp.getSiguiente();
        }

        System.out.println("Cliente atendido correctamente.");
    }
}
