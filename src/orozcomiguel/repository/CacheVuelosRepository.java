package orozcomiguel.repository;

import orozcomiguel.model.Vuelo;
import java.util.List;

public interface CacheVuelosRepository {
    Vuelo consultar(String codigo);
    List<String> codigosEnCache();
}
