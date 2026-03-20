package cr.ucenfotec.bl.ArbolProductos;
import cr.ucenfotec.bl.Productos.Producto;

public class ArbolProductos {

    private Producto raiz;

    public ArbolProductos() {
        raiz = null;
    }

    //Está vacío?
    public boolean estaVacio() {
        return raiz == null;
    }

    // INSERTAR
    public void insertar(Producto nuevo) {
        raiz = insertarRecursivo(raiz, nuevo);
    }

    private Producto insertarRecursivo(Producto actual, Producto nuevo) {

        if (actual == null) {
            System.out.println("Producto insertado correctamente.");
            return nuevo;
        }

        int comparacion = nuevo.getNombre().compareToIgnoreCase(actual.getNombre());

        if (comparacion < 0) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), nuevo));

        } else if (comparacion > 0) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), nuevo));

        } else {
            System.out.println("El producto ya existe en el inventario.");
        }

        return actual;
    }

    // BUSCAR productos
    public Producto buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Producto buscarRecursivo(Producto actual, String nombre) {
        if (actual == null) return null;

        if (nombre.equalsIgnoreCase(actual.getNombre())) {
            return actual;
        }

        if (nombre.compareToIgnoreCase(actual.getNombre()) < 0) {
            return buscarRecursivo(actual.getIzquierdo(), nombre);
        } else {
            return buscarRecursivo(actual.getDerecho(), nombre);
        }
    }

    // RECORRIDO INORDEN (ordenado)
    public void mostrarInOrden() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío");
            return;
        }
        inOrden(raiz);
    }

    private void inOrden(Producto actual) {
        if (actual != null) {
            inOrden(actual.getIzquierdo());
            System.out.println(actual);
            inOrden(actual.getDerecho());
        }
    }

    public boolean aumentarStock(String nombre, int cantidad) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.setCantidad(producto.getCantidad() + cantidad);
        return true;
    }
}