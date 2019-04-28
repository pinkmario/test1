package second.gongYingShangGuanLi;
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


import second.keHuGuanLi.KeHuXiuGaiPanel;
import model.GongYingShangGL;
public class GongYingShangXiuGaiPanel extends JPanel {
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
	private JComboBox gys;
	public GongYingShangXiuGaiPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);

		setupComponet(new JLabel("供应商全称："), 0, 0, 1, 1, false);
		quanChengF = new JTextField();
		quanChengF.setEditable(false);
		setupComponet(quanChengF, 1, 0, 3, 400, true);

		setupComponet(new JLabel("供应商单位："), 0, 1, 1, 1, false);

		khdwChengF = new JTextField();
		setupComponet(khdwChengF, 1, 1, 1, 160, true);

		setupComponet(new JLabel("电子邮箱："), 2, 1, 1, 1, false);
		youxiangF = new JTextField();
		
		setupComponet(youxiangF, 3, 1, 1, 0, true);

		setupComponet(new JLabel("地址："), 0, 2, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 1, 2, 3, 0, true);

		setupComponet(new JLabel("联系人1："), 0, 3, 1, 1, false);
		dianHuaF = new JTextField();
		
		setupComponet(dianHuaF, 1, 3, 1, 0, true);

		setupComponet(new JLabel("联系人2："), 2, 3, 1, 1, false);
		chuanZhenF = new JTextField();
		
		setupComponet(chuanZhenF, 3, 3, 1, 0, true);

		setupComponet(new JLabel("联系电话1："), 0, 4, 1, 1, false);
		ltel1XiRenF = new JTextField();
		setupComponet(ltel1XiRenF, 1, 4, 1, 0, true);

		setupComponet(new JLabel("联系电话2："), 2, 4, 1, 1, false);
		ltel1XiRenDianHuaF = new JTextField();
		
		setupComponet(ltel1XiRenDianHuaF, 3, 4, 1, 0, true);

		setupComponet(new JLabel("开户银行："), 0, 5, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 1, 5, 1, 0, true);

		setupComponet(new JLabel("银行账户："), 2, 5, 1, 1, false);
		KhbeizhuF = new JTextField();
		setupComponet(KhbeizhuF, 3, 5, 1, 0, true);

		setupComponet(new JLabel("选择供应商"), 0, 7, 1, 0, false);
		gys = new JComboBox();
		gys.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		// 处理供应商信息的下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		// 定位供应商信息的下拉选择框
		setupComponet(gys, 1, 7, 2, 0, true);
		JButton modifyButton = new JButton("修改");
		JButton delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new DelActionListener());
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ModifyActionListener());
	}
	// 初始化供应商下拉选择框
	public void initComboBox() {
		List gysInfo = ShuJuKu.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gys.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gys.addItem(item);
		}
		doGysSelectAction();
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
	// 处理供应商选择事件
	private void doGysSelectAction() {
		Item selectedItem;
		if (!(gys.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) gys.getSelectedItem();
		GongYingShangGL gysInfo = ShuJuKu.getGysInfo(selectedItem);
		quanChengF.setText(gysInfo.getName());
		diZhiF.setText(gysInfo.getAddress());
		khdwChengF.setText(gysInfo.getGysdw());
		youxiangF.setText(gysInfo.getyouxiang());
		dianHuaF.setText(gysInfo.getLname1());
		chuanZhenF.setText(gysInfo.getlname2());
		ltel1XiRenF.setText(gysInfo.getLtel1());
		ltel1XiRenDianHuaF.setText(gysInfo.getLtel2());
		KhbeizhuF.setText(gysInfo.gethao());
		yinHangF.setText(gysInfo.getYh());
	}
	//修改按钮的事件监听器
	class ModifyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();
			if(item == null){
				JOptionPane.showMessageDialog(GongYingShangXiuGaiPanel.this, "请选中要删除的数据");
				return;
			}
			GongYingShangGL gysInfo = new GongYingShangGL();
			gysInfo.setId(item.getId());
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
			if (ShuJuKu.updateGys(gysInfo) == 1)
				JOptionPane.showMessageDialog(GongYingShangXiuGaiPanel.this, "修改完成");
			else
				JOptionPane.showMessageDialog(GongYingShangXiuGaiPanel.this, "修改失败");
		}
	}
	//删除按钮的事件监听器
	class DelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();
			if (item == null || !(item instanceof Item))
				return;
			int confirm = JOptionPane.showConfirmDialog(
					GongYingShangXiuGaiPanel.this, "确认删除供应商信息吗？");
			if (confirm == JOptionPane.YES_OPTION) {
				int rs = ShuJuKu.delete("delete from GongYingShangXingXi where id='"
						+ item.getId() + "'");
				if (rs > 0) {
					JOptionPane.showMessageDialog(GongYingShangXiuGaiPanel.this,
							"供应商：" + item.getName() + "。删除成功");
					gys.removeItem(item);
				} else {
					JOptionPane.showMessageDialog(GongYingShangXiuGaiPanel.this,
							"无法删除客户：" + item.getName() + "。");
				}
			}
		}
	}
}
