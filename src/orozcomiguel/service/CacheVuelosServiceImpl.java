package orozcomiguel.service;

import orozcomiguel.model.Vuelo;
import orozcomiguel.repository.CacheVuelosRepository;
import orozcomiguel.repository.VueloRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CacheVuelosServiceImpl implements CacheVuelosRepository {

    private static class MapaConLimite extends LinkedHashMap<String, Vuelo> {

        private int capacidad;

        MapaConLimite(int capacidad) {
            super(16, 0.75f, true);
            this.capacidad = capacidad;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Vuelo> masViejo) {
            return size() > capacidad;
        }
    }

    private VueloRepository vuelos;
    private MapaConLimite cache;

    public CacheVuelosServiceImpl(VueloRepository vuelos, int capacidad) {
        this.vuelos = vuelos;
        this.cache = new MapaConLimite(capacidad);
    }

    @Override
    public Vuelo consultar(String codigo) {
        Vuelo vuelo = cache.get(codigo);
        if (vuelo != null) {
            return vuelo;
        }

        vuelo = vuelos.buscar(codigo);

        if (vuelo != null) {
            cache.put(codigo, vuelo);
        }
        return vuelo;
    }

    @Override
    public List<String> codigosEnCache() {
        return new ArrayList<>(cache.keySet());
    }
}
