package second.gongYingShangGuanLi;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import model.GongYingShangGL;
import first.shujuku.ShuJuKu;
public class GongYingShangTianJiaPanel extends JPanel {
	private JTextField KhbeizhuF;
	private JTextField yinHangF;
	private JTextField ltel1XiRenDianHuaF;
	private JTextField ltel1XiRenF;
	private JTextField chuanZhenF;
	private JTextField dianHuaF;
	private JTextField diZhiF;
	private JTextField youxiangF;
	private JTextField khdwChengF;
	private JTextField quanChengF;
	private JButton resetButton;
	public GongYingShangTianJiaPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);

		setupComponet(new JLabel("��Ӧ��ȫ�ƣ�"), 0, 0, 1, 1, false);

		quanChengF = new JTextField();
		setupComponet(quanChengF, 1, 0, 3, 400, true);

		setupComponet(new JLabel("��Ӧ�̵�λ��"), 0, 1, 1, 1, false);

		khdwChengF = new JTextField();
		setupComponet(khdwChengF, 1, 1, 1, 160, true);

		setupComponet(new JLabel("�������䣺"), 2, 1, 1, 1, false);
		youxiangF = new JTextField();
		
		setupComponet(youxiangF, 3, 1, 1, 0, true);

		setupComponet(new JLabel("��ַ��"), 0, 2, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 1, 2, 3, 0, true);

		setupComponet(new JLabel("��ϵ��1��"), 0, 3, 1, 1, false);
		dianHuaF = new JTextField();
		
		setupComponet(dianHuaF, 1, 3, 1, 0, true);

		setupComponet(new JLabel("��ϵ��2��"), 2, 3, 1, 1, false);
		chuanZhenF = new JTextField();
		
		setupComponet(chuanZhenF, 3, 3, 1, 0, true);

		setupComponet(new JLabel("��ϵ�绰1��"), 0, 4, 1, 1, false);
		ltel1XiRenF = new JTextField();
		setupComponet(ltel1XiRenF, 1, 4, 1, 0, true);

		setupComponet(new JLabel("��ϵ�绰2��"), 2, 4, 1, 1, false);
		ltel1XiRenDianHuaF = new JTextField();
		
		setupComponet(ltel1XiRenDianHuaF, 3, 4, 1, 0, true);

		setupComponet(new JLabel("�������У�"), 0, 5, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 1, 5, 1, 0, true);

		setupComponet(new JLabel("�����˻���"), 2, 5, 1, 1, false);
		KhbeizhuF = new JTextField();
		setupComponet(KhbeizhuF, 3, 5, 1, 0, true);

		final JButton tjButton = new JButton();
		tjButton.addActionListener(new TjActionListener());
		tjButton.setText("���");
		setupComponet(tjButton, 2, 6, 1, 0, false);

		resetButton = new JButton();
		setupComponet(resetButton, 3, 6, 1, 0, false);
		resetButton.addActionListener(new ResetActionListener());
		resetButton.setText("����");
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	class ResetActionListener implements ActionListener {// ���ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			diZhiF.setText("");
			youxiangF.setText("");
			chuanZhenF.setText("");
			khdwChengF.setText("");
			ltel1XiRenF.setText("");
			ltel1XiRenDianHuaF.setText("");
			KhbeizhuF.setText("");
			quanChengF.setText("");
			dianHuaF.setText("");
			yinHangF.setText("");
		}
	}
	class TjActionListener implements ActionListener {// ��Ӱ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			if (diZhiF.getText().equals("") || quanChengF.getText().equals("")
					|| chuanZhenF.getText().equals("")
					|| khdwChengF.getText().equals("")
					|| yinHangF.getText().equals("")
					|| youxiangF.getText().equals("")
					|| diZhiF.getText().equals("")
					|| ltel1XiRenF.getText().equals("")
					|| ltel1XiRenDianHuaF.getText().equals("")
					|| KhbeizhuF.getText().equals("")
					|| dianHuaF.getText().equals("")) {
				JOptionPane.showMessageDialog(GongYingShangTianJiaPanel.this, "����дȫ����Ϣ");
				return;
			}
			try {
				ResultSet haveUser = ShuJuKu
						.query("select * from GongYingShangXingXi where name='"
								+ quanChengF.getText().trim() + "'");
				if (haveUser.next()) {
					JOptionPane.showMessageDialog(GongYingShangTianJiaPanel.this,
							"��Ӧ����Ϣ���ʧ�ܣ�����ͬ����Ӧ��", "��Ӧ�������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				ResultSet set = ShuJuKu.query("select max(id) from GongYingShangXingXi");
				String id = null;
				if (set != null && set.next()) {
					String sid = set.getString(1) == null ? null : set.getString(1).trim();
					if (sid == null)
						id = "gys1001";
					else {
						String str = sid.substring(3);
						id = "gys" + (Integer.parseInt(str) + 1);
					}
				}
				GongYingShangGL gysInfo = new GongYingShangGL();
				gysInfo.setId(id);
				gysInfo.setAddress(diZhiF.getText().trim());
				gysInfo.setyouxiang(youxiangF.getText().trim());
				gysInfo.setlname2(chuanZhenF.getText().trim());
				gysInfo.setYh(yinHangF.getText().trim());
				gysInfo.setGysdw(khdwChengF.getText().trim());
				gysInfo.setName(quanChengF.getText().trim());
				gysInfo.setLtel1(ltel1XiRenF.getText().trim());
				gysInfo.setLtel2(ltel1XiRenDianHuaF.getText().trim());
				gysInfo.sethao(KhbeizhuF.getText().trim());
				gysInfo.setLname1(dianHuaF.getText().trim());
				ShuJuKu.addGys(gysInfo);
				JOptionPane.showMessageDialog(GongYingShangTianJiaPanel.this, "�ѳɹ���ӿͻ�",
						"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}