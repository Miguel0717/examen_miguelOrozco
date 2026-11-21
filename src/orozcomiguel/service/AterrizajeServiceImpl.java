package orozcomiguel.service;

import orozcomiguel.model.Aterrizaje;
import orozcomiguel.repository.AterrizajeRepository;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AterrizajeServiceImpl implements AterrizajeRepository {

    private PriorityQueue<Aterrizaje> cola;

    public AterrizajeServiceImpl() {
        Comparator<Aterrizaje> regla = new Comparator<Aterrizaje>() {
            @Override
            public int compare(Aterrizaje a, Aterrizaje b) {
                if (a.prioridad() != b.prioridad()) {
                    return Integer.compare(a.prioridad(), b.prioridad());
                }

                return Long.compare(a.llegada(), b.llegada());
            }
        };
        cola = new PriorityQueue<>(regla);
    }

    @Override
    public void solicitar(Aterrizaje a) {
        cola.offer(a);
    }

    @Override
    public Aterrizaje verSiguiente() {
        return cola.peek();
    }

    @Override
    public Aterrizaje autorizarSiguiente() {
        return cola.poll();
    }

    @Override
    public int pendientes() {
        return cola.size();
    }
}
