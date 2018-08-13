package klassen;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JXTableHeader extends JTableHeader
{
	private static final long serialVersionUID = -7818137370826952179L;

	public JXTableHeader( TableColumnModel cm )
    {
        super( cm );
        
    }

    @Override
    public Dimension getPreferredSize()
    {
        final Dimension size = super.getPreferredSize();
        final LayoutManager layout = getLayout();
        if ( layout != null )
        {
            size.height += layout.preferredLayoutSize( this ).height;
        }
        return size;
    }

    @Override
    public void columnMoved( TableColumnModelEvent e )
    {
        super.columnMoved( e );
        if ( getDraggedColumn() != null )
        {
            revalidate();
            repaint();
        }
    }

    @Override
    public void setDraggedColumn( TableColumn column )
    {
        super.setDraggedColumn( column );
        if ( column == null )
        {
            revalidate();
            repaint();
        }
    }
}
