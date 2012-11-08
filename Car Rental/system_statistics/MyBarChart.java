package system_statistics;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

//TODO: rename chart, title
public class MyBarChart extends ChartPanel {
	
	public MyBarChart() {
		super(null);
		setData(null);
		//TODO: Add first update?
	}
	
	public void setData(Set<Map.Entry<String, Integer>> set) {
		CategoryDataset dataset = new DefaultCategoryDataset();
		if (set != null) {
			Iterator<Map.Entry<String, Integer>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<String, Integer> entry = it.next();
				((DefaultCategoryDataset)dataset).addValue(entry.getValue(), "First", entry.getKey());
			}
		}
		JFreeChart chart = ChartFactory.createBarChart("Cars chart", "Car", "Amount", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		this.setChart(chart);
	}
}
