import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraficoPizza extends ModeloJanela {
    public GraficoPizza(ArrayList<Compra> compras) {
        super("Resumo da Fatura", 600, 400); // Chamando o construtor da JanelaBase
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        // Cria um dataset do tipo DefaultPieDataset, que é uma estrutura de dados utilizada para armazenar os dados do gráfico de pizza
        // Instância de DefaultPieDataset, está criando um objeto que pode armazenar dados para um gráfico de pizza. Inicialmente, o dataset está vazio
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Adicionar dados ao dataset usando for-each (Tipo variável : arrayOuColeção)
        // Itera sobre a lista de compras para adicionar os dados ao dataset, para cada objeto Compra, obtemos o setor e o valor
        for (Compra compra : Interface.getCompras()) {
            String setor = compra.getSetor();
            double valor = compra.getValor();


            // Verifica se o setor já existe no dataset, se sim, somamos o novo valor ao existente. Se não, adicionamos o novo setor com o valor inicial
            // Se não existir, o index  retorna -1
            if (dataset.getIndex(setor) >= 0) {
                double atual = dataset.getValue(setor).doubleValue();
                dataset.setValue(setor, atual + valor);
            } else {
                dataset.setValue(setor, valor);
            }
        }

        // Instância do gráfico
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribuição de Gastos",
                dataset,
                true,
                true,
                false
        );

        // Obtém o objeto PiePlot do gráfico, que é responsável pela renderização do gráfico de pizza
        PiePlot plot = (PiePlot) chart.getPlot();

        // É o que gera os rótulos do gráfico
        // Configura rótulo das fatias para incluir porcentagem
        // {0} é o nome do Setor -- {1} é o valor da fatia -- {2} é a porcentagem
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                new java.text.DecimalFormat("0.00"),
                new java.text.DecimalFormat("0.00%")
        );
        plot.setLabelGenerator(labelGenerator);

        // Cria um painel que contém o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
