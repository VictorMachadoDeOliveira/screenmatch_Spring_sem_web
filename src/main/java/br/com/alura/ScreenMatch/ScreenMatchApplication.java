package br.com.alura.ScreenMatch;

import br.com.alura.ScreenMatch.model.DadosEpisodio;
import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.model.DadosTemporada;
import br.com.alura.ScreenMatch.services.ConsumoAPI;
import br.com.alura.ScreenMatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.devolverDados("https://www.omdbapi.com/?t=Squid+Game&apikey=dd43a8e1");
		System.out.println(json );

		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obterdados(json, DadosSerie.class);
		System.out.println(dadosSerie);
		json = consumoAPI.devolverDados("https://www.omdbapi.com/?t=Squid+Game&season=1&episode=1&apikey=dd43a8e1");
		DadosEpisodio dadosEpisodio = converteDados.obterdados(json,DadosEpisodio.class );
		System.out.println(dadosEpisodio);
		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dadosSerie.TotalTemporadas(); i++){
			json = consumoAPI.devolverDados("https://www.omdbapi.com/?t=Squid+Game&season=" + i + "&apikey=dd43a8e1");
			DadosTemporada dadosTemporada = converteDados.obterdados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
		temporadas.forEach(System.out::println);
	}
}
