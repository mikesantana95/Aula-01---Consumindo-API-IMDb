import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os TOP 250 filmes
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_tmc3sz5q";
        URI endereço = URI.create(url);
        HttpClient client = HttpClient.newHttpClient(); //tbm poderia ser declarada como var client = ...
        var request = HttpRequest.newBuilder(endereço).GET().build(); //HttpRequest substituito por var, exemplo analogo citado acima
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> ListadeFilmes = parser.parse(body);
        
        //exibir os dados
        for (Map<String,String> filme : ListadeFilmes) {
  
            System.out.println("\u001b[1mTitle:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1mThumbnail:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[1mRating:\u001b[m " + filme.get("imDbRating"));


            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int Estrelas = (int) classificacao;
            for (int n = 0; n <= Estrelas; n++) {
                System.out.print("⭐");
            } 
            System.out.println("\n");
            
        }

    }
}
