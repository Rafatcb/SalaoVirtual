# Salão Virtual
### Formação da Equipe:
| Nome | RA | GitHub |
| ------ | ------ | ------ |
| Fábio Augusto Alves Diniz | 170775 | [fabao167](https://github.com/fabao167) |
| João Gabriel Pampanin De Abreu | 175937 | [jgpampanin](https://github.com/jgpampanin) |
| Rafael Tavares Carvalho Barros | 176257 | [Rafatcb](https://github.com/Rafatcb) |
***
### Objetivos
O projeto visa facilitar o gerenciamento de um salão de beleza ainda não automatizado, já que todas as informações são armazenadas em uma agenda, o que torna o processo de atendimento mais lento. Outro aspecto relevante é que várias pessoas preferem realizar o pagamento dias após o atendimento, tornando a consulta através da agenda do valor a ser pago muito desgastante e mais demorada do que um sistema automatizado seria capaz de proporcionar.

O sistema contará com funções como: agendamento, gerenciamento de fornecedores, contas a pagar e receber e um sistema de estoque para seus produtos.
***
### Requisitos Essenciais
* Agendamento de Serviços
* Controle de Fornecedores
* Controle de Produtos e Estoque
***
### Definição das Primeiras Classes
* **Funcionário**
  * **Atributos:** CPF, Nome, Telefone, Salário, Cargo.
  * **Descrição:** Funcionário do salão de beleza.
* **Cliente**
  * **Atributos:** CPF, Nome, Telefone, E-mail, Data de nascimento.
  * **Descrição:** Cliente do salão de beleza.
* **Fornecedor**
  * **Atributos:** CNPJ, Nome, Telefone, E-mail, Tipo de Produto, Marca.
  * **Descrição:** Fornecedor de produtos para o salão de beleza (para consumo e para venda). O atributo Tipo de Produto representa o que é fornecido (produtos para cabelo, maquiagem, esmalte etc.). O atributo Marca é para o caso do Fornecedor representar uma marca de produto em específico.
* **Serviço**
  * **Atributos:** Nome, Preço Base, Descrição.
  * **Descrição:** Serviço oferecido pelo salão de beleza. O atributo Nome representa o serviço em si (corte, depilação, manicure etc.), o Preço Base possui esse nome pois o valor pode ser alterado conforme as necessidades do cliente, e a Descrição é um campo opcional para descrever os detalhes do serviço, se necessário.
* **Produto**
  * **Atributos:** Nome, Marca, Preço, Descrição, Quantidade em Estoque.
  * **Descrição:** Produto utilizado pelo salão de beleza em um serviço ou vendido à um cliente. O atributo Descrição é um campo opcional que contém detalhes do produto, caso necessário.
* **Venda**
  * **Atributos:** Cliente, Funcionário, Serviço, Produto, Data, Preço Total, Forma de Pagamento.
  * **Descrição:** Venda realizada pelo salão de beleza à um cliente. Possui como atributos o Cliente, Funcionário(s), Serviço(s) e/ou Produto(s) relacionados à venda. Além disso, possui a Data em que a venda foi realizada.
* **Compra**
  * **Atributos:** Fornecedor, Produto, Data, Preço Total, Forma de Pagamento.
  * **Descrição:** Compra realizada pelo salão de beleza em relação à um fornecedor. Contém o Fornecedor e o(s) respectivo(s) Produto(s) comprados, além da Data da compra.
* **Agenda**
  * **Atributos:** Data, Horário, Cliente, Serviço, Funcionário.
  * **Descrição:** Agendamento de um serviço destinado à um cliente. Possui a Data e Horário do atendimento, além de, opcionalmente, o Funcionário responsável pelo serviço.
