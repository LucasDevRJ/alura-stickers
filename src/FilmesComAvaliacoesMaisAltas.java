import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class FilmesComAvaliacoesMaisAltas {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP e buscar os top 250 filmes
        String apiKey = System.getenv("API_KEY");
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
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
        System.out.println("----------|Filmes com avaliações mais altas|----------");
        System.out.println();
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[33m\u001b[47mSérie: " + filme.get("title") + "\u001b[m");
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
