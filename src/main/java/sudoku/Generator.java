package sudoku;
import java.util.Random;
/**
 * Gera valores aleatórios para preencher um tabuleiro de Sudoku.
 * 
 * Esta função insere um número especificado de valores aleatórios em posições 
 * vazias (células com valor 0) de um tabuleiro de Sudoku. Os valores são gerados 
 * aleatoriamente entre 1 e 9, e cada tentativa garante que uma célula vazia seja preenchida.
 * 
 * @param board o tabuleiro de Sudoku a ser preenchido.
 *              Deve ser uma instância da classe {@code Board}, que permite definir e obter valores das células.
 * @param num o número de valores aleatórios a serem gerados e inseridos no tabuleiro.
 * 
 * @throws NullPointerException se o parâmetro {@code board} for {@code null}.
 * @throws IllegalArgumentException se o número de valores a serem inseridos {@code num} for negativo.
 */
public class Generator {
    public static void generateRandomBoard(Board board, int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            int value = rand.nextInt(9) + 1;
            if (board.getValue(row, col) == 0) {
                board.setValue(row, col, value);
            } else {
                i--;
            }
        }
    }
}
