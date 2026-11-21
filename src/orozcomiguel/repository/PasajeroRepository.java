package orozcomiguel.repository;

import orozcomiguel.model.Pasajero;
import java.util.List;

public interface PasajeroRepository {
    void agregar(Pasajero p);
    List<Pasajero> listarPorApellido();
    Pasajero eliminarEnPosicion(int posicion);
    int total();
}
