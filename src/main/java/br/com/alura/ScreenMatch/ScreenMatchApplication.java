package br.com.alura.ScreenMatch;

import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.services.ConsumoAPI;
import br.com.alura.ScreenMatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
