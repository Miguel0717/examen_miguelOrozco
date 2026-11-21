package orozcomiguel.service;

import orozcomiguel.repository.RutaRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RutaServiceImpl implements RutaRepository {

    private LinkedList<String> ruta = new LinkedList<>();

    @Override
    public void agregarEscala(String ciudad) {
        ruta.add(ciudad);
    }

    @Override
    public boolean insertarEscalaDespuesDe(String ciudadExistente, String nuevaCiudad) {
        ListIterator<String> it = ruta.listIterator();
        while (it.hasNext()) {
            String ciudad = it.next();
            if (ciudad.equals(ciudadExistente)) {
                it.add(nuevaCiudad);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarEscala(String ciudad) {
        Iterator<String> it = ruta.iterator();
        while (it.hasNext()) {
            String actual = it.next();
            if (actual.equals(ciudad)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> listarRuta() {
        return new ArrayList<>(ruta);
    }
}
