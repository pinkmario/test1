package second.keHuGuanLi;

import first.shujuku.ShuJuKu;
import internalFrame.guanli.Item;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import model.KehuXX;
public class KeHuXiuGaiPanel extends JPanel {
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
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox kehu;
	public KeHuXiuGaiPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel khName = new JLabel();
		khName.setText("客户全称：");
		setupComponet(khName, 0, 0, 1, 0, false);

		keHuQuanCheng = new JTextField();
		keHuQuanCheng.setEditable(false);
		// 定位全称文本框
		setupComponet(keHuQuanCheng, 1, 0, 3, 350, true);

		final JLabel addressLabel = new JLabel("客户地址：");
		setupComponet(addressLabel, 0, 1, 1, 0, false);

		diZhi = new JTextField();
		// 定位地址文本框
		setupComponet(diZhi, 1, 1, 3, 0, true);

		setupComponet(new JLabel("客户单位："), 0, 2, 1, 0, false);
		keHuDanWei = new JTextField();
		// 定位客户单位文本框
		setupComponet(keHuDanWei, 1, 2, 1, 130, true);

		setupComponet(new JLabel("电子邮箱："), 2, 2, 1, 0, false);

		youZhengyouxiang = new JTextField();
		// 定位电子邮箱文本框
		setupComponet(youZhengyouxiang, 3, 2, 1, 100, true);
		

		setupComponet(new JLabel("联系人1："), 0, 3, 1, 0, false);

		dianHua = new JTextField();
		// 定位电话文本框
		setupComponet(dianHua, 1, 3, 1, 100, true);
	

		setupComponet(new JLabel("联系人2："), 2, 3, 1, 0, false);
		chuanZhen = new JTextField();
		// 定位联系人2文本框
		
		setupComponet(chuanZhen, 3, 3, 1, 100, true);

		setupComponet(new JLabel("联系电话1："), 0, 4, 1, 0, false);
		ltel1XiRen = new JTextField();
		// 定位联系人文本框
		setupComponet(ltel1XiRen, 1, 4, 1, 100, true);

		setupComponet(new JLabel("联系电话2："), 2, 4, 1, 0, false);
		ltel1XiDianHua = new JTextField();
		// 定位联系电话文本框
		setupComponet(ltel1XiDianHua, 3, 4, 1, 100, true);
		

		setupComponet(new JLabel("备注："), 0, 5, 1, 0, false);
		Khbeizhu = new JTextField();
		// 定位备注文本框
		setupComponet(Khbeizhu, 1, 5, 3, 350, true);

		setupComponet(new JLabel("开户银行："), 0, 6, 1, 0, false);
		kaiHuYinHang = new JTextField();
		// 定位开户银行文本框
		setupComponet(kaiHuYinHang, 1, 6, 1, 100, true);

		setupComponet(new JLabel("银行账号："), 2, 6, 1, 0, false);
		yinHangZhangHao = new JTextField();
		// 定位银行账号文本框
		setupComponet(yinHangZhangHao, 3, 6, 1, 100, true);

		setupComponet(new JLabel("选择客户"), 0, 7, 1, 0, false);
		kehu = new JComboBox();
		kehu.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		// 处理客户信息的下拉选择框的选择事件
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKeHuSelectAction();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(kehu, 1, 7, 2, 0, true);
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						KeHuXiuGaiPanel.this, "确认删除客户信息吗？");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = ShuJuKu.delete("delete from KeHuXingXi where id='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this,
								"客户：" + item.getName() + "。删除成功");
						kehu.removeItem(item);
					}
				}
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				KehuXX khinfo = new KehuXX();
				if(item == null)return;
				khinfo.setId(item.getId());
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
				if (ShuJuKu.updateKeHu(khinfo) == 1)
					JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this, "修改完成");
				else
					JOptionPane.showMessageDialog(KeHuXiuGaiPanel.this, "修改失败");
			}
		});
	}
	// 初始化客户下拉选择框
	public void initComboBox() {
		List khInfo = ShuJuKu.getKhInfos();
		List<Item> items = new ArrayList<Item>();
		kehu.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			kehu.addItem(item);
		}
		doKeHuSelectAction();
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	private void doKeHuSelectAction() {
		Item selectedItem;
		if (!(kehu.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) kehu.getSelectedItem();
		KehuXX khInfo = ShuJuKu.getKhInfo(selectedItem);
		keHuQuanCheng.setText(khInfo.getKhname());
		diZhi.setText(khInfo.getAddress());
		keHuDanWei.setText(khInfo.getKhdw());
		youZhengyouxiang.setText(khInfo.getyouxiang());
		dianHua.setText(khInfo.getLname1());
		chuanZhen.setText(khInfo.getlname2());
		ltel1XiRen.setText(khInfo.getLtel1());
		ltel1XiDianHua.setText(khInfo.getLtel2());
		Khbeizhu.setText(khInfo.getMail());
		kaiHuYinHang.setText(khInfo.getyinhang());
		yinHangZhangHao.setText(khInfo.getHao());
	}
}
