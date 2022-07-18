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
        System.out.println("----------|Filmes com avaliações mais altas|----------");
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Filme: " + filme.get("title"));
            System.out.println("Ano de lançamento: " + filme.get("year"));
            System.out.println("Link do poster: " + filme.get("image"));
            System.out.println("Elenco: " + filme.get("crew"));
            System.out.println("Nota final: " + filme.get("imDbRating"));
            System.out.println("Notas dadas: " + filme.get("imDbRatingCount"));
            System.out.println();
        }
        System.out.println("--------------------------------------------------------");
    }
}
