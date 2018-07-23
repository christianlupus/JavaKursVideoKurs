package klassen;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klassen.gui.GUINotification;

public class AdressModel extends AbstractTableModel {
	private static final long serialVersionUID = 4103677174051900966L;
	private String[] columnNames = { "Pos", "Vorname", "Nachname" };
	private List<Kontakt> data;
	private GUINotification notifier = null;

	public AdressModel(List<Kontakt> data) {
		this.data = data;
	}
	
	public void setNotifier(GUINotification notifier)
	{
		this.notifier = notifier;
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		default:
			return null;
		}

	}

	// Spaltenanzahl
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	// Spaltenueberschriften
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col) {
		if (col < 1) {
			return false;
		} else {
			return false /*true*/;
		}
	}

	public void add(Kontakt k)
	{
		int num;
		synchronized (data) {
			data.add(k);
			num = data.size();
		}
		fireTableRowsInserted(num-1, num-1);	
	}
	
	public Kontakt getRow(int row)
	{
		return data.get(row);
	}
	
	public void updateRow(int row, Kontakt k)
	{
		data.set(row, k);
		fireTableRowsUpdated(row, row);
	}
	
	public void remove(int row)
	{
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}

	// Liefert das, was in einer Zelle angezeigt werden soll.
	@Override
	public Object getValueAt(int row, int col) {
		Kontakt kont = data.get(row);
		
		assert(kont != null);
		/*if (kont == null)
			return "";*/
		
		switch (col) {
		case 0: {
			return row;
		}
		case 1: {
			return kont.getVorname();
		}
		case 2:
			return kont.getNachname();
		default:
			return null;

		}
	}
	
	@Override
	public void setValueAt(Object val, int row, int col) {
		super.setValueAt(val, row, col);
		
		switch(col)
		{
		case 1:
			data.get(row).setVorname((String) val);
			break;
		case 2:
			data.get(row).setNachname((String) val);
			break;
		default:
			// Darf nicht passieren
			assert(false);
		}
		
		if(notifier != null)
			notifier.updateGUI();
	}

}
