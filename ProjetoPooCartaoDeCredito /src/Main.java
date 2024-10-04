import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // new Interface();
        SwingUtilities.invokeLater(() -> new Interface());
        // Cria a interface na EDT (Boa prática em qualquer aplicação Swing que envolva manipulação de GUI)

    }
}
