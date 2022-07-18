import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class FilmesMaisPopulares {
    public static void main(String[] args) throws Exception {
        //Conexão HTTP buscando os filmes mais populares
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_l6qb542b";
        URI endereco = URI.create(url);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitacao = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> resposta = cliente.send(solicitacao, BodyHandlers.ofString());
        resposta.body();
        String conteudo = resposta.body();
        
    }
}
