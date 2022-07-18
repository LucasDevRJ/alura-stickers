import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

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

        //Extração de dados da URL
        JsonParser JsonParser = new JsonParser();
        List<Map<String, String>> filmesMaisPopulares = JsonParser.parse(conteudo);

        //Exibição do conteúdo no console
        System.out.println("----------|Filmes mais populares|----------");
        System.out.println();
        for (Map<String,String> filme : filmesMaisPopulares) {
            System.out.println("\u001b[33m\u001b[47mFilme: " + filme.get("title") + "\u001b[m");
            System.out.println("Ano: " + filme.get("year"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Elenco: " + filme.get("crew"));
            System.out.println("Nota: " + filme.get("imDbRating"));
            System.out.println("Avaliações: " + filme.get("imDbRatingCount"));
            System.out.println();
        }
        System.out.println("--------------------------------------------");
    }
}
