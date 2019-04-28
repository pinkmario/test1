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
		setTitle("��Ӧ�̹���");
		JTabbedPane tabPane = new JTabbedPane();
		final GongYingShangXiuGaiPanel spxgPanel = new GongYingShangXiuGaiPanel();
		final GongYingShangTianJiaPanel sptjPanel = new GongYingShangTianJiaPanel();
		tabPane.addTab("��Ӧ����Ϣ���", null, sptjPanel, "��Ӧ�����");
		tabPane.addTab("��Ӧ����Ϣ�޸���ɾ��", null, spxgPanel, "�޸���ɾ��");
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