package model.dao;

import com.google.gson.Gson;
import java.io.IOException;
import model.vo.EnderecoServer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EnderecoDaoServer {

    private static String BASE_URL = "https://viacep.com.br/ws/";
    private static String sufix = "/json/";

    public static EnderecoServer getEndereco(String cep) {
        //Cria a url a ser executada
        String url = BASE_URL + cep + sufix;
        System.out.println(url);
        //Iniciar HttpClient para requisição client com o servidor
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Executa a url com método GET
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;
        EnderecoServer enderecoServer;
        Gson gson = new Gson();
        try {
            //Armazena a resposta do servidor
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);

            //Transforma a resposta em um objeto do tipo Endereco
            enderecoServer = gson.fromJson(content, EnderecoServer.class);
        } catch (IOException ex) {
            enderecoServer = null;
        }

        return enderecoServer;
    }

}
