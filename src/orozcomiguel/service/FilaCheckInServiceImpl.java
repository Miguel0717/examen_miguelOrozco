package orozcomiguel.service;

import orozcomiguel.model.Pasajero;
import orozcomiguel.repository.FilaCheckInRepository;
import java.util.ArrayDeque;

public class FilaCheckInServiceImpl implements FilaCheckInRepository {

    private ArrayDeque<Pasajero> fila = new ArrayDeque<>();

    @Override
    public void llegar(Pasajero p) {
        fila.addLast(p);
    }

    @Override
    public Pasajero atender() {
        return fila.pollFirst();
    }

    @Override
    public Pasajero retirarUltimo() {
        return fila.pollLast();
    }

    @Override
    public Pasajero verSiguiente() {
        return fila.peekFirst();
    }

    @Override
    public int enFila() {
        return fila.size();
    }
}
