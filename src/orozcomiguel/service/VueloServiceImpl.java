package orozcomiguel.service;

import orozcomiguel.model.Vuelo;
import orozcomiguel.repository.VueloRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VueloServiceImpl implements VueloRepository {

    private HashMap<String, Vuelo> vuelos = new HashMap<>();

    @Override
    public boolean registrar(Vuelo v) {
        if (vuelos.containsKey(v.codigo())) {
            return false;
        }
        vuelos.put(v.codigo(), v);
        return true;
    }

    @Override
    public Vuelo buscar(String codigo) {
        return vuelos.get(codigo);
    }

    @Override
    public Vuelo eliminar(String codigo) {
        return vuelos.remove(codigo);
    }

    @Override
    public List<Vuelo> listar() {
        return new ArrayList<>(vuelos.values());
    }
}
