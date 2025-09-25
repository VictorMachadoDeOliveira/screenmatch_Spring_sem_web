package br.com.alura.ScreenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias ("Title") String tituloEpisodio,
                            @JsonAlias ("Season") Integer temporada,
                            @JsonAlias ("Episode") Integer numeroEpisodio,
                            @JsonAlias ("imdbRating") String avaliacaoEpisodio,
                            @JsonAlias ("Released") String dataLancamento) {


}
