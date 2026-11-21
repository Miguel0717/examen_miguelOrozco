package orozcomiguel.repository;

import orozcomiguel.model.Aterrizaje;

public interface AterrizajeRepository {
    void solicitar(Aterrizaje a);
    Aterrizaje verSiguiente();
    Aterrizaje autorizarSiguiente();
    int pendientes();
}
