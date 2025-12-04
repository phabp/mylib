package tests.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import entities.Book;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.E;

public class AddBookSteps {

    private boolean quantidadeEhValida;

    @Dado("que o usuário esteja na tela de adicionar livro")
    public void que_o_usuario_esteja_na_tela_de_adicionar_livro() {
        // Contexto inicial — não precisa fazer nada neste momento
    }

    @Quando("ele preencher todos os campos corretamente")
    public void ele_preencher_todos_os_campos_corretamente() {
        // Não verificamos campos nessa fase — o foco é a quantidade
    }

    @E("informar quantidade {int}")
    public void informar_quantidade(int quantidade) {
        quantidadeEhValida = Book.quantidadeValida(quantidade);
    }

    @Entao("o sistema deve cadastrar o livro")
    public void o_sistema_deve_cadastrar_o_livro() {
        assertTrue("Esperava quantidade válida", quantidadeEhValida);
    }

    @E("exibir mensagem de sucesso")
    public void exibir_mensagem_de_sucesso() {
        // Poderíamos verificar mensagens do sistema no futuro
    }

    // ===============================
    // CENÁRIOS DE QUANTIDADE INVÁLIDA
    // ===============================

    @Quando("ele informar quantidade igual a {int}")
    public void ele_informar_quantidade_igual_a(int quantidade) {
        quantidadeEhValida = Book.quantidadeValida(quantidade);
    }

    @E("informar quantidade menor que {int}")
    public void informar_quantidade_menor_que(int limite) {
        int quantidadeInvalida = limite - 1;
        quantidadeEhValida = Book.quantidadeValida(quantidadeInvalida);
    }

    @Entao("o sistema deve recusar o cadastro")
    public void o_sistema_deve_recusar_o_cadastro() {
        assertFalse("Esperava quantidade inválida", quantidadeEhValida);
    }

    @E("solicitar que o usuário informe uma quantidade válida")
    public void solicitar_que_o_usuario_informe_uma_quantidade_valida() {
        // Aqui poderia verificar mensagem do sistema
    }
}
