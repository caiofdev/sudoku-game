package sudoku;
import java.util.Scanner;

/**
 *
 * @author caiofdev
 */
public class Game {
    private Board board;
    private boolean[][] initialValues;
    
    /**
     * Construtor da classe Game.
     * 
     * Este construtor inicializa uma nova instância do jogo. Ele cria um novo tabuleiro 
     * através da classe {@code Board} e também inicializa uma matriz de valores booleanos 
     * {@code initialValues} de tamanho 9x9. A matriz {@code initialValues} pode ser usada para 
     * armazenar informações sobre quais células do tabuleiro possuem valores iniciais (não modificáveis).
     * 
     * O tabuleiro é configurado para um estado inicial vazio, onde todas as células são definidas como zero,
     * e a matriz {@code initialValues} é usada para rastrear as células que foram configuradas com 
     * valores iniciais (que não podem ser alterados pelo jogador durante o jogo).
     */
    public Game() {
        board = new Board();
        initialValues = new boolean[9][9];
    }
    
    /**
     * Construtor da classe Game com número de valores iniciais.
     * 
     * Este construtor inicializa uma nova instância do jogo, criando um novo tabuleiro 
     * através da classe {@code Board} e uma matriz de valores booleanos {@code initialValues} 
     * de tamanho 9x9. A matriz {@code initialValues} pode ser usada para identificar as células 
     * que possuem valores iniciais (não modificáveis).
     * 
     * Além disso, este construtor chama o método {@code Generator.generateRandomBoard} para gerar 
     * um tabuleiro aleatório com um número específico de valores iniciais, definido pelo parâmetro {@code num}.
     * 
     * O parâmetro {@code num} indica a quantidade de células do tabuleiro que devem ser preenchidas 
     * com valores iniciais, e a função {@code generateRandomBoard} preenche o tabuleiro com esses valores 
     * enquanto garante que o tabuleiro resultante seja válido.
     *
     * @param num O número de células que devem ser preenchidas com valores iniciais no tabuleiro. 
     *            Deve ser um valor entre 0 e 81, representando o total de células do tabuleiro 9x9.
     */
    public Game(int num) {
        board = new Board();
        initialValues = new boolean[9][9];
        Generator.generateRandomBoard(board, num);
    }
    
    /**
     * Define os valores iniciais do tabuleiro com base em uma entrada de string.
     * 
     * Este método recebe uma string de entrada no formato "linha,coluna,valor", com múltiplas entradas 
     * separadas por parênteses. Cada entrada representa uma célula do tabuleiro que será preenchida 
     * com um valor inicial. A string de entrada é processada para extrair as coordenadas e os valores,
     * e o método preenche o tabuleiro de acordo. Além disso, ele marca as células preenchidas como 
     * valores iniciais na matriz {@code initialValues}.
     * 
     * O formato da entrada deve ser uma sequência de entradas como "(linha,coluna,valor)", 
     * onde:
     * - linha é o número da linha (de 1 a 9),
     * - coluna é o número da coluna (de 1 a 9),
     * - valor é o número que deve ser inserido na célula (de 1 a 9).
     * 
     * O método ajusta a indexação para começar em 0 (em vez de 1) para acessar o tabuleiro e atualizar 
     * a matriz {@code initialValues}, que armazena a informação sobre quais células possuem valores 
     * iniciais (não modificáveis).
     * 
     * @param input A string contendo as células a serem preenchidas no tabuleiro, 
     *              no formato "(linha,coluna,valor)", com as entradas separadas por parênteses.
     */
    public void setInitialValues(String input) {
        String[] entries = input.split("\\)\\(");
        for (String entry : entries) {
            entry = entry.replace("(", "").replace(")", "");
            String[] parts = entry.split(",");
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            int value = Integer.parseInt(parts[2]);
            board.setValue(row, col, value);
            initialValues[row][col] = true;
        }
    }
    
    /**
     * Inicia e executa o loop principal do jogo.
     * 
     * Este método é responsável por controlar o fluxo de interação do jogador com o jogo. 
     * Ele exibe o tabuleiro atual e apresenta um menu com várias opções para o jogador, incluindo:
     * - Adicionar uma jogada,
     * - Remover uma jogada,
     * - Verificar se o tabuleiro está correto,
     * - Pedir uma dica para uma posição específica,
     * - Sair do jogo.
     * 
     * O método usa um objeto {@code Scanner} para ler as entradas do jogador e, conforme a escolha 
     * do jogador, chama os métodos apropriados, como {@code addMove}, {@code removeMove}, 
     * {@code Validator.validate}, ou {@code giveHint}. O loop continua até que o jogador escolha 
     * a opção de sair do jogo, momento em que o programa exibe uma mensagem de agradecimento e termina.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.printBoard();
            System.out.println("---Escolha uma opção:---");
            System.out.println("\n1. Adicionar jogada");
            System.out.println("2. Remover jogada");
            System.out.println("3. Verificar");
            System.out.println("4. Dica");
            System.out.println("5. Sair");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            /**
             * Embora este trecho de código possa ser substituído por uma estrutura switch-case, 
             * optei por mantê-lo como um conjunto de if-else após algumas implementações e testes.
             * Durante os testes, percebi que, ao tentar refatorar para switch-case, a aplicação
             * apresentava comportamentos inesperados e não estava funcionando corretamente.
             * Após investigar o comportamento, decidi deixar dessa forma para garantir a estabilidade 
             * e o funcionamento correto da aplicação. Portanto, o uso de if-else foi mantido por questões 
             * de compatibilidade com o restante do código e para evitar problemas de execução.
             */
            if (choice == 1) {
                System.out.println("\nDigite a jogada no formato (linha, coluna, valor):");
                String input = scanner.nextLine();
                addMove(input);
            } else if (choice == 2) {
                System.out.println("\nDigite a posição para remover no formato (linha, coluna):");
                String input = scanner.nextLine();
                removeMove(input);
            } else if (choice == 3) {
                Validator.validate(board);
            } else if (choice == 4) {
                System.out.println("\nDigite a posição para a dica no formato (linha, coluna):");
                String input = scanner.nextLine();
                giveHint(input);
            } else if (choice == 5) {
                System.out.println("\nObrigado por jogar!");
                break;
            }
        }
    }
    
    /**
     * Adiciona uma jogada no tabuleiro a partir de uma entrada fornecida pelo jogador.
     * 
     * Este método processa a entrada do jogador, que deve estar no formato "(linha,coluna,valor)", 
     * e preenche o tabuleiro com o valor fornecido, caso a célula não tenha um valor inicial (não modificado).
     * A entrada pode conter múltiplas jogadas, separadas por parênteses, e o método irá processar cada uma delas.
     * 
     * Se a célula já tiver um valor atribuído (for uma célula inicial), o método exibirá uma mensagem informando 
     * que a jogada não foi realizada. Caso contrário, o valor fornecido será atribuído à célula correspondente no tabuleiro.
     * 
     * O formato de entrada deve ser uma sequência de entradas como "(linha,coluna,valor)", onde:
     * - linha é o número da linha (de 1 a 9),
     * - coluna é o número da coluna (de 1 a 9),
     * - valor é o número a ser inserido na célula (de 1 a 9).
     * 
     * @param input A string contendo as jogadas a serem inseridas no tabuleiro, 
     *              no formato "(linha,coluna,valor)", com as entradas separadas por parênteses.
     */
    private void addMove(String input) {
        String[] entries = input.split("\\)\\(");
        for (String entry : entries) {
            entry = entry.replace("(", "").replace(")", "");
            String[] parts = entry.split(",");
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            int value = Integer.parseInt(parts[2]);
            if (value < 1 || value > 9) {
                System.out.println("Valor inválido. Por favor, insira um valor entre 1 e 9.");
                return;
            }
            if (initialValues[row][col]) {
                System.out.println("\nA entrada (" + (row + 1) + "," + (col + 1) + "," + value +") não foi inserida, pois já possui um valor atribuído.");
            } else {
                board.setValue(row, col, value);
            }
        }
    }
    
    /**
     * Remove uma jogada do tabuleiro, definindo o valor da célula para zero.
     * 
     * Este método processa a entrada do jogador, que deve estar no formato "(linha,coluna)", 
     * e remove a jogada da célula especificada no tabuleiro, ou seja, define o valor dessa célula como zero.
     * Se a célula contiver um valor inicial (não modificável), o método exibe uma mensagem informando que a 
     * jogada não pode ser removida. Caso contrário, o valor da célula é removido.
     * 
     * O formato de entrada deve ser "(linha,coluna)", onde:
     * - linha é o número da linha (de 1 a 9),
     * - coluna é o número da coluna (de 1 a 9).
     * 
     * @param input A string contendo a posição da célula a ser removida, no formato "(linha,coluna)".
     */
    private void removeMove(String input) {
        String[] parts = input.replace("(", "").replace(")", "").split(",");
        int row = Integer.parseInt(parts[0]) - 1;
        int col = Integer.parseInt(parts[1]) - 1;
        if (initialValues[row][col]) {
            System.out.println("\nA posição (" + (row + 1) + "," + (col + 1) + ") não pode ser removida, pois é um valor inicial.");
        } else {
            board.setValue(row, col, 0);
        }
    }
    
    /**
     * Fornece uma dica de valores possíveis para uma célula do tabuleiro.
     * 
     * Este método processa a entrada do jogador, que deve estar no formato "(linha,coluna)", 
     * e exibe os valores possíveis que podem ser inseridos na célula especificada. O método chama o 
     * método {@code Validator.getPossibleValues} para obter os valores válidos para aquela posição no tabuleiro.
     * 
     * O formato de entrada deve ser "(linha,coluna)", onde:
     * - linha é o número da linha (de 1 a 9),
     * - coluna é o número da coluna (de 1 a 9).
     * 
     * A dica apresentada é uma lista dos números que podem ser inseridos na célula sem violar as regras 
     * do Sudoku (ou seja, sem repetir valores na linha, coluna ou quadrado 3x3 correspondente).
     * 
     * @param input A string contendo a posição da célula para a qual a dica será fornecida, 
     *              no formato "(linha,coluna)".
     */
    private void giveHint(String input) {
        String[] parts = input.replace("(", "").replace(")", "").split(",");
        int row = Integer.parseInt(parts[0]) - 1;
        int col = Integer.parseInt(parts[1]) - 1;
        System.out.println("\nValores possíveis para (" + (row + 1) + "," + (col + 1) + "): " + Validator.getPossibleValues(board, row, col));
    }
}