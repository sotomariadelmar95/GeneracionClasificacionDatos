package datos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.Producto;
import modelo.Vendedor;

/**
 * Clase con método {@code main} encargada de generar, de forma
 * pseudoaleatoria, los archivos planos que servirán como entrada para el
 * programa principal de generación y clasificación de datos (Entrega 2/3).
 * <p>
 * Esta clase produce, en la carpeta del proyecto:
 * </p>
 * <ul>
 * <li>Un archivo {@code productos.txt} con el catálogo de productos.</li>
 * <li>Un archivo {@code vendedores.txt} con la información de los
 * vendedores.</li>
 * <li>Un archivo {@code ventas_<idVendedor>.txt} por cada vendedor, con sus
 * ventas.</li>
 * </ul>
 * <p>
 * Ninguno de los métodos de esta clase solicita información al usuario: toda
 * la información se genera de manera pseudoaleatoria pero coherente (por
 * ejemplo, los nombres de vendedores se toman de listas de nombres reales).
 * </p>
 *
 * @author Mar Soto
 */
public class GenerateInfoFiles {

    /** Generador de números pseudoaleatorios reutilizado por toda la clase. */
    private static final Random GENERADOR_ALEATORIO = new Random();

    /** Lista de nombres reales usada para generar vendedores coherentes. */
    private static final String[] NOMBRES = {
            "Carlos", "Maria", "Juan", "Laura", "Andres", "Camila", "Diego",
            "Valentina", "Santiago", "Isabella", "Felipe", "Daniela", "Sebastian",
            "Mariana", "Julian", "Natalia", "Alejandro", "Gabriela", "David", "Paula"
    };

    /** Lista de apellidos reales usada para generar vendedores coherentes. */
    private static final String[] APELLIDOS = {
            "Gomez", "Rodriguez", "Perez", "Sanchez", "Martinez", "Lopez",
            "Garcia", "Hernandez", "Diaz", "Torres", "Ramirez", "Castro",
            "Vargas", "Rojas", "Moreno", "Suarez", "Ortiz", "Jimenez", "Munoz", "Gutierrez"
    };

    /** Tipos de documento válidos para los vendedores. */
    private static final String[] TIPOS_DOCUMENTO = { "CC", "CE", "TI", "PA" };

    /** Catálogo de nombres de productos usado para generar productos coherentes. */
    private static final String[] NOMBRES_PRODUCTOS = {
            "Laptop", "Mouse", "Teclado", "Monitor", "Audifonos", "Impresora",
            "Tablet", "Smartphone", "Camara", "Parlante", "Disco Duro Externo",
            "Memoria USB", "Silla Ergonomica", "Router", "Webcam"
    };

    /**
     * Catálogo de productos generado en la ejecución actual. Se conserva en
     * memoria para que {@link #createSalesMenFile(int, String, long)} pueda
     * referenciar identificadores de producto válidos al generar las ventas.
     */
    private static List<Producto> productosDisponibles = new ArrayList<>();

    /**
     * Vendedores generados en la ejecución actual. Se conserva en memoria
     * para que {@code main} pueda generar un archivo de ventas por cada uno.
     */
    private static List<Vendedor> vendedoresDisponibles = new ArrayList<>();

    /**
     * Punto de entrada del programa. Genera el catálogo de productos, la
     * información de los vendedores y un archivo de ventas pseudoaleatorio
     * por cada vendedor. Al finalizar, imprime un mensaje de éxito o de
     * error en la consola.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        try {
            int cantidadProductos = 10 + GENERADOR_ALEATORIO.nextInt(11); // entre 10 y 20 productos
            createProductsFile(cantidadProductos);

            int cantidadVendedores = 5 + GENERADOR_ALEATORIO.nextInt(11); // entre 5 y 15 vendedores
            createSalesManInfoFile(cantidadVendedores);

            for (Vendedor vendedor : vendedoresDisponibles) {
                int cantidadVentas = 3 + GENERADOR_ALEATORIO.nextInt(8); // entre 3 y 10 ventas
                createSalesMenFile(cantidadVentas, vendedor.getTipoDocumento(), vendedor.getNumeroDocumento());
            }

            System.out.println("Generación de archivos completada exitosamente.");
            System.out.println("Productos generados: " + cantidadProductos);
            System.out.println("Vendedores generados: " + cantidadVendedores);
        } catch (IOException excepcion) {
            System.out.println("Error al generar los archivos: " + excepcion.getMessage());
        }
    }

    /**
     * Crea el archivo {@code productos.txt} con información pseudoaleatoria
     * de productos, con el formato
     * {@code IDProducto;NombreProducto;PrecioPorUnidadProducto} (un producto
     * por línea).
     *
     * @param productsCount cantidad de productos a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {
        List<Producto> nuevosProductos = new ArrayList<>();

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("productos.txt"))) {
            for (int idProducto = 1; idProducto <= productsCount; idProducto++) {
                String nombreProducto = NOMBRES_PRODUCTOS[GENERADOR_ALEATORIO.nextInt(NOMBRES_PRODUCTOS.length)];
                double precioPorUnidad = 1000 + GENERADOR_ALEATORIO.nextInt(499001); // entre 1.000 y 500.000

                Producto producto = new Producto(idProducto, nombreProducto, precioPorUnidad);
                nuevosProductos.add(producto);

                escritor.write(producto.toLineaArchivo());
                escritor.newLine();
            }
        }

        productosDisponibles = nuevosProductos;
    }

    /**
     * Crea el archivo {@code vendedores.txt} con información pseudoaleatoria
     * pero coherente de vendedores (nombres y apellidos tomados de listas de
     * nombres reales), con el formato
     * {@code TipoDocumento;NúmeroDocumento;NombresVendedor;ApellidosVendedor}
     * (un vendedor por línea).
     *
     * @param salesmanCount cantidad de vendedores a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        List<Vendedor> nuevosVendedores = new ArrayList<>();

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("vendedores.txt"))) {
            for (int i = 0; i < salesmanCount; i++) {
                String tipoDocumento = TIPOS_DOCUMENTO[GENERADOR_ALEATORIO.nextInt(TIPOS_DOCUMENTO.length)];
                long numeroDocumento = generarNumeroDocumentoUnico(nuevosVendedores);
                String nombres = NOMBRES[GENERADOR_ALEATORIO.nextInt(NOMBRES.length)];
                String apellidos = APELLIDOS[GENERADOR_ALEATORIO.nextInt(APELLIDOS.length)];

                Vendedor vendedor = new Vendedor(tipoDocumento, numeroDocumento, nombres, apellidos);
                nuevosVendedores.add(vendedor);

                escritor.write(vendedor.toLineaArchivo());
                escritor.newLine();
            }
        }

        vendedoresDisponibles = nuevosVendedores;
    }

    /**
     * Crea un archivo pseudoaleatorio de ventas para un único vendedor,
     * identificado por su tipo y número de documento. El archivo generado
     * tiene el formato:
     * <pre>
     * TipoDocumentoVendedor;NúmeroDocumentoVendedor
     * IDProducto1;CantidadProducto1Vendido;
     * IDProducto2;CantidadProducto2Vendido;
     * </pre>
     * <p>
     * Cada línea de venta referencia un identificador de producto tomado del
     * catálogo generado previamente por {@link #createProductsFile(int)},
     * cuando este está disponible; en caso contrario, se genera un
     * identificador dentro de un rango razonable.
     * </p>
     *
     * @param randomSalesCount cantidad de líneas de venta a generar
     * @param name             tipo de documento del vendedor (por ejemplo, "CC")
     * @param id               número de documento del vendedor
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String nombreArchivo = "ventas_" + id + ".txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            escritor.write(name + ";" + id);
            escritor.newLine();

            for (int i = 0; i < randomSalesCount; i++) {
                int idProducto = obtenerIdProductoValido();
                int cantidadVendida = 1 + GENERADOR_ALEATORIO.nextInt(20); // entre 1 y 20 unidades

                escritor.write(idProducto + ";" + cantidadVendida + ";");
                escritor.newLine();
            }
        }
    }

    /**
     * Genera un número de documento pseudoaleatorio de 10 dígitos que no se
     * repita dentro de los vendedores ya generados en la ejecución actual.
     *
     * @param vendedoresYaGenerados vendedores generados hasta el momento
     * @return un número de documento único dentro del lote actual
     */
    private static long generarNumeroDocumentoUnico(List<Vendedor> vendedoresYaGenerados) {
        long numeroDocumento;
        boolean repetido;

        do {
            numeroDocumento = 1_000_000_000L + (long) (GENERADOR_ALEATORIO.nextDouble() * 999_999_999L);
            repetido = false;

            for (Vendedor vendedor : vendedoresYaGenerados) {
                if (vendedor.getNumeroDocumento() == numeroDocumento) {
                    repetido = true;
                    break;
                }
            }
        } while (repetido);

        return numeroDocumento;
    }

    /**
     * Obtiene un identificador de producto válido para usar en una línea de
     * venta: uno tomado del catálogo generado por
     * {@link #createProductsFile(int)} si ya existe, o uno dentro de un
     * rango razonable en caso contrario.
     *
     * @return un identificador de producto a usar en el archivo de ventas
     */
    private static int obtenerIdProductoValido() {
        if (!productosDisponibles.isEmpty()) {
            Producto producto = productosDisponibles.get(GENERADOR_ALEATORIO.nextInt(productosDisponibles.size()));
            return producto.getId();
        }

        return 1 + GENERADOR_ALEATORIO.nextInt(20);
    }
}
