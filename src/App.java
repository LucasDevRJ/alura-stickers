import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Key;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitacao = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> resposta = cliente.send(solicitacao, BodyHandlers.ofString());
        resposta.body();
        String conteudo = resposta.body();
        //extrair só os dados que interessam (título, poster, classificação)
        JsonParser JsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = JsonParser.parse(conteudo);
        System.out.println("Total de filmes: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.get(0));
        //exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
        }
    }
}
