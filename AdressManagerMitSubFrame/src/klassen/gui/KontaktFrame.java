package klassen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import klassen.AdressModel;
import klassen.Kontakt;

public class KontaktFrame extends JFrame implements GUINotification {

	private static final long serialVersionUID = -3415056364125031900L;
	private JLabel lblHeader;
	private JLabel lblPos;
	private JLabel lblVorname;
	private JLabel lblNachname;
	private JLabel lblAnzKontakte;

	private JTextField fldPos;
	private JTextField fldVorname;
	JTextField fldNachname;
	private JTextField fldAnzKontakte;

	private JButton btnNeu;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSubFrame;
	
	private JTable table;
	private JScrollPane scrollTable;
	private JProgressBar progBar;
	private JPanel pnlAdd;
	private JPanel pnlLeft;

	private AdressModel model;

	
	public KontaktFrame(AdressModel model) {
		this.model = model;
		this.setTitle("Adressen Manager");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		createWidgets();
		setupInteractions();
		addWidgets();
		pack();
		setLocation(450, 200);

		WindowListener winListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int answer = JOptionPane.showConfirmDialog(KontaktFrame.this,
						"Wollen Sie das Programm wirklich beenden?",
						"Wirklich beenden?", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		};

		addWindowListener(winListener);

	}

	private void setupInteractions() {
		btnNeu.addActionListener(new AddKontaktAction());
		btnEdit.addActionListener(new EditKontaktAction());
		btnDelete.addActionListener(new DeleteKontaktAction());
		btnSubFrame.addActionListener(new SubFrameAction());
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new MySelectionListener(table));
		table.addMouseListener(new DoubleClickListener());
	}

	private void addWidgets() {
		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.PAGE_END, progBar);
		getContentPane().add(BorderLayout.CENTER, scrollTable);
		getContentPane().add(BorderLayout.LINE_START, pnlLeft);

		pnlAdd.add(lblPos);
		pnlAdd.add(fldPos);

		pnlAdd.add(lblVorname);
		pnlAdd.add(fldVorname);

		pnlAdd.add(lblNachname);
		pnlAdd.add(fldNachname);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnNeu);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnEdit);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnDelete);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnSubFrame);
		
		
		pnlAdd.add(lblAnzKontakte);
		pnlAdd.add(fldAnzKontakte);

		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());
		pnlAdd.setAlignmentX(LEFT_ALIGNMENT);

		pnlLeft.add(pnlAdd);
		pnlLeft.add(Box.createVerticalGlue());

		pnlLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	}

	private void createWidgets() {
		lblHeader = new JLabel("Adressen Manager");
		lblHeader.setFont(lblHeader.getFont().deriveFont(
				Font.BOLD + Font.ITALIC, 30));
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setOpaque(true);
		lblHeader.setBackground(Color.BLACK);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);

		progBar = new JProgressBar(0, 100);
		progBar.setPreferredSize(new Dimension(0, 30));

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);

		scrollTable = new JScrollPane(table);

		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 5, 5));

		lblPos = new JLabel("Pos");
		lblPos.setEnabled(false);

		lblVorname = new JLabel("Vorname");
		lblNachname = new JLabel("Nachname");

		lblAnzKontakte = new JLabel("Letzte KontaktId");

		fldPos = new JTextField();
		fldPos.setEnabled(false);

		fldVorname = new JTextField();
		fldNachname = new JTextField();

		fldAnzKontakte = new JTextField();
		fldAnzKontakte.setEnabled(false);

		btnNeu = new JButton("Neu");
		btnNeu.setEnabled(true);

		btnEdit = new JButton("Editieren");
		btnEdit.setEnabled(false);

		btnDelete = new JButton("Loeschen");
		btnDelete.setEnabled(false);

		btnSubFrame = new JButton("SubFrame");
		btnSubFrame.setEnabled(true);
		
		
		pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));

	}

	public class MySelectionListener implements ListSelectionListener {

		JTable table;

		public MySelectionListener(JTable table) {
			this.table = table;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				if(table.getSelectedRow() != -1)
				{
					fldPos.setText(table.getValueAt(table.getSelectedRow(), 0)
							.toString());
					fldVorname.setText(table.getValueAt(table.getSelectedRow(), 1)
							.toString());
					fldNachname.setText(table.getValueAt(table.getSelectedRow(), 2)
							.toString());
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
				}
				else
				{
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
				}
			}
		}
	}

	public class SubFrameAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			assert(row != -1);
			Kontakt k = model.getRow(row);
			new Detail(KontaktFrame.this, k);
		}
	}
	
	private class DoubleClickListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() != 2)
				return;
			
			int row = table.getSelectedRow();
			if(row == -1)
				return;
			
			Kontakt k = model.getRow(row);
			new Detail(KontaktFrame.this, k);
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
	private class AddKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.add(new Kontakt(fldVorname.getText(),
					fldNachname.getText()));

			updateCount();
			updateGUI();
		}

	}

	private class EditKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int row = table.getSelectedRow();
			
			// Da muss ein Wert >-1 stehen, sonst ging was richtig schief.
			assert(row != -1);
			
			/*
			 * Der Teil geht vielleicht ein wenig eleganter, wenn man die Kapselung
			 * der Objekte noch mit nutzt:
			 * Du koenntest dir nur den Kontakt zurueckgeben lassen. Dann kannst du darin
			 * Aenderungen machen, ohne, dass du die Struktur der Tabelle (welche Spalte steht wo?)
			 * im Hinterkopf haben musst. Das wird im Modell unter der Hand geloest.
			 * 
			 * Ich habe das mal soweit vogesehen.
			 */
			/*model.setValueAt(fldVorname.getText(), row - 1, 1);
			model.setValueAt(fldNachname.getText(), row - 1, 2);*/
			
			Kontakt k = model.getRow(row);
			k.setVorname(fldVorname.getText());
			k.setNachname(fldNachname.getText());
			
			// Man koennte auch mit updateRow arbeiten...
			model.fireTableDataChanged();
			updateGUI();
		}
	}

	private class DeleteKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			// Da muss ein Wert >-1 stehen, sonst ging was richtig schief.
			assert(row != -1);
			
			model.remove(row);
			updateCount();
			updateGUI();
		}
	}
	
	private void updateCount()
	{
		fldAnzKontakte.setText(String.valueOf(model.getRowCount()));
	}

	@Override
	public void updateGUI() {
		updateCount();
		
		int row = table.getSelectedRow();
		if(row == -1)
		{
			fldPos.setText("");
			fldVorname.setText("");
			fldNachname.setText("");
			btnEdit.setEnabled(false);
			btnDelete.setEnabled(false);
		}
		else
		{
			Kontakt k = model.getRow(row);
			
			fldPos.setText(String.valueOf(row));
			fldVorname.setText(k.getVorname());
			fldNachname.setText(k.getNachname());
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
			
			model.fireTableRowsUpdated(row, row);
		}
	}
}
