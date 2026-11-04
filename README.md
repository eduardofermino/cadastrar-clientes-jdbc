# Sistema de Cadastro de Clientes

## Sobre o Projeto
Este é um projeto prático de aplicação desktop em Java focado no gerenciamento de cadastros de clientes. O sistema implementa as operações fundamentais de um CRUD (Create, Read, Update, Delete) com persistência em banco de dados relacional. O objetivo principal deste projeto é aplicar conceitos de interface gráfica, arquitetura de software e conexão direta com banco de dados.

## Tecnologias e Padrões Utilizados
* Linguagem: Java
* Interface Gráfica: Java Swing
* Banco de Dados: MySQL
* Integração: JDBC (Java Database Connectivity)
* Arquitetura: MVC (Model-View-Controller) simplificado com padrão DAO (Data Access Object) e DTO (Data Transfer Object)

## Arquitetura do Projeto
* **View**: Classes responsáveis pela interface gráfica (telas, botões, tabelas) desenvolvidas com JFrame e JPanel.
* **DTO**: Classes de modelo que transportam os dados entre a tela e o banco de dados.
* **DAO**: Classes que contêm as instruções SQL e gerenciam as transações com o banco de dados.
* **Conexão**: Classe isolada para gerenciar as credenciais e o estabelecimento da comunicação com o banco.

## Resumo de Aprendizado: Dinâmica do JDBC
Durante o desenvolvimento deste projeto, a dinâmica de funcionamento do JDBC foi estruturada nos seguintes passos essenciais:

### 1. Configuração e Conexão
* O primeiro passo consiste em instalar o driver do banco de dados no projeto.
* É criada uma classe de conexão onde são definidos os parâmetros básicos (USUÁRIO, SENHA, URL e DRIVER). Essa classe apoia-se nas bibliotecas do MySQL para criar a conexão e armazená-la em uma variável.

### 2. Estruturação dos Dados
* É criado um **DTO** (Data Transfer Object) para agir como o modelo (Model) do projeto, representando as entidades do banco em formato de objeto Java.
* É criado um **DAO** (Data Access Object) para isolar e implementar toda a lógica de acesso aos dados.

### 3. Execução de Comandos (UPDATE, DELETE, INSERT)
Dentro do DAO, as queries são criadas em variáveis do tipo String, utilizando o caractere `?` para definir parâmetros dinâmicos. A conexão é instanciada e utilizada junto com a classe `PreparedStatement`.

```java
String query = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
con = new Conexao().getConnection();

PreparedStatement pst = null;
pst = con.prepareStatement(query);
pst.setString(1, cliente.getNome());
pst.setString(2, cliente.getEmail());
pst.setString(3, cliente.getTelefone());
pst.setInt(4, cliente.getId());

pst.executeUpdate();
```
O método `executeUpdate()` é o responsável por executar queries que alteram o estado do banco de dados (UPDATE, DELETE e INSERT).

### 4. Leitura e Listagem de Dados (SELECT)
Para queries do tipo SELECT, utiliza-se o método `executeQuery()`. 

Ao buscar listar todas as linhas de uma tabela, o retorno do comando é armazenado em um objeto `ResultSet`. O `ResultSet` gerencia o cursor de leitura do banco, mas não é uma estrutura de dados nativa do Java (como uma lista). 

Portanto, é necessário criar uma estrutura de dados (como um ArrayList) e alimentá-la iterando sobre o `ResultSet` através do comando `set.next()`:

```java
ResultSet set = pst.executeQuery();

while(set.next()){
    ClienteDto clienteDto = new ClienteDto(
        set.getInt("id"), 
        set.getString("nome"), 
        set.getString("email"), 
        set.getString("telefone")
    );
    clientes.add(clienteDto);
}
```