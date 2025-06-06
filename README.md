# mylib: um sistema de gerenciamento para bibliotecas

O **mylib** foi criado para auxiliar no gerenciamento de acervos de livros, sejam eles pessoais (domésticos) ou de bibliotecas em geral.

O sistema permite o controle do seu acervo, garantindo que você tenha sempre domínio sobre sua biblioteca. As seguintes funcionalidades estão disponíveis:

1. **Adicionar livro**  
   Permite que o usuário adicione um novo livro ao acervo, cadastrando título, autor, editora, gênero e quantidade. Um código exclusivo será gerado para o livro recém-adicionado.

2. **Consultar código por título**  
   Importante para localizar o código do livro que o usuário deseja excluir, pois a exclusão é feita através do código. Portanto, essa consulta é um passo obrigatório antes da exclusão.

3. **Excluir livro por código**  
   Com o código do livro em mãos, o usuário pode excluí-lo do acervo, recebendo uma confirmação da exclusão bem-sucedida.

4. **Consultar livro por autor**  
   Permite buscar e listar todos os livros de um determinado autor presentes no acervo.

5. **Consultar livro por editora**  
   Permite buscar e listar todos os livros de uma editora específica no acervo.

6. **Consultar livro por gênero**  
   Permite buscar e listar todos os livros de um gênero literário específico.

7. **Editar dados de um livro existente**  
   Possibilita ao usuário alterar informações de um livro já cadastrado, como título, autor, gênero, editora ou quantidade (para controle de entradas e saídas).

8. **Visualizar a lista de livros disponíveis no acervo**  
   Exibe uma lista completa dos livros cadastrados com seus respectivos códigos.

---

## Tecnologias utilizadas

- Linguagem: **Java** (programação orientada a objetos)  
- Armazenamento de dados: **JSON**

## Próximos aprimoramentos:

A finalização completa do sistema inclui o desenvolvimento de um front-end com **React.js**, já em andamento e previsto para as próximas semanas.

## Como executar o sistema:

Até a implementação do front-end, o usuário pode executar o sistema diretamente pelo prompt de comando.

---

> "Um leitor vive mil vidas antes de morrer. O homem que não lê vive apenas uma."  
> — George R. R. Martin

A tecnologia deve servir à melhoria da vida do ser humano nas mais diversas áreas, e não ser um fim em si mesma. Este projeto busca contribuir para esse propósito, incentivando o hábito da leitura e a organização de bibliotecas.
