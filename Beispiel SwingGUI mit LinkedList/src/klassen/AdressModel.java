package klassen;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AdressModel extends AbstractTableModel {
	private static final long serialVersionUID = 4103677174051900966L;
	private String[] columnNames = { "Pos", "Vorname", "Nachname" };
	public List<Kontakt> data;
	private int slot = 0;

	public AdressModel(List<Kontakt> data) {
		this.data = data;
	}

	public int getSlot() {
		return slot;
	}

	public void incSlot() {
		this.slot++;
	}

	public void decSlot() {
		this.slot++;
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

	// Spaltenüberschriften
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col) {
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}

	// Rückgabe der Werte aus Table, falls Table Editable ist.
	public void setValueAt(Object value, int row, int col) {
		switch (col) {
		case 0:
			this.data.get(row).setPos((Integer) value);
			break;
		case 1:
			this.data.get(row).setVorname((String) value);
			break;
		case 2:
			this.data.get(row).setNachname((String) value);
			break;
		default:
			break;
		}
	}

	// Liefert das, was in einer Zelle angezeigt werden soll.
	@Override
	public Object getValueAt(int row, int col) {
		Kontakt kont = null;
		int i = 0;
		for (Kontakt c : data) {
			if (i == row) {
				kont = c;
				break;
			}
			i++;
		}
		if (kont == null)
			return "";
		switch (col) {
		case 0: {
			return kont.getPos();
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

}
