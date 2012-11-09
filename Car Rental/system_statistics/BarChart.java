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

public class BarChart extends ChartPanel {
	private RentalSystem2 system;
	
	public BarChart(RentalSystem2 system) {
		super(null);
		this.system = system;
		update();
	}
	
	public void update() {
		Set<Map.Entry<String, Integer>> set = system.getCarStatistics();
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
