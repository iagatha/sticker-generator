

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {


        String url = "https://imdb-api.com/en/API/Top250Movies/k_78xavxku";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);


        //String url = "https://api.nasa.gov/planetary/apod?api_key=Bv88sqf4fWTcjhpHzVvo4kFhzafIfVlhgdpcVlyH&start_date=2022-06-12&end_date=2022-06-14";
       // ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();


        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();



        }

    }
}
