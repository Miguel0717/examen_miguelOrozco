package orozcomiguel.repository;

import java.util.List;

public interface DestinoRepository {
    boolean registrarDestino(String destino);
    List<String> listarEnOrden();
    int total();
}
