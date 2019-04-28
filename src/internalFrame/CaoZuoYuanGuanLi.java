package internalFrame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import second.caoZuoYuanGuanLi.ShanChuCaoZuoYuan;
import second.caoZuoYuanGuanLi.TianJiaCaoZuoYuan;
public class CaoZuoYuanGuanLi extends JInternalFrame {
	public CaoZuoYuanGuanLi() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 491, 287);
		setTitle("����Ա����");
		JTabbedPane tabPane = new JTabbedPane();
		final TianJiaCaoZuoYuan tjPanel = new TianJiaCaoZuoYuan();
		final ShanChuCaoZuoYuan delPanel = new ShanChuCaoZuoYuan();
		tabPane.addTab("��Ӳ���Ա", null, tjPanel, "��Ӳ���Ա");
		tabPane.addTab("ɾ������Ա", null, delPanel, "ɾ������Ա");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				delPanel.initTable();
			}
		});
		pack();
		setVisible(true);
	}
}
