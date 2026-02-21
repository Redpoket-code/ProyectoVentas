package cr.ucenfotec.ui;

import cr.ucenfotec.Productos.ListaProductos;
import cr.ucenfotec.Productos.Producto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    private static ListaProductos listaProductos = new ListaProductos();

    public static void main(String[] args) throws IOException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws IOException {
        int opcion;
        Producto nuevo;
        String nombre;

        do {
            System.out.println("\n  SISTEMA DE GESTIÓN DE PRODUCTOS\n");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Mostrar todos los productos");
            System.out.println("4. Buscar producto por nombre");
            System.out.println("5. Modificar producto");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Generar reporte de costos");
            System.out.println("8. Salir");
            System.out.print("\nSeleccione una opción: ");

            try {
                opcion = Integer.parseInt(entrada.readLine());

                switch (opcion) {
                    case 1:
                        nuevo = listaProductos.crearProducto();
                        listaProductos.insertarProductoInicio(nuevo);
                        break;
                    case 2:
                        nuevo = listaProductos.crearProducto();
                        listaProductos.insertarProductoFinal(nuevo);
                        break;
                    case 3:
                        listaProductos.mostrarLista();
                        break;
                    case 4:
                        if (listaProductos.estaVacia()) {
                            System.out.println("No hay productos para buscar.");
                            break;
                        }

                        System.out.print("Nombre del producto a buscar: ");
                        nombre = entrada.readLine();
                        Producto encontrado = listaProductos.buscarProducto(nombre);

                        if(encontrado != null){
                            System.out.println(encontrado);
                        }
                        break;
                    case 5:
                        if (listaProductos.estaVacia()) {
                            System.out.println("No hay productos para modificar.");
                            break;
                        }

                        System.out.print("Nombre del producto a modificar: ");
                        nombre = entrada.readLine();
                        listaProductos.modificarProducto(nombre);
                        break;
                    case 6:
                        if (listaProductos.estaVacia()) {
                            System.out.println("No hay productos para eliminar.");
                            break;
                        }

                        System.out.print("Nombre del producto a eliminar: ");
                        nombre = entrada.readLine();
                        Producto eliminado = listaProductos.eliminarProducto(nombre);

                        if(eliminado == null){
                            break;
                        }

                        System.out.println("Producto eliminado:");
                        System.out.println(eliminado);
                        break;
                    case 7:
                        listaProductos.reporteCostos();
                        break;
                    case 8:
                        System.out.println("¡Gracias por usar el sistema!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = 0;
            }

        } while (opcion != 8);
    }
}