package br.com.alura.ScreenMatch.principal;

import br.com.alura.ScreenMatch.model.DadosEpisodio;
import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.model.DadosTemporada;
import br.com.alura.ScreenMatch.model.Episodios;
import br.com.alura.ScreenMatch.services.ConsumoAPI;
import br.com.alura.ScreenMatch.services.ConverteDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MenuUsuario {
    private static final Logger log = LoggerFactory.getLogger(MenuUsuario.class);
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
        try {
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


        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).toList();
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.tituloEpisodio())));
        System.out.println("Melhores episódios!: ");

        dadosEpisodios.stream().filter(e -> !e.avaliacaoEpisodio().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro (N/A): " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacaoEpisodio)
                .reversed())
                .peek(e -> System.out.println("Primeira Comparação: " + e))
                .limit(5)
                .peek(e -> System.out.println("Primeira Limitação: " + e))
                .map(e -> e.tituloEpisodio().toUpperCase())
                .peek(e -> System.out.println("Mapeamento: " + e))
                .forEach(System.out::println);


        System.out.println("Digite um trecho do Título para uma busca mais precisa!: ");
        var trechoTitulo = scanner.nextLine();

        Optional<DadosEpisodio> episodioBuscado = dadosEpisodios.stream()
                .filter(e -> e.tituloEpisodio().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado: " + episodioBuscado.get().tituloEpisodio());
            System.out.println("Temporada: " + episodioBuscado.get().temporada());
            System.out.println("Avaliacao: " + episodioBuscado.get().avaliacaoEpisodio());
        }else{
            System.out.println("Episódio não encontrado!");
        }
        List<Episodios> listaEpisodios = temporadas.stream().
               flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodios(t.temporada(), d)))
                .collect(Collectors.toList());

        Map<Integer, Double> avaliacoesPorTemporada = listaEpisodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodios::getNumeroTemporada,
                         Collectors.averagingDouble(Episodios::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = listaEpisodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));
        System.out.println("Média de Avaliações das temporadas: " + est.getAverage());
        }catch (NullPointerException e){
            System.out.println("Nome não encontrado!");
            System.exit(1);
        }

//        listaEpisodios.forEach(System.out::println);
//
//        System.out.println("Defina um ANO mínimo para visualizar os episódios em diante: ");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//
//        listaEpisodios.stream()
//                .filter(e -> e.getDataLancamento() != null & e.getDataLancamento().isAfter(dataBusca))
//                        .forEach(e -> System.out.println(
//                                "Temporada: " + e.getNumeroTemporada() +
//                                " Título do Episódio: " + e.getTitulo() +
//                                " Data de Lançamento: " + e.getDataLancamento().format(formatter) +
//                                " Numero do Episódio: " + e.getNumeroEpisodio()
//                        ));

    }
}
