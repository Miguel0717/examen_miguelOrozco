package orozcomiguel.repository;

import orozcomiguel.model.Pasajero;

public interface FilaCheckInRepository {
    void llegar(Pasajero p);
    Pasajero atender();
    Pasajero retirarUltimo();
    Pasajero verSiguiente();
    int enFila();
}
