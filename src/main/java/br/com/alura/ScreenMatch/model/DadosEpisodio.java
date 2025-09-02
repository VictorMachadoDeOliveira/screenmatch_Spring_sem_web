package br.com.alura.ScreenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias ("Title") String Titulo,
                            @JsonAlias ("Episode") Integer numero,
                            @JsonAlias ("imdbRating") String AvaliacaoEpisodio,
                            @JsonAlias ("Released") String DataLancamento) {


}
