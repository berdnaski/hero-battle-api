# Heroes Battle API 🦸‍♂️⚔️

Backend completo para sistema de batalha de heróis desenvolvido em Spring Boot.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.5.8**
- **PostgreSQL**
- **Docker & Docker Compose**
- **JPA/Hibernate**
- **Lombok**
- **Validation API**

## 📋 Funcionalidades

- ✅ Cadastro de heróis
- ✅ Consulta com filtros dinâmicos
- ✅ Sistema de batalha com cálculo de dano
- ✅ Validações de entrada
- ✅ Tratamento de erros global
- ✅ CORS configurado para frontend

## 🏗️ Estrutura do Projeto

```
src/main/java/br/com/berdnaski/heroes/
├── controllers/          
├── domain/hero/        
├── dto/                
├── infra/                
├── repositories/
└── services/        
```

## 🐳 Execução com Docker

```bash
# Subir banco de dados
docker-compose up -d

# Executar aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Endpoints da API

### 🦸‍♂️ Criar Herói

**Request:**
```http
POST /api/heroes
Content-Type: application/json
```

```json
{
  "name": "Thor",
  "attackPower": 80,
  "defensePower": 60,
  "health": 100
}
```

**Response:**
```json
{
  "id": "74e29c9e-7c1c-4e92-ada2-ad22a5156b3e",
  "name": "Thor",
  "attackPower": 80,
  "defensePower": 60,
  "health": 100
}
```

### 🔍 Listar Heróis

**Request:**
```http
GET /api/heroes?name=Thor&attackPower=80
```

**Response:**
```json
[
  {
    "id": "74e29c9e-7c1c-4e92-ada2-ad22a5156b3e",
    "name": "Thor",
    "attackPower": 80,
    "defensePower": 60,
    "health": 100
  }
]
```

### ⚔️ Atacar Herói

**Request:**
```http
PUT /api/heroes/{id}/attack
Content-Type: application/json
```

```json
{
  "attackValue": 70
}
```

**Respostas Possíveis:**

```json
// Herói não encontrado
{
  "message": "O herói não está no campo de batalha."
}

// Ataque bloqueado
{
  "message": "O herói não sofreu danos, tente novamente."
}

// Ataque bem-sucedido
{
  "message": "O herói sofreu danos. Vida atual: 90"
}
```

## 🧪 Regras de Negócio

### Cálculo de Dano

```
Dano = ValorAtaque - PoderDefesa
NovaVida = VidaAtual - Dano
```

**Exemplo:**
- Vida: 100
- Defesa: 60
- Ataque: 70
- **Dano:** 70 - 60 = 10
- **Nova Vida:** 100 - 10 = 90

### Validações

- **Nome:** não pode ser nulo ou vazio
- **Poder de Ataque:** deve ser maior que zero
- **Poder de Defesa:** deve ser maior que zero
- **Vida:** deve ser maior que zero
- **Valor do Ataque:** deve ser maior que zero

## 🔧 Configuração

### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/heroes_db
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
app.cors.allowed-origins=http://localhost:4200
```

### docker-compose.yml

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: heroes_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
```

## 🎯 Como Testar

1. **Subir o banco de dados:**
   ```bash
   docker-compose up -d
   ```

2. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

3. **Testar os endpoints** usando Insomnia, Postman ou curl

## 📝 Desenvolvimento

### Commits Semânticos

```bash
feat: nova funcionalidade
fix: correção de bug
docs: documentação
refactor: refatoração de código
test: adição de testes
```

### Próximos Passos

- [ ] Implementar frontend Angular
- [ ] Adicionar testes unitários
- [ ] Configurar CI/CD
- [ ] Adicionar autenticação JWT
- [ ] Implementar histórico de batalhas

---

**Desenvolvido com ☕ e ❤️ - Erick Berdnaski**