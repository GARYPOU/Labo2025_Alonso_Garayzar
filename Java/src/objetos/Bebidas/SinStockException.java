package objetos.Bebidas;

public class SinStockException extends RuntimeException {
    public SinStockException(String message) {
        super(message);
    }
}
