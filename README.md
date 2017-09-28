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
  * **Atributos:** Login, Senha, Nome, CPF, Telefone, Endereço (Rua, Número, Complemento, Cidade, Estado).
  * **Descrição:** Funcionário do salão de beleza.
* **Cliente**
  * **Atributos:** Código, Nome, Data de Nascimento, CPF, E-mail, Telefone.
  * **Descrição:** Cliente do salão de beleza.
* **Fornecedor**
  * **Atributos:** Código, Nome, CNPJ, Telefone, E-mail, Endereço (Rua, Número, Complemento, Cidade, Estado).
  * **Descrição:** Fornecedor de produtos para o salão de beleza.
* **Serviço**
  * **Atributos:** Código, Nome, Valor, Data, Estado {Agendado, Realizado, Cancelado}.
  * **Descrição:** Serviço oferecido pelo salão de beleza. O atributo Nome representa o serviço em si (corte, depilação, manicure etc.), o Estado serve para possuir um melhor controle sobre a quantidade de serviços Cancelados vs Realizados em um relatório, por exemplo.
* **Produto**
  * **Atributos:** Código, Nome, Marca, Unidade, Quantidade Unitária, Valor, Quantidade em Estoque, Quantidade Mínima de Estoque.
  * **Descrição:** Produto utilizado pelo salão de beleza em um serviço ou vendido à um cliente. O atributo Quantidade Unitária serve para indicar a quantidade que uma unidade pode fornecer. Por exemplo, um Shampoo possui como Unidade ml (mililitros) e como Quantidade Unitária 750, pois em uma unidade vem 750ml.
* **Venda**
  * **Atributos:** Código, Cliente, Funcionário, Serviço, Produto, Data, Forma de Pagamento.
  * **Descrição:** Venda realizada pelo salão de beleza à um cliente. Possui como atributos o Cliente, Funcionário(s), Serviço(s) e/ou Produto(s) relacionados à venda. Além disso, possui a Data em que a venda foi realizada. O valor total será calculado através de uma operação.
* **Compra**
  * **Atributos:** Código, Fornecedor, Produto, Data, Quantidade, Valor.
  * **Descrição:** Compra realizada pelo salão de beleza em relação à um fornecedor. Contém o Fornecedor e o(s) respectivo(s) Produto(s) comprados, além da Data da compra.
