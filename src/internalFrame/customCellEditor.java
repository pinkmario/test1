package internalFrame;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

import model.ShangPinXX;
import first.shujuku.ShuJuKu;
public class customCellEditor extends JComboBox implements TableCellEditor {
	private CellEditorListener list;
	private String gysName;
	private ChangeEvent ce = new ChangeEvent(this);
	public customCellEditor() {
		super();
	}
	public Object getCellEditorValue() {
		return getSelectedItem();
	}
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		ResultSet set = ShuJuKu.query("select * from ShangPingXingXi where gysName='"
				+gysName+"'");
		DefaultComboBoxModel dfcbm = (DefaultComboBoxModel) getModel();
		dfcbm.removeAllElements();
		dfcbm.addElement(new ShangPinXX());
		try {
			while (set.next()) {
				ShangPinXX spinfo=new ShangPinXX();
				spinfo.setId(set.getString("id").trim());
				spinfo.setSpname(set.getString("spname").trim());
				spinfo.setPp(set.getString("pinpai").trim());
				spinfo.setGysdw(set.getString("Gysdw").trim());
				spinfo.setScjy(set.getString("scqy").trim());
				spinfo.setGg(set.getString("gg").trim());
				spinfo.setBz(set.getString("bz").trim());
				spinfo.setPh(set.getString("zhibao").trim());
				spinfo.setScjk(set.getString("Scjk").trim());
				spinfo.setZhuyi(set.getString("zhuyi").trim());
				spinfo.setGysname(set.getString("gysname").trim());
				dfcbm.addElement(spinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	public void addCellEditorListener(CellEditorListener arg0) {
		list = arg0;
	}
	public void cancelCellEditing() {
		list.editingCanceled(ce);
	}
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}
	public void removeCellEditorListener(CellEditorListener arg0) {
	}
	public boolean shouldSelectCell(EventObject arg0) {
		return true;
	}
	public boolean stopCellEditing() {
		list.editingStopped(ce);
		return true;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
}
