import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface extends ModeloJanela {
    private double limiteCartao;
    private double totalGastos;
    private JComboBox<String> setorComboBox;
    private JTextField produtoField;
    private JTextField valorField;
    private static ArrayList<Compra> compras;
    private JTextField limiteField;
    private JButton confirmarLimiteButton;

    public Interface() {
        super("Controle de Gastos", 480, 350); // Chamando o construtor da JanelaBase
        compras = new ArrayList<>();
        totalGastos = 0.0;

        inicializarComponentes();
        setVisible(true);
    }

    @Override
    protected void inicializarComponentes() {
        JLabel boasVindasLabel = new JLabel("<html>Bem vindo ao programa de controle de gastos para Cartão de Crédito!" +
                "<br>Desenvolvido por: Marcelo Oya e Kenzo Toda<html>");
        JLabel limiteLabel = new JLabel("Digite o limite do cartão em R$: ");
        limiteField = new JTextField(10);
        confirmarLimiteButton = new JButton("Definir Limite");
        setLayout(new FlowLayout());

        add(boasVindasLabel);
        add(limiteLabel);
        add(limiteField);
        add(confirmarLimiteButton);

        confirmarLimiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarLimiteCartao();
            }
        });

        // Imagem da Janela Inicial (logo IFPA)
        ImageIcon logo = new ImageIcon(getClass().getResource("/imagens/ifpaLogo.png"));
        JLabel logoIfpaLabel = new JLabel(logo);
        add(logoIfpaLabel, BorderLayout.CENTER);
    }

    private void iniciarInterface() {
        getContentPane().removeAll();
        // Criar os componentes da interface principal
        setorComboBox = new JComboBox<>(new String[]{
                "Investimento",
                "Alimentação",
                "Transporte",
                "Saúde",
                "Lazer",
                "Educação",
                "Assinaturas",
                "Outros"
        });
        produtoField = new JTextField(15);
        valorField= new JTextField(15);
        JButton botaoAdicionarGasto = new JButton("Adicionar Gasto");
        JButton botaoMostrarGrafico = new JButton("Mostrar Gráfico");
        JButton botaoMostrarFatura = new JButton("Mostrar Fatura");

        // Adicionando os componentes à janela
        add(new JLabel("Setor: "));
        add(setorComboBox);
        add(new JLabel("Produto: "));
        add(produtoField);
        add(new JLabel("Valor: "));
        add(valorField);
        add(botaoAdicionarGasto);
        add(botaoMostrarGrafico);
        add(botaoMostrarFatura);

        // Imagem da Janela Principal
        ImageIcon imagem2 = new ImageIcon(getClass().getResource("/imagens/controleOrcamento.png"));

        // Redimensionando a imagem
        Image redimensionandoImg2 = imagem2.getImage().getScaledInstance(250,200, Image.SCALE_SMOOTH);
        ImageIcon img2Redimensionada = new ImageIcon(redimensionandoImg2);

        // Chamando a imagem redimensionada
        JLabel img2Final = new JLabel(img2Redimensionada);
        add(img2Final, BorderLayout.CENTER);

        botaoAdicionarGasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGasto();
            }
        });

        botaoMostrarGrafico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGrafico();
            }
        });

        botaoMostrarFatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFatura();
            }
        });

        // Atualizar a tela para exibir novos componentes
        revalidate();
        repaint();
    }

    // Método Limite do cartão
    private void solicitarLimiteCartao() {
        try {
            limiteCartao = Double.parseDouble(limiteField.getText().trim());

            if (limiteCartao <= 0) {
                JOptionPane.showMessageDialog(this, "O limite deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Iniciar a janela principal se o limite informado for válido
            iniciarInterface();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para adicionar uma compra
    private void addGasto() {
        try {
            String setor = (String) setorComboBox.getSelectedItem();
            String produto = produtoField.getText().trim();
            String valorString = valorField.getText().trim();

            if (setor.isEmpty() || produto.isEmpty() || valorString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos");
                return;
            }

            double valor = Double.parseDouble(valorString);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero");
                return;
            }

            if (totalGastos + valor > limiteCartao) {
                JOptionPane.showMessageDialog(this, "O valor do gasto excede o limite do cartão. Total atual: " + totalGastos);
                return;
            }

            Compra compra = new Compra(setor, produto, valor);
            compras.add(compra);
            totalGastos += valor;

            setorComboBox.setSelectedIndex(0);
            produtoField.setText("");
            valorField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido, insira um número válido");
        }
    }

    // Método para mostrar gráfico
    private void mostrarGrafico() {
        new GraficoPizza(compras).setVisible(true);
    }

    // Método para mostrar fatura
    private void mostrarFatura() {
        StringBuilder resumo = new StringBuilder("Resumo da fatura: \n\n");
        for (Compra compra : compras) {
            resumo.append(compra.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, resumo.toString(), "Resumo da Fatura", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getter
    public static ArrayList<Compra> getCompras() {
        return compras;
    }
}
