package klassen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import klassen.AdressModel;
import klassen.KontaktPrivat;

public class FMFrame extends JFrame {

	private JLabel lblHeader;
	private JLabel lblPos;
	private JLabel lblKontaktId;
	private JLabel lblVorname;
	private JLabel lblNachname;
	private JLabel lblStaat;
	private JLabel lblAnzKontakte;

	private JTextField fldPos;
	private JTextField fldKontaktId;
	private JTextField fldVorname;
	private JTextField fldNachname;
	private JTextField fldStaat;
	private JTextField fldAnzKontakte;

	private JButton btnNeu;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private JTable table;
	private JScrollPane scrollTable;
	private JProgressBar progBar;
	private JPanel pnlAdd;
	private JPanel pnlLeft;

	private AdressModel<KontaktPrivat> model;

	public FMFrame(AdressModel<KontaktPrivat> model) {
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

				int answer = JOptionPane.showConfirmDialog(FMFrame.this,
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
		btnNeu.addActionListener(new NeuKontaktAction());
		btnAdd.addActionListener(new AddKontaktAction());
		btnEdit.addActionListener(new EditKontaktAction());
		btnDelete.addActionListener(new DeleteKontaktAction());
		fldNachname.addCaretListener(new PflichtfeldListener());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new MySelectionListener(table));
	}

	private void addWidgets() {
		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.PAGE_END, progBar);
		getContentPane().add(BorderLayout.CENTER, scrollTable);
		getContentPane().add(BorderLayout.LINE_START, pnlLeft);

		pnlAdd.add(lblPos);
		pnlAdd.add(fldPos);

		pnlAdd.add(lblKontaktId);
		pnlAdd.add(fldKontaktId);

		pnlAdd.add(lblVorname);
		pnlAdd.add(fldVorname);

		pnlAdd.add(lblNachname);
		pnlAdd.add(fldNachname);

		pnlAdd.add(lblStaat);
		pnlAdd.add(fldStaat);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnNeu);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnAdd);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnEdit);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnDelete);

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
		lblKontaktId = new JLabel("KontaktId");
		lblVorname = new JLabel("Vorname");
		lblNachname = new JLabel("Nachname");
		lblStaat = new JLabel("Staatsangehörigkeit");
		lblAnzKontakte = new JLabel("Anzahl Kontakte");

		fldPos = new JTextField();
		fldPos.setEnabled(false);
		fldKontaktId = new JTextField();
		fldVorname = new JTextField();
		fldNachname = new JTextField();
		fldStaat = new JTextField();
		fldAnzKontakte = new JTextField();
		fldAnzKontakte.setEnabled(false);
		
		btnNeu = new JButton("Neu");
		btnNeu.setEnabled(true);

		btnAdd = new JButton("Hinzufügen");
		btnAdd.setEnabled(false);

		btnEdit = new JButton("Editieren");
		btnEdit.setEnabled(false);

		btnDelete = new JButton("Löschen");
		btnDelete.setEnabled(false);

		pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));

	}

	private class PflichtfeldListener implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent e) {
			JTextField fld = (JTextField) e.getSource();
			String text = fld.getText();
			btnAdd.setEnabled(!text.trim().isEmpty());
		}
	}

	public class MySelectionListener implements ListSelectionListener {

		JTable table;

		public MySelectionListener(JTable table) {
			this.table = table;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				 System.out.println(table.getValueAt(table.getSelectedRow(),1));
				fldPos.setText(String.valueOf(table.getValueAt(
						table.getSelectedRow(), 0)));
				fldKontaktId.setText(String.valueOf(table.getValueAt(
						table.getSelectedRow(), 1)));
				fldVorname.setText((String) table.getValueAt(
						table.getSelectedRow(), 3));
				fldNachname.setText((String) table.getValueAt(
						table.getSelectedRow(), 2));
				fldStaat.setText((String) table.getValueAt(
						table.getSelectedRow(), 4));
			}
		}
	}

	private class NeuKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fldPos.setText(null);
			fldKontaktId.setText(null);
			fldVorname.setText(null);
			fldNachname.setText(null);
			fldStaat.setText(null);

			btnAdd.setEnabled(false);
			btnEdit.setEnabled(false);

		}
	}

	private class DeleteKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Zeile wird gelöscht: "
					+ Integer.parseInt(fldPos.getText()));

			KontaktPrivat kont = null;
			kont = new KontaktPrivat(0);
			model.setValueAt(kont, Integer.parseInt(fldPos.getText()), 0);
			AdressModel<KontaktPrivat> hilf = new AdressModel<KontaktPrivat>(1);
			int neuPos=0;
			for (int i = 1; i<=model.getElementCount(); i++) {
				if (model.getA(i).getKontaktId()>0){			
				++neuPos;
				hilf.put(neuPos, model.get(i));
				}

			}
			model=hilf;
            System.out.println("Länge"+model.getRowCount());
	
			for (int i = 1; i<=model.getRowCount(); i++) {
				System.out.println(model.getA(i).getKontaktId()+"  "+model.getA(i).getNachname());
			}
			
			table.repaint();

		}
	}

	private class EditKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			KontaktPrivat kont = null;
			kont = new KontaktPrivat(Integer.parseInt(fldKontaktId.getText()));
			System.out.println("--->   "
					+ Integer.parseInt(fldKontaktId.getText()));
			kont.setVorname(fldVorname.getText());
			kont.setNachname(fldNachname.getText());
			kont.setStaatsangehoerigkeit(fldStaat.getText());
			model.setValueAt(kont, Integer.parseInt(fldPos.getText()), 0);
		}
	}

	private class AddKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			KontaktPrivat kont = null;
			kont = new KontaktPrivat(Integer.parseInt(fldKontaktId.getText()));
			fldPos.setText(String.valueOf(model.getElementCount()+1));
			kont.setVorname(fldVorname.getText());
			kont.setNachname(fldNachname.getText());
			kont.setStaatsangehoerigkeit(fldStaat.getText());
			//model.add(model.getElementCount()+1, kont);
			model.add(model.getRowCount()+1,kont);
			System.out.println("Aktueller Kontakt: " + (model.getElementCount()));
			fldAnzKontakte.setText(String.valueOf(model.getElementCount()));
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
		}
	}
}
