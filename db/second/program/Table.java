import java.util.ArrayList;
import java.util.List;


public class Table {
	private String[] titles;
	List<String[]> data;
	
	public Table(String[] titles) {
		this.titles = titles;
		data = new ArrayList<String[]>();
	}
	
	public void addRow(String[] row) {
		if (row.length != titles.length)
			throw new IllegalArgumentException("Column count does not match!");
		for (int i = 0; i < row.length; ++i)
			if (row[i] == null)
				row[i] = "";
		this.data.add(row);
	}
	
	private void printRepeat(char c, int t) {
		while (t-- > 0)
			System.out.print(c);
	}
	
	private void printString(String s, int length) {
		System.out.print(' ' + s);
		printRepeat(' ', length - s.length()+1);
	}
	
	public void print() {
		int[] len = new int[titles.length];
		for (int i = 0; i < len.length; ++i) {
			len[i] = titles[i].length();
			for (int j = 0; j < data.size(); ++j)
				len[i] = Math.max(len[i], data.get(j)[i].length());
		}
		
		for (int i = 0; i < titles.length; ++i) {
			if (i > 0)
				System.out.print("|");
			printString(titles[i], len[i]);
		}
		System.out.println();
		
		for (int i = 0; i < titles.length; ++i) {
			if (i > 0)
				System.out.print("+");
			printRepeat('-', len[i]+2);
		}
		System.out.println();
		
		for (int i = 0; i < data.size(); ++i) {
			for (int j = 0; j < titles.length; ++j) {
				if (j > 0)
					System.out.print("|");
				printString(data.get(i)[j], len[j]);
			}
			System.out.println();
		}
	}
}
