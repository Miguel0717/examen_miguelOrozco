package orozcomiguel.service;

import orozcomiguel.repository.DestinoRepository;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DestinoServiceImpl implements DestinoRepository {

    private LinkedHashSet<String> destinos = new LinkedHashSet<>();

    @Override
    public boolean registrarDestino(String destino) {
        return destinos.add(destino);
    }

    @Override
    public List<String> listarEnOrden() {
        return new ArrayList<>(destinos);
    }

    @Override
    public int total() {
        return destinos.size();
    }
}
