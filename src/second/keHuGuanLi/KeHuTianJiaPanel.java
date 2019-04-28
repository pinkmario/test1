package second.keHuGuanLi;
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


import model.KehuXX;
import first.shujuku.ShuJuKu;
public class KeHuTianJiaPanel extends JPanel {
	private JTextField keHuQuanCheng;
	private JTextField yinHangZhangHao;
	private JTextField kaiHuYinHang;
	private JTextField Khbeizhu;
	private JTextField ltel1XiDianHua;
	private JTextField ltel1XiRen;
	private JTextField chuanZhen;
	private JTextField dianHua;
	private JTextField youZhengyouxiang;
	private JTextField diZhi;
	private JTextField keHuDanWei;
	private JButton resetButton;
	public KeHuTianJiaPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		final JLabel khName = new JLabel();
		khName.setText("�ͻ�ȫ�ƣ�");
		setupComponet(khName, 0, 0, 1, 0, false);
		keHuQuanCheng = new JTextField();
		// ��λȫ���ı���
		setupComponet(keHuQuanCheng, 1, 0, 3, 350, true);
		final JLabel addressLabel = new JLabel("�ͻ���ַ��");
		setupComponet(addressLabel, 0, 1, 1, 0, false);
		diZhi = new JTextField();
		// ��λ��ַ�ı���
		setupComponet(diZhi, 1, 1, 3, 0, true);
		final JLabel Gysdw = new JLabel();
		Gysdw.setText("�ͻ���λ��");
		setupComponet(Gysdw, 0, 2, 1, 0, false);
		keHuDanWei = new JTextField();
		// ��λ�ͻ���λ�ı���
		setupComponet(keHuDanWei, 1, 2, 1, 100, true);
		setupComponet(new JLabel("�������䣺"), 2, 2, 1, 0, false);
		youZhengyouxiang = new JTextField();
		// ��λ���������ı���
		setupComponet(youZhengyouxiang, 3, 2, 1, 100, true);
		
		setupComponet(new JLabel("��ϵ��1��"), 0, 3, 1, 0, false);
		dianHua = new JTextField();
		// ��λ��ϵ��1�ı���
		setupComponet(dianHua, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("��ϵ��2��"), 2, 3, 1, 0, false);
		chuanZhen = new JTextField();
		// ��λ��ϵ��2�ı���
		
		setupComponet(chuanZhen, 3, 3, 1, 100, true);
		setupComponet(new JLabel("��ϵ�绰1��"), 0, 4, 1, 0, false);
		ltel1XiRen = new JTextField();
		// ��λ��ϵ���ı���
		setupComponet(ltel1XiRen, 1, 4, 1, 100, true);
		setupComponet(new JLabel("��ϵ�绰2��"), 2, 4, 1, 0, false);
		ltel1XiDianHua = new JTextField();
		// ��λ��ϵ�绰�ı���
		setupComponet(ltel1XiDianHua, 3, 4, 1, 100, true);
		
		setupComponet(new JLabel("��ע��"), 0, 5, 1, 0, false);
		Khbeizhu = new JTextField();
		// ��λ��ע�ı���
		setupComponet(Khbeizhu, 1, 5, 3, 350, true);
		setupComponet(new JLabel("�������У�"), 0, 6, 1, 0, false);
		kaiHuYinHang = new JTextField();
		// ��λ���������ı���
		setupComponet(kaiHuYinHang, 1, 6, 1, 100, true);
		setupComponet(new JLabel("�����˺ţ�"), 2, 6, 1, 0, false);
		yinHangZhangHao = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(yinHangZhangHao, 3, 6, 1, 100, true);
		final JButton saveButton = new JButton("����");
		// ��λ���水ť
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		// ��λ���ð�ť
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ChongZheButtonActionListener());
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
	// ���水ť���¼�������
	private final class SaveButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (diZhi.getText().equals("")
					|| youZhengyouxiang.getText().equals("")
					|| chuanZhen.getText().equals("")
					|| yinHangZhangHao.getText().equals("")
					|| keHuDanWei.getText().equals("")
					|| keHuQuanCheng.getText().equals("")
					|| ltel1XiRen.getText().equals("")
					|| ltel1XiDianHua.getText().equals("")
					|| Khbeizhu.getText().equals("")
					|| dianHua.getText().equals("")
					|| kaiHuYinHang.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			ResultSet haveUser = ShuJuKu
					.query("select * from KeHuXingXi where khname='"
							+ keHuQuanCheng.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(KeHuTianJiaPanel.this,
							"�޷���ӣ�������ͬ���ֵĿͻ�", "�ͻ������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			ResultSet set = ShuJuKu.query("select max(id) from KeHuXingXi");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "kh1001";
					else {
						String str = sid.substring(2);
						id = "kh" + (Integer.parseInt(str) + 1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			KehuXX khinfo = new KehuXX();
			khinfo.setId(id);
			khinfo.setAddress(diZhi.getText().trim());
			khinfo.setyouxiang(youZhengyouxiang.getText().trim());
			khinfo.setlname2(chuanZhen.getText().trim());
			khinfo.setHao(yinHangZhangHao.getText().trim());
			khinfo.setKhdw(keHuDanWei.getText().trim());
			khinfo.setKhname(keHuQuanCheng.getText().trim());
			khinfo.setLian(ltel1XiRen.getText().trim());
			khinfo.setLtel2(ltel1XiDianHua.getText().trim());
			khinfo.setMail(Khbeizhu.getText().trim());
			khinfo.setLname1(dianHua.getText().trim());
			khinfo.setyinhang(kaiHuYinHang.getText());
			ShuJuKu.addKeHu(khinfo);
			JOptionPane.showMessageDialog(KeHuTianJiaPanel.this, "�ѳɹ���ӿͻ�",
					"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// ���ð�ť���¼�������
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			keHuQuanCheng.setText("");
			yinHangZhangHao.setText("");
			kaiHuYinHang.setText("");
			Khbeizhu.setText("");
			ltel1XiDianHua.setText("");
			ltel1XiRen.setText("");
			chuanZhen.setText("");
			dianHua.setText("");
			youZhengyouxiang.setText("");
			diZhi.setText("");
			keHuDanWei.setText("");
		}
	}
}