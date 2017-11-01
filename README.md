# Salão Virtual
### Formação da Equipe:
| Nome | RA | GitHub |
| ------ | ------ | ------ |
| Fábio Augusto Alves Diniz | 170775 | [fabao167](https://github.com/fabao167) |
| João Gabriel Pampanin De Abreu | 175937 | [jgpampanin](https://github.com/jgpampanin) |
| Rafael Tavares Carvalho Barros | 176257 | [Rafatcb](https://github.com/Rafatcb) |
***
### Objetivos
O projeto visa facilitar o gerenciamento de um salão de beleza ainda não automatizado, já que todas as informações são armazenadas em uma agenda, o que torna o processo de atendimento mais lento. 

O sistema conta com funções como: agendamento, visualização de fornecedores e seus fornecimentos, visualização de estoque através da consulta pelo produto etc.
***
### Requisitos Essenciais
* Agendamento de Serviços
* Controle de Fornecedores
* Controle de Produtos e Estoque
***
### Acesso ao Sistema
O acesso ao sistema se dá através de algum funcionário já cadastrado. Apesar das informações ficarem no arquivo correspondente, não é possível visualizar a senha através do mesmo, já que está criptografada. Portanto, para acessar o sistema, é recomendada a utilização do seguinte usuário:
* **Login:** login
* **Senha:** senha
***
### Definição das Classes relacionadas ao Negócio
* **Cliente**
  * **Atributos:** Código, CPF, Nome, Telefone, E-mail e Data de Nascimento.
  * **Descrição:** Cliente do salão de beleza.
  
* **Funcionário**
  * **Atributos:** Login, Senha, CPF, Nome, Telefone e Endereço (Rua, Número, Complemento, Cidade e Estado).
  * **Descrição:** Funcionário do salão de beleza. É o usuário do sistema.
  
* **Fornecedor**
  * **Atributos:** Código, CNPJ, Nome, Telefone, E-mail e Endereço (Rua, Número, Complemento, Cidade e Estado).
  * **Descrição:** Fornecedor do salão de beleza.
  
* **Produto**
  * **Atributos:** Código, Nome, Marca, Quantidade Unitária (número), Unidade (medida), Valor, Quantidade em Estoque e Quantidade Mínima de Estoque.
  * **Descrição:** Produtos que o salão de beleza vende e/ou compra.
  
* **Serviço**
  * **Atributos:** Código, Nome, Valor, Data, Estado ("Cancelado", "Agendado" ou "Realizado"), Funcionário e Cliente.
  * **Descrição:** Serviços que o salão de beleza presta para os clientes.
  
* **Compra**
  * **Atributos:** Código, Data, Valor Total, Produtos Comprados (com quantidade e valor) e Fornecedor.
  * **Descrição:** Compra que o salão de beleza faz, também chamada de Fornecimento.
  
* **Venda**
  * **Atributos:** Código, Data, Cliente, Funcionário, Forma de Pagamento, Produtos Vendidos (com quantidade e valor) e Serviços Vendidos.
  * **Descrição:** Venda que o salão de beleza faz.
  
* **Forma de Pagamento**
  * **Atributos:** Código, Valor Total, Valor Recebido (se Dinheiro), Tipo (se Cartão, "Crédito" ou "Débito") e Quantidade de Parcelas (se "Crédito").
  * **Descrição:** Forma de Pagamento da venda. É especializada em Dinheiro e Cartão.
