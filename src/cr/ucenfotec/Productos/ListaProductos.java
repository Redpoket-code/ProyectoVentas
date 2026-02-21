package cr.ucenfotec.Productos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListaProductos {

    private BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    //Atributos
    private Producto primero;

    //Constructor
    public ListaProductos(){
        this.primero = null;
    }

    //Getter
    public Producto getPrimero() {
        return primero;
    }

    //Setter
    public void setPrimero(Producto primero) {
        this.primero = primero;
    }

    //Metodos
    public boolean estaVacia(){
        return primero == null;
    }

    public void insertarProductoInicio(Producto producto){
        producto.setSiguiente(primero);
        setPrimero(producto);
        System.out.println("El producto se inserto correctamente al inicio de la lista");
    }

    public void insertarProductoFinal(Producto producto){
        if(estaVacia()){
            setPrimero(producto);
            System.out.println("Producto insertado al final de la lista");
            return;
        }

        Producto temp = primero;

        while(temp.getSiguiente() != null){
            temp =  temp.getSiguiente();
        }
        temp.setSiguiente(producto);

        System.out.println("Producto insertado al final de la lista");
    }

    public Producto buscarProducto(String nombre){
        Producto temp = primero;

        while(temp != null && !temp.getNombre().equalsIgnoreCase(nombre)){
            temp = temp.getSiguiente();
        }

        if(temp == null){
            System.out.println("Producto " + nombre + " no se ha encontrado");
        } else {
            System.out.println("Producto " + nombre + " se ha encontrado");
        }

        return temp;
    }

    public void mostrarLista(){
        if(estaVacia()){
            System.out.println("La lista de productos esta vacia");
            return;
        }
        System.out.println("\nLista de Productos");
        Producto temp = primero;
        int contador = 1;

        while (temp != null) {
            System.out.println("\nProducto #" + contador);
            System.out.println(temp);
            temp = temp.getSiguiente();
            contador++;
        }
        System.out.println("\nTotal de productos: " + (contador - 1));
    }

    public Producto eliminarProducto(String nombre){
        Producto temp = primero;
        Producto anterior = null;

        while(temp != null && !temp.getNombre().equalsIgnoreCase(nombre)){
            anterior = temp;
            temp = temp.getSiguiente();
        }

        if(temp == null){
            System.out.println("No se encontro el producto a eliminar");
            return null;
        }

        if (anterior == null) {
            setPrimero(temp.getSiguiente());
        } else {
            anterior.setSiguiente(temp.getSiguiente());
        }
        return temp;
    }

    public void modificarProducto(String nombre) throws IOException {
        Producto modificar = buscarProducto(nombre);

        if(modificar == null){
            return;
        }

        System.out.println("\nDatos del producto a modificar");
        System.out.println(modificar);

        System.out.println("\n¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Precio");
        System.out.println("3. Categoría");
        System.out.println("4. Fecha de Vencimiento");
        System.out.println("5. Cantidad");
        System.out.println("6. Agregar imagen");
        System.out.println("7. Ver imágenes");
        System.out.println("8. Eliminar imagen");
        System.out.println("9. Cancelar");

        String opcion = entrada.readLine();

        switch (opcion) {
            case "1":
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = entrada.readLine();
                modificar.setNombre(nuevoNombre);
                System.out.println("Nombre modificado correctamente.");
                break;

            case "2":
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(entrada.readLine());
                modificar.setPrecio(nuevoPrecio);
                System.out.println("Precio modificado correctamente.");
                break;

            case "3":
                System.out.print("Nueva categoría: ");
                String nuevaCategoria = entrada.readLine();
                modificar.setCategoria(nuevaCategoria);
                System.out.println("Categoría modificada correctamente.");
                break;

            case "4":
                System.out.print("Nueva fecha de vencimiento (YYYY-MM-DD) o 'No aplica': ");
                String nuevaFecha = entrada.readLine();
                modificar.setFechaVencimiento(nuevaFecha);
                System.out.println("Fecha modificada correctamente.");
                break;

            case "5":
                System.out.print("Nueva cantidad: ");
                int nuevaCantidad = Integer.parseInt(entrada.readLine());
                modificar.setCantidad(nuevaCantidad);
                System.out.println("Cantidad modificada correctamente.");
                break;

            case "6":
                System.out.print("Ruta de la imagen a agregar: ");
                String rutaImagen = entrada.readLine();
                modificar.agregarImagen(rutaImagen);
                break;

            case "7":
                modificar.mostrarImagenes();
                break;

            case "8":
                modificar.mostrarImagenes();
                if (!modificar.getListaImagenes().isEmpty()) {
                    System.out.print("Número de imagen a eliminar: ");
                    int indice = Integer.parseInt(entrada.readLine()) - 1;
                    if (modificar.eliminarImagen(indice)) {
                        System.out.println("Imagen eliminada correctamente.");
                    } else {
                        System.out.println("Índice no válido.");
                    }
                }
                break;

            case "9":
                System.out.println("Modificación cancelada.");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    public void reporteCostos(){
        if(estaVacia()){
            System.out.println("La lista esta vacia");
            return;
        }

        System.out.println("Reporte costos totales");
        Producto temp = primero;
        double costoTotalAcumulado = 0;
        int contador = 1;

        while (temp != null) {
            double costoProducto = temp.calcularCostoTotal();
            costoTotalAcumulado += costoProducto;

            System.out.println("\n--- Producto #" + contador + ": " + temp.getNombre() + " ---");
            System.out.println("  Precio unitario: $" + String.format("%.2f", temp.getPrecio()));
            System.out.println("  Cantidad: " + temp.getCantidad());
            System.out.println("  Costo total: $" + String.format("%.2f", costoProducto));

            temp = temp.getSiguiente();
            contador++;
        }

        System.out.println("Cantidad de productos: " + (contador - 1));
        System.out.println("Costo total acumlado: $" + String.format("%.2f", costoTotalAcumulado));
    }

    public Producto crearProducto() throws IOException {
        System.out.println("\nCrear nuevo producto");

        System.out.print("Nombre: ");
        String nombre = entrada.readLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(entrada.readLine());

        System.out.print("Categoría: ");
        String categoria = entrada.readLine();

        System.out.print("Cantidad en inventario: ");
        int cantidad = Integer.parseInt(entrada.readLine());

        System.out.print("¿Tiene fecha de vencimiento? (s/n): ");
        String respuesta = entrada.readLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
            String fecha = entrada.readLine();
            return new Producto(nombre, precio, categoria, fecha, cantidad);
        } else {
            return new Producto(nombre, precio, categoria, cantidad);
        }
    }
}
