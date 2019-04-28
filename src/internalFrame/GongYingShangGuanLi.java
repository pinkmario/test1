package internalFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import second.gongYingShangGuanLi.GongYingShangTianJiaPanel;
import second.gongYingShangGuanLi.GongYingShangXiuGaiPanel;
public class GongYingShangGuanLi extends JInternalFrame {
	public GongYingShangGuanLi() {
		setIconifiable(true);
		setClosable(true);
		setTitle("供应商管理");
		JTabbedPane tabPane = new JTabbedPane();
		final GongYingShangXiuGaiPanel spxgPanel = new GongYingShangXiuGaiPanel();
		final GongYingShangTianJiaPanel sptjPanel = new GongYingShangTianJiaPanel();
		tabPane.addTab("供应商信息添加", null, sptjPanel, "供应商添加");
		tabPane.addTab("供应商信息修改与删除", null, spxgPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spxgPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}