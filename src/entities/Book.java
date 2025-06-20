

package entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Book {

    private static final String ARQUIVO_JSON = "livro.json";

    private String nome;
    private String autor;
    private String editora;
    private int quantidade;
    private String genero;
    private boolean disponivel;
    private String codigo;

    public Book() {
    }

    public Book(String codigo, String nome, String autor, String editora, int quantidade, String genero, boolean disponivel) {
        this.codigo = codigo;
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
        this.quantidade = quantidade;
        this.genero = genero;
        this.disponivel = disponivel;
    }

    

    public static void adicionarLivros(List<Book> biblioteca, Scanner sc) {
        System.out.print("Nome do livro: ");
        String nome = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        int quantidade;
        while (true) {
            System.out.print("Quantidade: ");
            try {
                quantidade = sc.nextInt();
                sc.nextLine(); // Limpar buffer
                if (quantidade <= 0) {
                    System.out.println("Erro: A quantidade deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Insira um número válido.");
                sc.nextLine(); // Limpar input inválido
            }
        }

        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        System.out.print("Editora: ");
        String editora = sc.nextLine();

        
        boolean encontrado = false;
        for (Book livro : biblioteca) {
            if (livro.getNome().equalsIgnoreCase(nome) &&
                livro.getAutor().equalsIgnoreCase(autor) &&
                livro.getEditora().equalsIgnoreCase(editora)) {

                livro.setQuantidade(livro.getQuantidade() + quantidade);
                livro.setDisponivel(true);
                encontrado = true;
                System.out.println("\nLivro já existente. Quantidade atualizada com sucesso!");
                break;
            }
        }

       
        if (!encontrado) {
            String codigo = "LIV" + System.currentTimeMillis();
            boolean disponivel = true;

            Book novoLivro = new Book(codigo, nome, autor, editora, quantidade, genero, disponivel);
            biblioteca.add(novoLivro);
            System.out.println("\nLivro adicionado com sucesso!");
            System.out.println("Código do livro: " + codigo);
        }

        salvarBiblioteca(biblioteca);
    }


    public static void removerLivroPorCodigo(List<Book> biblioteca, Scanner sc) {
        System.out.println("Insira o código do livro que deseja excluir: ");
        String codigo = sc.nextLine();
        Book livroParaRemover = null;

        for (Book livro : biblioteca) {
            if (livro.getCodigo().equals(codigo)) {
                livroParaRemover = livro;
                break;
            }
        }

        if (livroParaRemover != null) {
            biblioteca.remove(livroParaRemover);
            salvarBiblioteca(biblioteca);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado com o código fornecido.");
        }
    }

    public static void consultarCodigo(List<Book> biblioteca, Scanner sc) {
        System.out.println("Digite o nome do livro que deseja saber o código:");
        String livroDigitado = sc.nextLine();
        boolean encontrado = false;

        for (Book livro : biblioteca) {
            if (livro.getNome().equalsIgnoreCase(livroDigitado)) {
                System.out.println("O código do livro é: " + livro.getCodigo());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Não há esse livro no seu acervo.");
        }
    }

    public static void consultarAutor(List<Book> biblioteca, Scanner sc) {
        System.out.println("Digite o nome do autor que deseja consultar:");
        String autorDigitado = sc.nextLine();
        boolean encontrou = false;

        for (Book livro : biblioteca) {
            if (livro.getAutor().equalsIgnoreCase(autorDigitado)) {
                System.out.println("Título deste autor: " + livro.getNome());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não há livros deste autor no seu acervo.");
        }
    }

    public static void consultarPorEditora(List<Book> biblioteca, Scanner sc) {
        System.out.println("Digite o nome da editora que deseja verificar os títulos:");
        String editoraDigitada = sc.nextLine();
        boolean encontrou = false;

        for (Book livro : biblioteca) {
            if (livro.getEditora().equalsIgnoreCase(editoraDigitada)) {
                System.out.println("Título dessa editora: " + livro.getNome());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não há livros dessa editora no seu acervo.");
        }
    }
    
    public static void consultarGenero(List<Book> biblioteca, Scanner sc) {
        System.out.println("Digite o gênero que deseja consultar:");
        String generoDigitado = sc.nextLine();
        boolean encontrou = false;

        for (Book livro : biblioteca) {
            if (livro.getGenero().equalsIgnoreCase(generoDigitado)) {
                System.out.println("Título deste gênero: " + livro.getNome());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não há livros deste gênero no seu acervo.");
        }
    }

    public static List<Book> carregarBiblioteca() {
        try (Reader reader = new FileReader(ARQUIVO_JSON)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Book>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void salvarBiblioteca(List<Book> biblioteca) {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(biblioteca, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a biblioteca no arquivo JSON: " + e.getMessage());
        }
    }
    
    
    public static void editarLivroPorCodigo(List<Book> biblioteca, Scanner sc) {
        System.out.println("Insira o código do livro que deseja editar: ");
        String codigo = sc.nextLine();
        Book livroParaEditar = null;

        for (Book livro : biblioteca) {
            if (livro.getCodigo().equals(codigo)) {
                livroParaEditar = livro;
                break;
            }
        }

        if (livroParaEditar != null) {
            System.out.println("Escolha a informação que deseja editar:");
            System.out.println("1 - título");
            System.out.println("2 - autor");
            System.out.println("3 - quantidade");
            System.out.println("4 - editora");
            System.out.println("5 - gênero ");
            
            
            int opcao = sc.nextInt(); 
            sc.nextLine();
            
            switch(opcao) { 
            
            case 1 : 
            	System.out.println("Digite o novo título: ");
            	String nomeNovo = sc.nextLine(); 
            	livroParaEditar.setNome(nomeNovo);
            	salvarBiblioteca(biblioteca);
            	System.out.println("Título alterado com sucesso");
            	break; 
            case 2: 
            	System.out.println("Digite o novo nome para o autor: ");
            	String autorNovo = sc.nextLine(); 
            	livroParaEditar.setAutor(autorNovo);
            	salvarBiblioteca(biblioteca);
            	System.out.println("Autor alterado com sucesso");
            	break; 
            case 3: 
            	System.out.println("Digite a nova quantidade de exemplares desse título (empréstimo/retorno): ");
            	int qteLivrosAdicionados = sc.nextInt();
                sc.nextLine();  
            	livroParaEditar.setQuantidade(qteLivrosAdicionados);
            	salvarBiblioteca(biblioteca);
            	System.out.println("Quantidade alterada com sucesso");
            	if (livroParaEditar.quantidade <= 0 ) {
            		livroParaEditar.disponivel = false ; 
            	} 
            	salvarBiblioteca(biblioteca);
            	break; 
            case 4: 
            	System.out.println("Digite o nome da editora:  ");
            	String editoraNova = sc.nextLine(); 
            	livroParaEditar.setEditora(editoraNova);
            	salvarBiblioteca(biblioteca);
            	System.out.println("Editora alterada com sucesso");
            	break; 
            case 5: 
            	System.out.println("Digite o nome do gênero:  ");
            	String generoNovo = sc.nextLine(); 
            	livroParaEditar.setGenero(generoNovo);
            	salvarBiblioteca(biblioteca);
            	System.out.println("Gênero alterado com sucesso");
            	break; 
            default:
                System.out.println("Opção inválida!");
                break;
            	
            }
            
            salvarBiblioteca(biblioteca);
            
        } else {
            System.out.println("Livro não encontrado com o código fornecido.");
        }
    }

    public static void exibirLivrosComCodigo() {
        List<Book> biblioteca = carregarBiblioteca();
        
        if (biblioteca.isEmpty()) {
            System.out.println("Não há livros cadastrados na biblioteca.");
            return;
        }
        
        System.out.println("\n=== LIVROS CADASTRADOS ===");
        for (Book livro : biblioteca) {
            System.out.println("Código: " + livro.getCodigo() + " | Título: " + livro.getNome());
        }
        System.out.println("==========================");
    }

    public String getNome() {
        return nome; 
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
