package br.com.alura.ScreenMatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodios {
    private Integer numeroTemporada;
    private String Titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate DataLancamento;

    public Episodios(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.numeroTemporada = numeroTemporada;
        this.Titulo = dadosEpisodio.tituloEpisodio();
        this.numeroEpisodio = dadosEpisodio.numeroEpisodio();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacaoEpisodio());
        }catch (NumberFormatException e){
            this.avaliacao = 0.0;
        }
        try {
            this.DataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        }catch (DateTimeException e)
        {
            this.DataLancamento = null;
        }
    }

    public Integer getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(Integer numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        DataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return
                "numeroTemporada= " + numeroTemporada +
                ", Titulo= '" + Titulo + '\'' +
                ", numeroEpisodio= " + numeroEpisodio +
                ", Avaliacao= " + avaliacao +
                ", DataLancamento= " + DataLancamento
                ;
    }
}
