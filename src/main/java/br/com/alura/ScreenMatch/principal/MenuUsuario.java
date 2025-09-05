package br.com.alura.ScreenMatch.principal;

import br.com.alura.ScreenMatch.model.DadosEpisodio;
import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.model.DadosTemporada;
import br.com.alura.ScreenMatch.services.ConsumoAPI;
import br.com.alura.ScreenMatch.services.ConverteDados;
import com.sun.jdi.PrimitiveValue;

import java.util.*;

public class MenuUsuario {
    //Variáveis:

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=dd43a8e1";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();


    Scanner scanner = new Scanner(System.in);
    String buscaSerie;


    //Código
    public void exibirMenu(){
        System.out.println("Digite o nome da Série para a busca: ");
        buscaSerie = scanner.nextLine();
        var consumoAPI = new ConsumoAPI();
        var json = consumoAPI.devolverDados(ENDERECO + buscaSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = converteDados.obterdados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.TotalTemporadas(); i++){
            json = consumoAPI.devolverDados(ENDERECO + buscaSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterdados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(System.out::println);
        /*for (int i = 0; i < dadosSerie.TotalTemporadas(); i++){
            List<DadosEpisodio> dadosEpisodios = temporadas.get(i).episodios();
            for(int j = 0; j< dadosEpisodios.size(); j++){
                System.out.println(dadosEpisodios.get(j).Titulo());*/
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.Titulo())));

            {
        }
    }
}
