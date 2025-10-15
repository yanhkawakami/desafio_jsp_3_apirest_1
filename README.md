# Desafio: CRUD de Clientes

## Descrição

Crie um projeto **Spring Boot** contendo um **CRUD completo** de web services REST para acessar um recurso de **clientes**, implementando as cinco operações básicas:

- **Busca paginada de recursos**
- **Busca de recurso por ID**
- **Inserir novo recurso**
- **Atualizar recurso**
- **Deletar recurso**

---

## Especificações Técnicas

O projeto deve:

- Utilizar **Java** e **Maven** como gerenciador de dependências.  
- Ter um **ambiente de testes configurado** com banco de dados **H2**.  
- Conter **seed de pelo menos 10 clientes** com dados **significativos** (ex: nomes reais e valores plausíveis).  

---

## Entidade `Client`

A entidade `Client` deve possuir os seguintes atributos:

| Campo | Tipo | Descrição |
|:------|:-----|:-----------|
| `id` | Long | Identificador único |
| `name` | String | Nome do cliente |
| `cpf` | String | CPF do cliente |
| `income` | Double | Renda do cliente |
| `birthDate` | LocalDate | Data de nascimento |
| `children` | Integer | Quantidade de filhos |

> **Observação:** A JPA converte automaticamente nomes em *camelCase* para *snake_case*.  
> Portanto, o campo `birthDate` será persistido como `birth_date` no banco de dados.  
> Certifique-se de que scripts SQL sigam esse padrão.

---

## Validações

O projeto deve tratar as seguintes **exceções** e **validações**:

- **ID não encontrado** (para GET por ID, PUT e DELETE) → Retornar **404 Not Found**
- **Erro de validação** → Retornar **422 Unprocessable Entity**, com mensagens personalizadas:

| Campo | Regra |
|:------|:------|
| `name` | Não pode ser vazio |
| `birthDate` | Não pode ser uma data futura (`@PastOrPresent`) |

---

## Exemplo de Requisições

### 1. Busca de cliente por ID

```
GET /clients/1
```

---

### 2. Busca paginada de clientes

```
GET /clients?page=0&size=6&sort=name
```

---

### 3. Inserção de novo cliente

```
POST /clients
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```

---

### 4. Atualização de cliente

```
PUT /clients/1
{
  "name": "Maria Silvaaa",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```

---

### 5. Deleção de cliente

```
DELETE /clients/1
```

---

## Checklist de Validação

| # | Requisito | Resultado Esperado |
|:-:|:-----------|:------------------|
| 1 | Busca por ID retorna cliente existente | ✅ |
| 2 | Busca por ID retorna 404 para cliente inexistente | ✅ |
| 3 | Busca paginada retorna listagem corretamente | ✅ |
| 4 | Inserção de cliente com dados válidos | ✅ |
| 5 | Inserção de cliente retorna 422 com dados inválidos | ✅ |
| 6 | Atualização de cliente com dados válidos | ✅ |
| 7 | Atualização de cliente retorna 404 para cliente inexistente | ✅ |
| 8 | Atualização de cliente retorna 422 com dados inválidos | ✅ |
| 9 | Deleção de cliente existente | ✅ |
| 10 | Deleção de cliente inexistente retorna 404 | ✅ |

---

## Entrega

O projeto deve ser entregue em um **repositório público no GitHub**, contendo:

- Código-fonte completo do projeto Spring Boot  
- Banco de dados de testes H2 configurado  
- Arquivo `README.md` explicativo  
- Scripts de seed e exemplos de requisições REST

---
