package modelo;

/**
 * Representa a un vendedor identificado por su tipo y número de documento.
 * <p>
 * Esta clase es inmutable: una vez creado un {@code Vendedor}, sus datos no
 * pueden modificarse.
 * </p>
 *
 * @author Mar Soto
 */
public class Vendedor {

    /** Tipo de documento del vendedor (por ejemplo, CC, CE, TI, PA). */
    private final String tipoDocumento;

    /** Número de documento del vendedor. */
    private final long numeroDocumento;

    /** Nombres del vendedor. */
    private final String nombres;

    /** Apellidos del vendedor. */
    private final String apellidos;

    /**
     * Crea un nuevo vendedor con la información indicada.
     *
     * @param tipoDocumento   tipo de documento del vendedor
     * @param numeroDocumento número de documento del vendedor
     * @param nombres         nombres del vendedor
     * @param apellidos       apellidos del vendedor
     */
    public Vendedor(String tipoDocumento, long numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    /**
     * Retorna el tipo de documento del vendedor.
     *
     * @return el tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Retorna el número de documento del vendedor.
     *
     * @return el número de documento
     */
    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Retorna los nombres del vendedor.
     *
     * @return los nombres del vendedor
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Retorna los apellidos del vendedor.
     *
     * @return los apellidos del vendedor
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Convierte el vendedor a su representación en línea de archivo plano,
     * con el formato
     * {@code TipoDocumento;NúmeroDocumento;NombresVendedor;ApellidosVendedor}.
     *
     * @return la línea de texto lista para escribir en el archivo de vendedores
     */
    public String toLineaArchivo() {
        return tipoDocumento + ";" + numeroDocumento + ";" + nombres + ";" + apellidos;
    }
}
