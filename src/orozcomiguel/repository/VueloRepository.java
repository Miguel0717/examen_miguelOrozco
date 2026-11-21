package orozcomiguel.repository;

import orozcomiguel.model.Vuelo;
import java.util.List;

public interface VueloRepository {
    boolean registrar(Vuelo v);
    Vuelo buscar(String codigo);
    Vuelo eliminar(String codigo);
    List<Vuelo> listar();
}
