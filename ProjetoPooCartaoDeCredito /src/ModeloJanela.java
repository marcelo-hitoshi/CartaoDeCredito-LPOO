import javax.swing.*;
import java.awt.*;

public abstract class ModeloJanela extends JFrame {

    public ModeloJanela(String titulo, int largura, int altura) {
        setTitle(titulo);
        setSize(largura, altura);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Apenas fecha a janela, sem encerrar o aplicativo
        setLayout(new BorderLayout());
    }

    // Método abstrato para ser implementado pelas subclasses
    // Inicializar os componentes da janela
    protected abstract void inicializarComponentes();
}
