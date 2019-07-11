package util;

public class FuncionarioLogado {
    private static FuncionarioLogado instance;

    private FuncionarioLogado() {}

    public static FuncionarioLogado getInstance() {
        if (instance == null)
            instance = new FuncionarioLogado();
        return instance;
    }

    public static void setInstance(FuncionarioLogado instance) {
        FuncionarioLogado.instance = instance;
    }

}