package cr.ucenfotec.bl.Tienda;

import cr.ucenfotec.bl.ArbolProductos.ArbolProductos;
import cr.ucenfotec.bl.Cliente.ColaClientes;
import cr.ucenfotec.bl.Productos.Producto;

public class Tienda {

    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    public Tienda() {
        inventario = new ArbolProductos();
        colaClientes = new ColaClientes();
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

    public void aumentarStock(String nombre, int cantidad) {
        boolean resultado = inventario.aumentarStock(nombre, cantidad);

        if (resultado) {
            System.out.println("Stock aumentado correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
}