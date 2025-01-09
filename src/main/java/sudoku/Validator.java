package sudoku;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author caiofdev
 */
public class Validator {
    /**
     * Valida o estado atual do tabuleiro para garantir que ele está correto.
     * 
     * Este método verifica se as linhas, colunas e caixas (subgrades 3x3) do tabuleiro são válidas 
     * em relação às regras de um jogo, como Sudoku, por exemplo. Ele percorre o tabuleiro e, 
     * para cada índice de 0 a 8, chama métodos auxiliares (presumivelmente {@code isValidRow}, 
     * {@code isValidColumn}, e {@code isValidBox}) para verificar se as respectivas linhas, 
     * colunas e caixas do tabuleiro estão corretas. 
     * 
     * Se o tabuleiro for válido (sem erros), o método imprime uma mensagem indicando que o 
     * jogo está correto até o momento. Caso contrário, imprime uma mensagem indicando que 
     * o jogo contém erros.
     *
     * @param board O objeto {@code Board} que representa o tabuleiro a ser validado.
     */
    public static void validate(Board board) {
        boolean valid = true;
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i) || !isValidColumn(board, i) || !isValidBox(board, i)) {
                valid = false;
            }
        }
        if (valid) {
            System.out.println("O jogo está correto até o momento.");
        } else {
            System.out.println("O jogo contém erros.");
        }
    }
    
    /**
     * Verifica se uma linha do tabuleiro é válida.
     * 
     * Este método valida se todos os valores em uma linha específica do tabuleiro são únicos. 
     * Ele percorre todas as colunas da linha indicada pelo parâmetro {@code row} e utiliza um 
     * conjunto (`Set`) para garantir que não existam valores duplicados. Caso o valor na célula 
     * seja diferente de zero e já exista no conjunto, o método imprime uma mensagem de erro 
     * indicando a linha com a duplicata e retorna {@code false}. Se a linha for válida (sem duplicatas), 
     * o método retorna {@code true}.
     *
     * @param board O objeto {@code Board} que representa o tabuleiro a ser validado.
     * @param row O índice da linha a ser verificada. Deve ser um valor entre 0 e 8 (inclusive).
     * @return {@code true} se a linha for válida (sem valores duplicados), {@code false} caso contrário.
     */
    private static boolean isValidRow(Board board, int row) {
        Set<Integer> set = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            int value = board.getValue(row, col);
            if (value != 0 && !set.add(value)) {
                System.out.println("Erro na linha " + (row + 1));
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica se uma coluna do tabuleiro é válida.
     * 
     * Este método valida se todos os valores em uma coluna específica do tabuleiro são únicos. 
     * Ele percorre todas as linhas da coluna indicada pelo parâmetro {@code col} e utiliza um 
     * conjunto (`Set`) para garantir que não existam valores duplicados. Caso o valor na célula 
     * seja diferente de zero e já exista no conjunto, o método imprime uma mensagem de erro 
     * indicando a coluna com a duplicata e retorna {@code false}. Se a coluna for válida (sem duplicatas), 
     * o método retorna {@code true}.
     *
     * @param board O objeto {@code Board} que representa o tabuleiro a ser validado.
     * @param col O índice da coluna a ser verificada. Deve ser um valor entre 0 e 8 (inclusive).
     * @return {@code true} se a coluna for válida (sem valores duplicados), {@code false} caso contrário.
     */
    private static boolean isValidColumn(Board board, int col) {
        Set<Integer> set = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            int value = board.getValue(row, col);
            if (value != 0 && !set.add(value)) {
                System.out.println("Erro na coluna " + (col + 1));
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica se uma caixa (subgrade 3x3) do tabuleiro é válida.
     * 
     * Este método valida se todos os valores em uma caixa específica do tabuleiro são únicos. 
     * Cada caixa do tabuleiro é composta por um bloco 3x3 de células, e o índice da caixa 
     * é fornecido pelo parâmetro {@code box}. O método utiliza um conjunto (`Set`) para garantir 
     * que não existam valores duplicados dentro da caixa. Caso o valor na célula seja diferente de 
     * zero e já exista no conjunto, o método imprime uma mensagem de erro indicando a caixa com a duplicata 
     * e retorna {@code false}. Se a caixa for válida (sem duplicatas), o método retorna {@code true}.
     *
     * A caixa é identificada por um número de 0 a 8, onde as caixas são organizadas em uma grade 
     * 3x3 dentro do tabuleiro 9x9.
     *
     * @param board O objeto {@code Board} que representa o tabuleiro a ser validado.
     * @param box O índice da caixa a ser verificada. Deve ser um valor entre 0 e 8 (inclusive).
     * @return {@code true} se a caixa for válida (sem valores duplicados), {@code false} caso contrário.
     */
    private static boolean isValidBox(Board board, int box) {
        Set<Integer> set = new HashSet<>();
        int rowStart = (box / 3) * 3;
        int colStart = (box % 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                int value = board.getValue(rowStart + i, colStart + j);
                if (value !=0 && !set.add(value)) {
                    System.out.println("Erro no quadrado " + (box + 1));
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Retorna os valores possíveis para uma célula específica no tabuleiro, 
     * considerando as regras de um jogo como o Sudoku.
     * 
     * Este método determina quais números (de 1 a 9) podem ser inseridos em uma célula do tabuleiro,
     * levando em consideração as restrições de linha, coluna e caixa 3x3. Ele começa com todos os números 
     * de 1 a 9 disponíveis e, em seguida, remove os números que já estão presentes na mesma linha, coluna 
     * e caixa (subgrade 3x3) da célula indicada pelos parâmetros {@code row} e {@code col}.
     * O conjunto resultante de números representa os valores válidos para a célula específica.
     * 
     * @param board O objeto {@code Board} que representa o tabuleiro no qual a célula será validada.
     * @param row O índice da linha da célula para a qual os valores possíveis estão sendo calculados. 
     *            Deve ser um valor entre 0 e 8 (inclusive).
     * @param col O índice da coluna da célula para a qual os valores possíveis estão sendo calculados. 
     *            Deve ser um valor entre 0 e 8 (inclusive).
     * @return Um conjunto {@code Set<Integer>} contendo os números válidos que podem ser colocados na célula 
     *         especificada. Se não houver valores possíveis, o conjunto estará vazio.
     */
    public static Set<Integer> getPossibleValues(Board board, int row, int col) {
        Set<Integer> possibleValues = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            possibleValues.add(i);
        }
        
        for (int i = 0; i < 9; i++){
            possibleValues.remove(board.getValue(row, i));
            possibleValues.remove(board.getValue(i, col));
        }
        
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                possibleValues.remove(board.getValue(rowStart + i, colStart + j));
            }
        }
        
        return possibleValues;
    }
}