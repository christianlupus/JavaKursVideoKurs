package programme.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import klassen.KontaktPrivat;

public class GuiDemoTableMitHashSet extends JPanel {

	private static final long serialVersionUID = 1L;

	public GuiDemoTableMitHashSet() {
		super(new GridLayout(1, 0));

		HashSet<KontaktPrivat> data = new HashSet<KontaktPrivat>();
		JTable table = new JTable(new MyTableModel(data));
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 470));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 675419199439436088L;
		private String[] columnNames = { "ID", "Vorname", "Nachname" };
		HashSet<KontaktPrivat> data;

		public MyTableModel(HashSet<KontaktPrivat> data) {
			data.add(new KontaktPrivat(101, "Max", "Mustermann"));
			data.add(new KontaktPrivat(102, "Erika", "Mustermann"));
			data.add(new KontaktPrivat(103, "Daniel", "Düsentrieb"));
			data.add(new KontaktPrivat(104, "Micky", "Maus"));
			data.add(new KontaktPrivat(105, "Mini", "Maus"));
			this.data = data;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public boolean isCellEditable(int row, int col) {
			if (col < 0) {
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {

			System.out.println("(setValueAt) Object: " + value + "  row: "
					+ row + " col: " + col);
			fireTableCellUpdated(row, col);       //??
			fireTableDataChanged();               //??
			fireTableRowsInserted(row, col);      //??
			fireTableRowsUpdated(row, col);       //??
		}

		@Override
		public Object getValueAt(int row, int col) {
			KontaktPrivat kont = null;
			int i = 0;
			for (KontaktPrivat c : data) {
				if (i == row) {
					kont = c;
					break;
				}
				i++;
			}
			if (kont == null)
				return "";
			switch (col) {
			case 0:
				return kont.getKontaktId();
			case 1:
				return kont.getVorname();

			case 2:
				return kont.getNachname();
			default:
				return null;

			}

		}
	}

	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("TableMitHashSet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiDemoTableMitHashSet newContentPane = new GuiDemoTableMitHashSet();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
