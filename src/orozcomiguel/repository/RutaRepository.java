package orozcomiguel.repository;

import java.util.List;

public interface RutaRepository {
    void agregarEscala(String ciudad);
    boolean insertarEscalaDespuesDe(String ciudadExistente, String nuevaCiudad);
    boolean eliminarEscala(String ciudad);
    List<String> listarRuta();
}
