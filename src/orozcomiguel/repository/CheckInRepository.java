package orozcomiguel.repository;

public interface CheckInRepository {
    boolean registrar(String pasaporte);
    boolean estaRegistrado(String pasaporte);
    int total();
}
