package sudoku;

/**
 *
 * @author caiofdev
 */
public class Main {
/**
 * Método principal da aplicação.
 * 
 * Este método é o ponto de entrada da aplicação. Ele cria uma instância da classe {@code Menu} 
 * e exibe a tela de boas-vindas utilizando o método {@code displayWelcomeScreen()}.
 * 
 * O método principal organiza a execução inicial da aplicação, preparando o ambiente 
 * para interações posteriores com o usuário.
 * 
 * @param args argumentos de linha de comando (não utilizados neste caso).
 */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayWelcomeScreen();
    }
}