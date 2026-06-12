# 🌸 MyAsianDramas

Plataforma web para fãs de doramas asiáticos organizarem e descobrirem seus títulos favoritos. Desenvolvido como projeto acadêmico para a disciplina de Programação Orientada a Objetos — FATEC ADS 4º Ciclo.

---

## 🚀 Acesso

A aplicação está hospedada no Render:  
**[myasiandramas.onrender.com](https://myasiandramas.onrender.com)**

> ⚠️ O plano gratuito do Render pode colocar a aplicação em modo sleep após inatividade. Aguarde alguns segundos na primeira visita para o servidor iniciar.

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

## ⚠️ Problemas conhecidos

### ID de sessão aparecendo na URL
Em alguns casos o Tomcat pode exibir o `JSESSIONID` na URL, por exemplo:
```
https://myasiandramas.onrender.com/;jsessionid=ABC123...
```
Isso pode acontecer quando o browser bloqueia ou rejeita o cookie de sessão.

**Solução:** Volte para a página anterior e faça o login novamente. Se o problema persistir, limpe os cookies do browser para o domínio da aplicação.