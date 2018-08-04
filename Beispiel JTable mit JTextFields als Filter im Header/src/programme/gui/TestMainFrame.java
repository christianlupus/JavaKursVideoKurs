package programme.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import klassen.JXTableHeader;
import swing.TableHeaderSouthLayout;

public class TestMainFrame extends JFrame   {

	private static final long serialVersionUID = 8405615042523967994L;
	private TableRowSorter<TableModel> sorter;
	private JLabel lblFilterId,lblFilterVN,lblFilterNN;
    private JTextField filterId, filterVorname, filterNachname;
	private JPanel pnlLineStart,pnlMiddle;
	private JPanel pnlAdd,pnlFilter;
    
	private JTable table;
	private JScrollPane scrollTable;
	private JTableHeader tableHeader;
	String[] columnNames = { "Id","Vorname", "Nachname" };

	Object[][] data = { { new Integer(1),"Max", "Mustermann" }, { new Integer(2),"Erika", "Mustermann" },
			{ new Integer(3),"Michael", "Jackson" }, { new Integer(4),"Donald", "Duck" },
			{ new Integer(5),"James", "Brown" } };

	public TestMainFrame() {
		this.setTitle("JTable mit FilterComboBoxen im Header");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createWidgets();
		setupInteractions();
		addWidgets();
		
		
	}

	private void createWidgets() {
		
		pnlFilter = new JPanel();
		pnlFilter.setLayout(new BoxLayout(pnlFilter, BoxLayout.PAGE_AXIS));

		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 5, 5));
		
		lblFilterId = new JLabel("(Filter(Id)");
		lblFilterVN = new JLabel("(Filter(Vorname)");
		lblFilterNN = new JLabel("(Filter(Nachname)");
		
		
		filterId= new JTextField(20);
		filterVorname=new JTextField(20);
		filterNachname=new JTextField(20);

		
		table = new JTable(data, columnNames);
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		table.setTableHeader(new JXTableHeader(table.getColumnModel()));
		tableHeader = table.getTableHeader();

		final TableCellRenderer defaultRenderer = tableHeader
				.getDefaultRenderer();
		if (defaultRenderer instanceof JLabel) {
			((JLabel) defaultRenderer).setVerticalAlignment(SwingConstants.TOP);
		}
		tableHeader.setLayout(new TableHeaderSouthLayout());
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			tableHeader.add(createTextField(i), Integer.valueOf(i));
		}
		
		scrollTable = new JScrollPane(table);
				
		pnlLineStart = new JPanel();
		pnlLineStart.setLayout(new BoxLayout(pnlLineStart, BoxLayout.PAGE_AXIS));

		this.pnlMiddle = new JPanel();
		this.pnlMiddle.setLayout(new BoxLayout(this.pnlMiddle, BoxLayout.PAGE_AXIS));
		
	}

	private void addWidgets() {		
		getContentPane().setLayout(new BorderLayout(5, 5));
//		getContentPane().add(BorderLayout.PAGE_START, lblPageStart);
		getContentPane().add(BorderLayout.LINE_START, pnlLineStart);
		getContentPane().add(BorderLayout.CENTER, pnlMiddle);
//		getContentPane().add(BorderLayout.LINE_END, lblLineEnd);
//		getContentPane().add(BorderLayout.PAGE_END, lblPageEnd);

		pnlAdd.add(lblFilterId);
		pnlAdd.add(Box.createVerticalGlue());
		pnlAdd.add(filterId);
		pnlAdd.add(Box.createVerticalGlue());
		pnlAdd.add(lblFilterVN);
		pnlAdd.add(Box.createVerticalGlue());
		pnlAdd.add(filterVorname);
		pnlAdd.add(Box.createVerticalGlue());
		pnlAdd.add(lblFilterNN);
		pnlAdd.add(Box.createVerticalGlue());
		pnlAdd.add(filterNachname);
		pnlAdd.add(Box.createVerticalGlue());
		
		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());
		pnlAdd.setAlignmentX(LEFT_ALIGNMENT);

		pnlLineStart.add(pnlAdd);
		pnlLineStart.add(Box.createVerticalGlue());

		pnlLineStart.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		pnlMiddle.add(scrollTable);
		pnlMiddle.add(Box.createVerticalGlue());
		
		pnlMiddle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		
	}		
	
	private void setupInteractions() {
	    filterId.getDocument().addDocumentListener(new addDocumentAction());
	    filterVorname.getDocument().addDocumentListener(new addDocumentAction());
	    filterNachname.getDocument().addDocumentListener(new addDocumentAction());
	}

		
	private class addDocumentAction implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			newFilter();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			newFilter();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			newFilter();
		}
		
	}

	private void newFilter() {
		RowFilter rf = null;
		try {
			ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
					3);
			filters.add(RowFilter.regexFilter(filterId.getText(), 0));
			filters.add(RowFilter.regexFilter("(?i).*"+filterVorname.getText(), 1));			
			filters.add(RowFilter.regexFilter("(?i).*"+filterNachname.getText(), 2));
			rf = RowFilter.andFilter(filters);

		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	private JTextField createTextField(int columnIndex) {
		final JTextField tf = new JTextField();
		tf.setCursor(Cursor.getDefaultCursor());
		tf.setName(Integer.toString(columnIndex));
		tf.setBackground(Color.YELLOW);
		return tf;
	}
	

	
}