package cr.ucenfotec.bl.Cliente;

import cr.ucenfotec.bl.Productos.ListaProductos;
import cr.ucenfotec.bl.Productos.Producto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente {

    private String nombre;
    private int prioridad;
    private ListaProductos carrito;

    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public Cliente(String nombre, int prioridad) throws IOException {

        while(prioridad < 1 || prioridad > 3){
            System.out.println("Prioridad inválida. Debe ser 1, 2 o 3.");
            System.out.print("Ingrese nuevamente la prioridad: ");

            try {
                prioridad = Integer.parseInt(entrada.readLine());
            } catch (NumberFormatException e){
                prioridad = 0;
            }
        }

        this.nombre = nombre;
        this.prioridad = prioridad;
        carrito = new ListaProductos();
    }

    public void agregarProductoAlCarrito(Producto producto, int cantidadDeseada){
        if(cantidadDeseada <= 0){
            System.out.println("Cantidad inválida");
            return;
        }
        Producto copia = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria(),
                cantidadDeseada
        );
        carrito.insertarProductoFinal(copia);
    }

    public void mostrarCarrito(){
        carrito.mostrarLista();
    }

    public void generarFactura(){
        System.out.println("\n ===Factura===");
        System.out.println("Cliente " + nombre);
        System.out.println("Prioridad: " + prioridad);
        carrito.reporteCostos();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public void setCarrito(ListaProductos carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", prioridad=" + prioridad +
                ", carrito=" + carrito +
                '}';
    }
}
