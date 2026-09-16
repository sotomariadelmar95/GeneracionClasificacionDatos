# Generación y Clasificación de Datos — Entrega 1 (Semana 3)

Proyecto Eclipse (Java 8) que implementa la clase `GenerateInfoFiles`, requerida
para la Entrega 1 del módulo Conceptos Fundamentales de Programación.

## Cómo importarlo en Eclipse

1. Descomprime el archivo `.zip`.
2. En Eclipse: `File > Import... > General > Existing Projects into Workspace`.
3. Selecciona la carpeta descomprimida `GeneracionClasificacionDatos` y da clic en `Finish`.
4. Verifica que el proyecto use un JRE 8 (clic derecho en el proyecto > `Properties` > `Java Build Path` > `Libraries`).

## Cómo ejecutarlo

1. Abre `src/datos/GenerateInfoFiles.java`.
2. Clic derecho > `Run As` > `Java Application`.
3. Al terminar, se crean en la raíz del proyecto:
   - `productos.txt`
   - `vendedores.txt`
   - un `ventas_<numeroDocumento>.txt` por cada vendedor generado.

## Estructura

```
src/
  modelo/
    Producto.java     -> modelo inmutable de un producto
    Vendedor.java      -> modelo inmutable de un vendedor
  datos/
    GenerateInfoFiles.java  -> clase con método main; genera los 3 tipos de archivo
```

## Métodos clave (piden un mensaje de éxito/error, ninguno pide input al usuario)

- `createProductsFile(int productsCount)`
- `createSalesManInfoFile(int salesmanCount)`
- `createSalesMenFile(int randomSalesCount, String name, long id)`

## Pendiente para próximas entregas

- Clase `main` (Entrega 2/3) que lea estos archivos generados y produzca los
  reportes de ventas por vendedor y de productos vendidos, ambos ordenados
  descendentemente.
- Validación de archivos con formato erróneo (punto extra c).
- Soporte para más de un archivo de ventas por vendedor (punto extra a).
- Soporte para archivos serializados (punto extra b).
