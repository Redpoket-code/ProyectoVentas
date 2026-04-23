package cr.ucenfotec.bl.Tienda;

import cr.ucenfotec.bl.ArbolProductos.ArbolProductos;
import cr.ucenfotec.bl.Cliente.ColaClientes;
import cr.ucenfotec.bl.Productos.Producto;
import cr.ucenfotec.bl.Grafo.Grafo;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.Map;

public class Tienda {

    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    private Grafo mapaEntregas;
    private final String UBICACION_TIENDA = "Tienda Central";

    public Tienda() {
        inventario = new ArbolProductos();
        colaClientes = new ColaClientes();
        mapaEntregas = new Grafo();
        mapaEntregas.agregarVertice("Tienda Central");
        inicializarMapa();
    }

    // Getter
    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    // 1. Agregar producto
    public void agregarProducto(Producto producto) {
        inventario.insertar(producto);
    }

    // 2. Buscar producto
    public Producto buscarProducto(String nombre) {
        return inventario.buscar(nombre);
    }

    // 3. Mostrar inventario
    public void mostrarInventario() {
        inventario.mostrarInOrden();
    }

    // 4. Verificar Stock

    public boolean hayStock(String nombre, int cantidadSolicitada) {
        Producto producto = inventario.buscar(nombre);

        if (producto == null) {
            System.out.println("Producto no existe en inventario");
            return false;
        }

        if (producto.getCantidad() >= cantidadSolicitada) {
            return true;
        } else {
            System.out.println("Stock insuficiente");
            return false;
        }
    }

    // 5. Reducir inventario

    public boolean reducirStock(String nombre, int cantidad) {
        Producto producto = inventario.buscar(nombre);

        if (producto == null) {
            System.out.println("Producto no encontrado");
            return false;
        }

        if (producto.getCantidad() < cantidad) {
            System.out.println("No hay suficiente stock");
            return false;
        }

        producto.setCantidad(producto.getCantidad() - cantidad);
        System.out.println("Stock actualizado correctamente");

        return true;
    }

    // 6. Aumentar Stock

    public void aumentarStock(String nombre, int cantidad) {
        boolean resultado = inventario.aumentarStock(nombre, cantidad);

        if (resultado) {
            System.out.println("Stock aumentado correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    // 7. Iniciar Mapa
    public void inicializarMapa(){
        mapaEntregas.agregarArista("Tienda Central", "Sector Norte", 10);
        mapaEntregas.agregarArista("Tienda Central", "Sector Sur", 15);
        mapaEntregas.agregarArista("Sector Norte", "Sector Este", 5);
        mapaEntregas.agregarArista("Sector Sur", "Sector Este", 20);
    }

    public Grafo getMapaEntregas() {return mapaEntregas; }
    public String getUbicacionTienda(){return "Tienda Central"; }


}