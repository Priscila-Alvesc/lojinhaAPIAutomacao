package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComUmValorIgualA(double valor){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Celular");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Rosa");
        cores.add("Branco");
        produto.setProdutoCores(cores);

        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("carregador");
        componente.setComponenteQuantidade(2);
        componentes.add(componente);


        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("tomada");
        segundoComponente.setComponenteQuantidade(1);
        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;


    }
}
