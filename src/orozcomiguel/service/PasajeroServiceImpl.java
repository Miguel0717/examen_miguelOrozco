package orozcomiguel.service;

import orozcomiguel.model.Pasajero;
import orozcomiguel.repository.PasajeroRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PasajeroServiceImpl implements PasajeroRepository {

    private ArrayList<Pasajero> pasajeros = new ArrayList<>();

    @Override
    public void agregar(Pasajero p) {
        pasajeros.add(p);
    }

    @Override
    public List<Pasajero> listarPorApellido() {
        List<Pasajero> copia = new ArrayList<>(pasajeros);

        Comparator<Pasajero> porApellido = new Comparator<Pasajero>() {
            @Override
            public int compare(Pasajero a, Pasajero b) {
                return a.apellido().compareTo(b.apellido());
            }
        };

        copia.sort(porApellido);
        return copia;
    }

    @Override
    public Pasajero eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= pasajeros.size()) {
            return null;
        }
        return pasajeros.remove(posicion);
    }

    @Override
    public int total() {
        return pasajeros.size();
    }
}
