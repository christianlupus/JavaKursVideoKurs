package klassen;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class KontaktModel extends AbstractTableModel {

	private static final long serialVersionUID = -5028828935981525458L;
	
	private ArrayList<KontaktPrivat> list = new ArrayList<>();

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
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		KontaktPrivat k = list.get(arg0);
		switch(arg1)
		{
		case 0:
			return arg0;
		case 1:
			return k.getKontaktId();
		case 2:
			return k.getNachname();
		case 3:
			return k.getVorname();
		case 4:
			return k.getStaatsangehoerigkeit();
		}
		return null;
	}
	
	public void edit(int index, KontaktPrivat k)
	{
		if(index < list.size())
			list.set(index, k);
		else
			System.out.println("There went something wrong.");
		
		fireTableRowsUpdated(index, index);
	}
	
	public int add(KontaktPrivat k)
	{
		list.add(k);
		int ret = list.size() - 1;
		fireTableRowsInserted(ret, ret);
		return ret;
	}
	
	public void insertBefore(int index, KontaktPrivat k)
	{
		if(index >= list.size())
		{
			add(k);
			return;
		}
		
		list.add(index, k);
		fireTableRowsInserted(index, index);
	}
	
	public void delete(int index)
	{
		if(index >= list.size())
			return;
		
		list.remove(index);
		fireTableRowsDeleted(index, index);
	}

	public int getSize() 
	{
		return list.size();
	}
	
	public KontaktPrivat get(int index)
	{
		return list.get(index);
	}
}
