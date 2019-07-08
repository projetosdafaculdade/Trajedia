package util;

import view.Select;
import java.util.ArrayList;
import java.util.List;

public class SelectOptions {

    private List<String> itens = new ArrayList<>();
    private String retorno;
    private int indice;
    private String titulo;

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    
    public void adicionar(String item) {
        itens.add(item);
    }

    public void remover(int indice) {
        itens.remove(indice);
    }

    public String retorno() {
        return retorno;
    }

    public String getRetorno() {
        return retorno;
    }
    public void setRetorno(String retorno) {
         this.retorno = retorno;
    }

    public void instanciar(SelectOptions selectOptions) {
        Select gds = new Select(null, true, selectOptions);
        gds.setVisible(true);
    }

    public List<String> listar() {
        return itens;
    }

    public String listarEspecifico(int indice) {
        return itens.get(indice);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
