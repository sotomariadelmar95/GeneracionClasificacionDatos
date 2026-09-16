package modelo;

/**
 * Representa un producto disponible para la venta.
 * <p>
 * Contiene el identificador único del producto, su nombre comercial y el
 * precio de venta por unidad. Esta clase es inmutable: una vez creado un
 * {@code Producto}, sus datos no pueden modificarse.
 * </p>
 *
 * @author Mar Soto
 */
public class Producto {

    /** Identificador único del producto. */
    private final int id;

    /** Nombre comercial del producto. */
    private final String nombre;

    /** Precio de venta por unidad del producto. */
    private final double precioPorUnidad;

    /**
     * Crea un nuevo producto con la información indicada.
     *
     * @param id              identificador único del producto
     * @param nombre          nombre comercial del producto
     * @param precioPorUnidad precio de venta por unidad
     */
    public Producto(int id, String nombre, double precioPorUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioPorUnidad = precioPorUnidad;
    }

    /**
     * Retorna el identificador único del producto.
     *
     * @return el id del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el nombre comercial del producto.
     *
     * @return el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el precio de venta por unidad del producto.
     *
     * @return el precio por unidad
     */
    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    /**
     * Convierte el producto a su representación en línea de archivo plano,
     * con el formato {@code IDProducto;NombreProducto;PrecioPorUnidadProducto}.
     *
     * @return la línea de texto lista para escribir en el archivo de productos
     */
    public String toLineaArchivo() {
        return id + ";" + nombre + ";" + precioPorUnidad;
    }
}
