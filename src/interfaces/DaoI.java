package interfaces;

import java.util.List;

public interface DaoI<T> {

    public List<T> listar();

    public int cadastrar(T obj);

    public boolean alterar(T obj);

    public boolean deletar(int id);

    public T lerPorId(int id);

    public List<T> pesquisar(String termo);
}
