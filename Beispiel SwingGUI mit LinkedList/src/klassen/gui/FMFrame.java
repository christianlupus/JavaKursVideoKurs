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

import klassen.AdressModel;
import klassen.Kontakt;

public class FMFrame extends JFrame {

	private static final long serialVersionUID = -3415056364125031900L;
	private JLabel lblHeader;
	private JLabel lblPos;
	private JLabel lblVorname;
	private JLabel lblNachname;
	private JLabel lblAnzKontakte;

	private JTextField fldPos;
	private JTextField fldVorname;
	private JTextField fldNachname;
	private JTextField fldAnzKontakte;

	private JButton btnNeu;
	private JButton btnEdit;
	private JButton btnDelete;

	private JTable table;
	private JScrollPane scrollTable;
	private JProgressBar progBar;
	private JPanel pnlAdd;
	private JPanel pnlLeft;

	private AdressModel model;

	public FMFrame(AdressModel model) {
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
		btnNeu.addActionListener(new AddKontaktAction());
		btnEdit.addActionListener(new EditKontaktAction());
		btnDelete.addActionListener(new DeleteKontaktAction());
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
		btnEdit.setEnabled(true);

		btnDelete = new JButton("Löschen");
		btnDelete.setEnabled(true);

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
				fldPos.setText(table.getValueAt(table.getSelectedRow(), 0)
						.toString());
				fldVorname.setText(table.getValueAt(table.getSelectedRow(), 1)
						.toString());
				fldNachname.setText(table.getValueAt(table.getSelectedRow(), 2)
						.toString());
				model.fireTableRowsUpdated(table.getSelectedRow(), 0);
				model.fireTableRowsUpdated(table.getSelectedRow(), 1);
				model.fireTableRowsUpdated(table.getSelectedRow(), 2);

			}
		}
	}

	private class AddKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.incSlot();
			model.data.add(new Kontakt(model.getSlot(), fldVorname.getText(),
					fldNachname.getText()));
			fldPos.setText(String.valueOf(model.getSlot()));
			fldAnzKontakte.setText(String.valueOf(model.getSlot()));

			model.fireTableDataChanged();
		}

	}

	private class EditKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = Integer.parseInt(fldPos.getText());
			model.setValueAt(Integer.parseInt(fldPos.getText()), row - 1, 0);
			model.setValueAt(fldVorname.getText(), row - 1, 1);
			model.setValueAt(fldNachname.getText(), row - 1, 2);
			model.fireTableDataChanged();
		}
	}

	private class DeleteKontaktAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = Integer.parseInt(fldPos.getText());
			model.data.remove(row - 1);
			fldAnzKontakte.setText(String.valueOf(model.getSlot()));
			model.fireTableDataChanged();
		}
	}
}
