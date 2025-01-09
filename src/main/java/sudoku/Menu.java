package sudoku;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author caio1
 */
public class Menu {
    private Game game;
    /**
     * Exibe a tela de boas-vindas e permite que o usuário escolha como iniciar o jogo de Sudoku.
     * 
     * O método apresenta ao usuário um menu com opções para gerar um jogo aleatório ou 
     * configurar manualmente os valores iniciais do tabuleiro. Dependendo da escolha, ele 
     * inicializa o jogo adequadamente e lida com entradas do usuário.
     * 
     * Em caso de erros, mensagens detalhadas são exibidas para ajudar na identificação 
     * e resolução do problema.
     * 
     * Fluxo de execução:
     * 
     *  O usuário escolhe entre gerar um jogo aleatório ou configurar o tabuleiro.
     *  Se optar por um jogo aleatório, é solicitado o número de valores a serem sorteados.
     *  Se optar por configurar o jogo, o usuário pode inserir valores iniciais no formato "linha, coluna, valor".
     *  O jogo é então iniciado com os valores fornecidos ou gerados.
     * 
     * @throws InputMismatchException se a entrada do usuário for inválida ao escolher opções ou inserir valores.
     * @throws Exception se ocorrer um erro durante a configuração ou início do jogo, exibindo detalhes sobre o erro.
     */
    public void displayWelcomeScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Bem-vindo ao Sudoku!---");
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Gerar jogo aleatório");
        System.out.println("2. Definir jogo");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if(choice == 1) {
            System.out.println("Quantos números você deseja sortear?");
            int num = scanner.nextInt();
            scanner.nextLine();
            game = new Game(num);
        } else if (choice == 2){
            game = new Game();
            System.out.println("Defina os valores iniciais do jogo no formato (linha, coluna, valor). Digite X para encerrar.");
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("X")) {
                    break;
                }
                try {
                    game.setInitialValues(input);
                }catch(Exception e) {
                    StackTraceElement element = e.getStackTrace()[0];
                    System.out.println("ERRO: " + e.getMessage() + " -- ARQUIVO: " + element.getFileName() + " -- LINHA: " + element.getLineNumber());
                }
            }
        }
        try {
            game.start();
        }catch(Exception e) {
            StackTraceElement element = e.getStackTrace()[0];
            System.out.println("ERRO: " + e.getMessage() + " -- ARQUIVO: " + element.getFileName() + " -- LINHA: " + element.getLineNumber());
        }
    }
}
