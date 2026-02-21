package cr.ucenfotec.Productos;

import java.util.ArrayList;

public class Producto {

    //Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private int cantidad;
    private String fechaVencimiento;
    private ArrayList<String> listaImagenes;
    private Producto siguiente;

    //Constructor
    public Producto(String nombre, double precio, String categoria,String fechaVencimiento, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    public Producto(String nombre, double precio, String categoria, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public Producto getSiguiente() {
        return siguiente;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setSiguiente(Producto siguiente) {
        this.siguiente = siguiente;
    }

    //Metodos
    public void agregarImagen(String ruta){
        listaImagenes.add(ruta);
        System.out.println("Imagen agregada al producto: " + nombre);
    }

    public void mostrarImagenes(){
        if(listaImagenes.isEmpty()){
            System.out.println("La lista de imagenes esta vacia");
        } else {
            System.out.println("Rutas de las imagenes del producto");
            for (int i = 0; i < listaImagenes.size(); i++) {
                System.out.println((i + 1) + ". " + listaImagenes.get(i));
            }
        }
    }

    public boolean eliminarImagen(int indice){
        if(indice >= 0 && indice < listaImagenes.size()){
            listaImagenes.remove(indice);
            return true;
        }
        return false;
    }

    public double calcularCostoTotal() {
        return precio * cantidad;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Precio: $").append(String.format("%.2f", precio)).append("\n");
        sb.append("Categoría: ").append(categoria).append("\n");
        sb.append("Fecha Vencimiento: ").append(fechaVencimiento).append("\n");
        sb.append("Cantidad: ").append(cantidad).append("\n");
        sb.append("Imágenes: ").append(listaImagenes.size()).append(" archivo(s)");
        return sb.toString();
    }
}
