# language: pt

Funcionalidade: adicionar novo livro no acervo virtual

  Cenário: Cadastro com quantidade válida
    Dado que o usuário esteja na tela de adicionar livro
    Quando ele preencher todos os campos corretamente
    E informar quantidade 5
    Então o sistema deve cadastrar o livro
    E exibir mensagem de sucesso

  Cenário: Cadastro com quantidade inválida
    Dado que o usuário esteja na tela de adicionar livro
    Quando ele informar quantidade igual a 0
    E informar quantidade menor que 0
    Então o sistema deve recusar o cadastro
    E solicitar que o usuário informe uma quantidade válida
