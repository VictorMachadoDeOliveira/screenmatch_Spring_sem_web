📺 ScreenMatch (Spring Boot sem Web)

Projeto desenvolvido durante o curso da Alura para praticar Spring Boot, consumo de APIs externas e manipulação de dados em Java.
O objetivo é criar um sistema de séries que se conecta à API do IMDb para buscar informações de séries e episódios.

🚀 Funcionalidades atuais

Consumo de dados de séries via API IMDb.

Conversão de dados JSON para objetos Java (DTOs).

Exibição de informações básicas da série e seus episódios.

🛠 Tecnologias utilizadas

Java 17+

Spring Boot

Jackson (JSON)

HttpClient (Java nativo)

Maven

📂 Estrutura do projeto

ScreenMatchApplication.java → Classe principal da aplicação.

ConsumoAPI.java → Responsável por consumir a API do IMDb.

IConverteDados.java → Interface para conversão de dados JSON.

ConverteDados.java → Implementação da conversão de dados.

DadosSerie.java → DTO para dados da série.

DadosEpisodio.java → DTO para dados de episódios.

🔮 Futuras melhorias

Detalhar cada episódio separadamente.
