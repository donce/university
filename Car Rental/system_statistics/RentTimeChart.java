package system_statistics;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class RentTimeChart extends ChartPanel {

	public RentTimeChart() {
		super(null);
//		setData(new Set<Map.Entry<Integer>>());
		//TODO: Add first update?
	}

	public void setData(Set<Map.Entry<Integer, Integer>> set) {
		PieDataset dataset = new DefaultPieDataset();
		if (set != null) {
			Iterator<Map.Entry<Integer, Integer>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Integer> entry = it.next();
				((DefaultPieDataset)dataset).setValue(entry.getKey() + " " + (entry.getKey() > 1 ? "days" : "day"), entry.getValue());
			}
		}
		JFreeChart chart = ChartFactory.createPieChart3D("Rent duration", dataset, false, true, false);
		chart.getPlot().setForegroundAlpha((float)0.8);
		this.setChart(chart);
	}
}
