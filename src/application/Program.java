package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Program {

    static final String CAMINHO_JSON = "src/arquivo/livro.json";
    static List<Book> biblioteca = new ArrayList<>();

    public static void main(String[] args) {
        carregarBibliotecaDeJson();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nBem-vindo ao sistema de gerenciamento da sua biblioteca.");
            System.out.println("1 - Adicionar livro");
            System.out.println("2 - Excluir livro por código");
            System.out.println("3 - Consultar código pelo título do livro");
            System.out.println("4 - Consultar livro por autor");
            System.out.println("5 - Consultar livro por editora");
            System.out.println("6 - Editar dados de um livro existente");
            System.out.println("7 - Consultar livro por gênero");
            System.out.println("8 - Visualizar todos os livros disponíveis no acervo");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Book.adicionarLivros(biblioteca, sc);
                    salvarBiblioteca();
                    break;

                case 2:
                    Book.removerLivroPorCodigo(biblioteca, sc);
                    salvarBiblioteca();
                    break;

                case 3:
                    Book.consultarCodigo(biblioteca, sc);
                    break;

                case 4:
                    Book.consultarAutor(biblioteca, sc);
                    break;

                case 5:
                    Book.consultarPorEditora(biblioteca, sc);
                    break;
                
                case 6:
                    Book.editarLivroPorCodigo(biblioteca, sc);
                    salvarBiblioteca();
                    break;
                    
                case 7:
                	Book.consultarGenero(biblioteca, sc);
                	break; 
                	
                case 8:
                	Book.exibirLivrosComCodigo(); 
                	break; 
                	

                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("Deseja continuar? (1 - Sim | 2 - Não)");
            int continuar = sc.nextInt();
            sc.nextLine();

            if (continuar != 1) {
                System.out.println("Obrigado!");
                break; 
            }
        }

        sc.close();
    }

    public static void salvarBiblioteca() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO_JSON)) {
            gson.toJson(biblioteca, writer);
            System.out.println("Biblioteca salva com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static void carregarBibliotecaDeJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO_JSON)) {
            Type listType = new TypeToken<List<Book>>() {}.getType(); 
            biblioteca = gson.fromJson(reader, listType);
            if (biblioteca == null) {
                biblioteca = new ArrayList<>();
            }
            System.out.println("Biblioteca carregada. Número de Livros: " + biblioteca.size());
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Biblioteca vazia será criada.");
            biblioteca = new ArrayList<>();
        }
    }
}
