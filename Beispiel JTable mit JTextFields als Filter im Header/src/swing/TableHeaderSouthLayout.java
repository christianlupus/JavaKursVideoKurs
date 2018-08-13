package swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
/* ****
 * Interface: LayoutManager, LayoutManager2
 * ****
 * Definiert eine Schnittstelle für Klassen, die wissen, wie Container auf der 
 * Basis von Layouts erstellt werden auf einem Layout-Constraints-Objekt.
 * Diese Schnittstelle erweitert die LayoutManager-Oberfläche, um mit Layouts
 * umzugehen explizit in Bezug auf Constraint-Objekte, die angeben, wie und wo 
 * Komponenten dem Layout hinzugefügt werden sollten.
 * Diese minimale Erweiterung für den LayoutManager ist für Tool-Anbieter gedacht, 
 * die dies zur Erstellung von Constraint-basierten Layouts wünschen.
 * Es bietet noch keine vollständige, allgemeine Unterstützung für benutzerdefinierte
 * Constraint-basierte Layout-Manager.
 * 
 *  Mehtoden:
 *  	addLayoutComponent(Component comp, Object constraints)
 *      addLayoutComponent(String name, Component comp)
 *  	getLayoutAlignmentX(Container target)
 *  	getLayoutAlignmentY(Container target)
 *  	invalidateLayout(Container target)
 *  	maximumLayoutSize(Container target)
 *      layoutContainer(Container parent)
 *      minimumLayoutSize(Container parent)
 *      preferredLayoutSize(Container parent)
 *      removeLayoutComponent(Component comp)
 * 
 */
public final class TableHeaderSouthLayout implements LayoutManager2 {

	private Insets margin = new Insets(1, 1, 1, 1);
	private final Map<Component, Integer> components = new HashMap<Component, Integer>();

	public Insets getMargin() {
		return margin;
	}

	public void setMargin(Insets m) {
		if (m == null) {
			throw new IllegalArgumentException( //
					"margin not allowed null"); //$NON-NLS-1$
		}
		this.margin = new Insets(m.top, m.left, m.bottom, m.right);
	}

	@Override
	public void layoutContainer(Container parent) {
		final JTableHeader th = ((JTableHeader) parent);
		final JTable table = th.getTable();
		final int componentCount = th.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			final Component comp = th.getComponent(i);
			final Integer columnIndexObj = components.get(comp);
			final int colIndex;
			final int viewIndex;
			if (table == null
					|| columnIndexObj == null
					|| (colIndex = columnIndexObj.intValue()) < 0
					|| (viewIndex = table.convertColumnIndexToView(colIndex)) < 0
					|| viewIndex >= table.getColumnCount()) {
				comp.setBounds(0, 0, 0, 0);
			} else {
				final Rectangle rect = th.getHeaderRect(viewIndex);
				final TableColumn draggedColumn = th.getDraggedColumn();
				if (draggedColumn != null
						&& draggedColumn.getModelIndex() == colIndex) {
					rect.x += th.getDraggedDistance();
					th.setComponentZOrder(comp, 0);
				}
				rect.x += margin.left;
				rect.y += margin.top;
				rect.width -= margin.left + margin.right;
				rect.height -= margin.top + margin.bottom;
				final Dimension size = comp.getPreferredSize();
				if (rect.height > size.height) {
					rect.y += rect.height - size.height;
					rect.height = size.height;
				}
				comp.setBounds(rect);
			}
		}
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		final JTableHeader th = ((JTableHeader) parent);
		final JTable table = th.getTable();
		final int componentCount = th.getComponentCount();
		int h = 0;
		for (int i = 0; i < componentCount; i++) {
			final Component comp = th.getComponent(i);
			final Integer columnIndexObj = components.get(comp);
			final int colIndex;
			final int viewIndex;
			if (table != null
					&& columnIndexObj != null
					&& (colIndex = columnIndexObj.intValue()) >= 0
					&& (viewIndex = table.convertColumnIndexToView(colIndex)) >= 0
					&& viewIndex < table.getColumnCount()) {
				h = Math.max(h, comp.getPreferredSize().height);
			}
		}
		return new Dimension(0, margin.top + margin.bottom + h);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension();
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension();
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		components.remove(comp);
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if (!(constraints instanceof Integer)) {
			throw new IllegalArgumentException( //
					"Wrong type: Integer expected"); //$NON-NLS-1$
		}
		components.put(comp, (Integer) constraints);
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
	}

}
