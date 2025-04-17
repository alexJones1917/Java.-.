import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class Chart  extends JFrame {
    public Chart(String WindowTitle , List<String> comand1, List<Double> avgHeight) {
        super(WindowTitle);
        //Построение графика
        JFreeChart Chart = ChartFactory.createBarChart(
                "График",
                "Команда",
                "Средний возраст",
                createDataset(comand1, avgHeight),
                PlotOrientation.VERTICAL, // Отображение графика по вертикали
                false, true, false);



        ChartPanel chartPanel = new ChartPanel(Chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setContentPane(chartPanel);
    }

    //Данные графика
    private CategoryDataset createDataset(List<String> comand, List<Double> avg) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(avg.get(0),"средний возраст", comand.get(0));
        dataset.addValue(avg.get(1),"средний возраст", comand.get(1));
        dataset.addValue(avg.get(2),"средний возраст", comand.get(2));
        dataset.addValue(avg.get(3),"средний возраст", comand.get(3));
        dataset.addValue(avg.get(4),"средний возраст", comand.get(4));
        dataset.addValue(avg.get(5),"средний возраст", comand.get(5));
        dataset.addValue(avg.get(6),"средний возраст", comand.get(6));
        dataset.addValue(avg.get(7),"средний возраст", comand.get(7));
        dataset.addValue(avg.get(8),"средний возраст", comand.get(8));
        dataset.addValue(avg.get(9),"средний возраст", comand.get(9));
        return dataset;
    }

}
