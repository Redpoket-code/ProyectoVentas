package cr.ucenfotec.ui;

import cr.ucenfotec.bl.Cliente.Cliente;
import cr.ucenfotec.bl.Productos.Producto;
import cr.ucenfotec.bl.Tienda.Tienda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    static Tienda tienda = new Tienda();

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE TIENDA ===");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Crear cliente y agregar a cola");
            System.out.println("4. Atender siguiente cliente");
            System.out.println("5. Aumentar stock de producto");
            System.out.println("6. Salir");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    agregarProducto();
                    break;

                case 2:
                    tienda.mostrarInventario();
                    break;

                case 3:
                    crearCliente();
                    break;

                case 4:
                    tienda.getColaClientes().atenderCliente(tienda);
                    break;

                case 5:
                    aumentarStock();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 6);
    }

    public static void agregarProducto() throws IOException {

        System.out.println("\n=== Nuevo Producto ===");

        System.out.print("Nombre: ");
        String nombre = entrada.readLine();
        while (nombre.trim().isEmpty()) {
            System.out.print("El nombre no puede estar vacío. Intente de nuevo: ");
            nombre = entrada.readLine();
        }

        double precio;
        do {
            precio = leerDouble("Precio: ");
            if (precio <= 0) {
                System.out.println("El precio debe ser mayor a 0.");
            }
        } while (precio <= 0);

        System.out.print("Categoria: ");
        String categoria = entrada.readLine();
        while (categoria.trim().isEmpty()) {
            System.out.print("La categoria no puede estar vacía. Intente de nuevo: ");
            categoria = entrada.readLine();
        }

        int cantidad;
        do {
            cantidad = leerEntero("Cantidad: ");
            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
            }
        } while (cantidad < 0);

        System.out.print("¿Tiene fecha de vencimiento? (s/n): ");
        String respuesta = entrada.readLine();

        Producto producto;

        if(respuesta.equalsIgnoreCase("s")){
            System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
            String fecha = entrada.readLine();

            producto = new Producto(nombre, precio, categoria, fecha, cantidad);
        } else {
            producto = new Producto(nombre, precio, categoria, cantidad);
        }

        System.out.print("¿Desea agregar imágenes? (s/n): ");
        String respImagenes = entrada.readLine();

        if(respImagenes.equalsIgnoreCase("s")){

            while(true){
                System.out.print("Ingrese ruta de la imagen (o 'fin' para terminar): ");
                String ruta = entrada.readLine();

                if(ruta.equalsIgnoreCase("fin")){
                    break;
                }

                if(ruta.trim().isEmpty()){
                    System.out.println("Ruta inválida.");
                    continue;
                }

                producto.agregarImagen(ruta);
            }
        }

        tienda.agregarProducto(producto);
    }

    public static void crearCliente() throws IOException {

        System.out.println("\n=== Nuevo Cliente ===");

        System.out.print("Nombre: ");
        String nombre = entrada.readLine();

        int prioridad = leerEntero("Prioridad (1-3): ");

        Cliente cliente = new Cliente(nombre, prioridad);

        String opcion;

        do {
            System.out.println("\n--- Inventario ---");
            tienda.mostrarInventario();

            System.out.print("Nombre del producto a agregar (o 'fin'): ");
            String nombreProducto = entrada.readLine();

            if(nombreProducto.equalsIgnoreCase("fin")){
                break;
            }

            Producto producto = tienda.buscarProducto(nombreProducto);

            if(producto == null){
                System.out.println("Producto no encontrado");
                continue;
            }

            int cantidad = leerEntero("Cantidad deseada: ");

            if(tienda.hayStock(nombreProducto, cantidad)){
                cliente.agregarProductoAlCarrito(producto, cantidad);
                System.out.println("Producto agregado al carrito");
            }

        } while(true);

        tienda.getColaClientes().encolar(cliente);

        System.out.println("Cliente agregado a la cola correctamente.");
    }

    public static void aumentarStock() throws IOException {

        System.out.println("\n=== Aumentar Stock ===");

        System.out.print("Nombre del producto: ");
        String nombre = entrada.readLine();

        int cantidad;
        do {
            cantidad = leerEntero("Cantidad a agregar: ");
            if (cantidad <= 0) {
                System.out.println("Debe ser mayor a 0");
            }
        } while (cantidad <= 0);

        tienda.aumentarStock(nombre, cantidad);
    }

    public static int leerEntero(String mensaje) throws IOException {
        int valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(entrada.readLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
            }
        }
    }

    public static double leerDouble(String mensaje) throws IOException {
        double valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Double.parseDouble(entrada.readLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
            }
        }
    }
}