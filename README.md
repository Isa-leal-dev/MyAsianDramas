# 🌸 MyAsianDramas

Plataforma web para fãs de doramas asiáticos organizarem e descobrirem seus títulos favoritos. Desenvolvido como projeto acadêmico para a disciplina de Programação Orientada a Objetos — FATEC ADS 4º Ciclo.

---

## 📋 Funcionalidades

### Para todos os visitantes
- Navegar pela landing page com doramas em andamento, por gênero e lançamentos do ano
- Filtrar doramas por país (Coréia do Sul, Japão, China, Tailândia, Taiwan)
- Ver todos os doramas em andamento
- Buscar doramas e atores pelo nome
- Ver página detalhada de cada dorama (informações, sinopse, gêneros, elenco)
- Ver página detalhada de cada ator (informações e filmografia)

### Para usuários cadastrados
- Criar listas personalizadas de doramas
- Adicionar e remover doramas das listas
- Editar nome e descrição das listas
- Excluir listas (apenas se não houver doramas adicionados)
- Adicionar doramas manualmente à plataforma
- Editar informações de doramas existentes
- Adicionar e remover gêneros de doramas
- Criar e editar atores
- Adicionar atores ao elenco de doramas

---

## 🛠️ Stack

- **Backend:** Java 21, Spring Boot 4.0.6, JDBC
- **Frontend:** Thymeleaf, HTML, CSS
- **Banco de dados:** PostgreSQL
- **Deploy:** Render (Docker)
- **Dados:** API TMDB (seed automático na inicialização)

---

## 🔑 Variáveis de ambiente (produção)

| Variável | Descrição |
|---|---|
| `SPRING_DATASOURCE_URL` | URL de conexão com o PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |
| `TMDB_API_KEY` | Chave da API TMDB |

---

## ⚙️ Como rodar localmente

### Pré-requisitos
- Java 21+
- Maven
- PostgreSQL

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/Isa-leal-dev/MyAsianDramas.git
cd MyAsianDramas
```

2. Crie o banco de dados PostgreSQL:
```sql
CREATE DATABASE myasiandramas;
```

3. Crie o arquivo `application-local.yml` em `src/main/resources/` (não versionado):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/myasiandramas
    username: seu_usuario
    password: sua_senha

tmdb:
  api-key: sua_chave_tmdb
```

4. Rode a aplicação com o profile local:
```bash
./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
```

5. Acesse em `http://localhost:8080`

> Na primeira execução o DataLoader vai popular o banco automaticamente com ~120 doramas da API TMDB. Aguarde alguns minutos.

