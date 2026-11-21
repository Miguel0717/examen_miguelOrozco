package orozcomiguel.service;

import orozcomiguel.repository.CheckInRepository;
import java.util.HashSet;

public class CheckInServiceImpl implements CheckInRepository {

    private HashSet<String> pasaportes = new HashSet<>();

    @Override
    public boolean registrar(String pasaporte) {
        return pasaportes.add(pasaporte);
    }

    @Override
    public boolean estaRegistrado(String pasaporte) {
        return pasaportes.contains(pasaporte);
    }

    @Override
    public int total() {
        return pasaportes.size();
    }
}
