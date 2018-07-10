package klassen;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AdressModel<T> extends DynGenArray implements TableModel {

	private List<TableModelListener> tableListener = new ArrayList<TableModelListener>();
	public int length;
	

	public void add(int pos, KontaktPrivat obj) {
		this.put(pos, obj);
		for (TableModelListener l : tableListener) {
			l.tableChanged(new TableModelEvent(this)); // this ==> es hat sich irgendawas geändert
		}
	}

	public KontaktPrivat getA(int index) {
		KontaktPrivat kont = new KontaktPrivat(index);
		kont = (KontaktPrivat) this.get(index);
		return kont;
	}

	// Welcher Datentyp. Wichtig für Sortierung der Spalten
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		default:
			return null;
		}
	}

	// Wieviel Spalten Soll die Table haben? 5 Spalten
	@Override
	public int getColumnCount() {
		return 5;
	}

	// Spaltennamen
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Pos";
		case 1:
			return "ID";
		case 2:
			return "Nachname";
		case 3:
			return "Vorname";
		case 4:
			return "Staatsangehörigkeit";
		default:
			return null;

		}
	}

	// Wieviel Zeilen soll die Table haben?
	@Override
	public int getRowCount() {
		return getElementCount();
	}

	// Liefert das, was in einer Zelle angezeigt werden soll.
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		KontaktPrivat kont = new KontaktPrivat(rowIndex);
		kont = this.getA(rowIndex + 1);

		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return kont.getKontaktId();
		case 2:
			return kont.getNachname();
		case 3:
			return kont.getVorname();
		case 4:
			return kont.getStaatsangehoerigkeit();
		default:
			return null;

		}

	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		tableListener.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
		this.edit(rowIndex, arg0);

	}

	// Konstruktor
	public AdressModel(int capacity) {
		super(capacity);
	}

}
