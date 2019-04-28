package second.shangPinGuanLi;
import first.shujuku.ShuJuKu;
import internalFrame.guanli.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import model.ShangPinXX;
public class ShangPinTianJiaPanel extends JPanel {
	private JComboBox gysQuanCheng;
	private JTextField beiZhu;
	private JTextField wenHao;
	private JTextField piHao;
	private JTextField baoZhuang;
	private JTextField guiGe;
	private JTextField danWei;
	private JTextField chanDi;
	private JTextField khdwCheng;
	private JTextField quanCheng;
	private JButton resetButton;
	public ShangPinTianJiaPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);
		setupComponent(new JLabel("商品名称："), 0, 0, 1, 1, false);
		quanCheng = new JTextField();
		setupComponent(quanCheng, 1, 0, 3, 1, true);
		setupComponent(new JLabel("供应商单位："), 0, 1, 1, 1, false);
		khdwCheng = new JTextField();
		setupComponent(khdwCheng, 1, 1, 3, 10, true);
		setupComponent(new JLabel("品牌："), 0, 2, 1, 1, false);
		chanDi = new JTextField();
		setupComponent(chanDi, 1, 2, 3, 300, true);
		setupComponent(new JLabel("生产企业："), 0, 3, 1, 1, false);
		danWei = new JTextField();
		setupComponent(danWei, 1, 3, 1, 130, true);
		setupComponent(new JLabel("规格："), 2, 3, 1, 1, false);
		guiGe = new JTextField();
		setupComponent(guiGe, 3, 3, 1, 1, true);
		setupComponent(new JLabel("包装："), 0, 4, 1, 1, false);
		baoZhuang = new JTextField();
		setupComponent(baoZhuang, 1, 4, 1, 1, true);
		setupComponent(new JLabel("质保时间："), 2, 4, 1, 1, false);
		piHao = new JTextField();
		setupComponent(piHao, 3, 4, 1, 1, true);
		setupComponent(new JLabel("输出接口："), 0, 5, 1, 1, false);
		wenHao = new JTextField();
		setupComponent(wenHao, 1, 5, 3, 1, true);
		setupComponent(new JLabel("供应商全称："), 0, 6, 1, 1, false);
		gysQuanCheng = new JComboBox();
		gysQuanCheng.setMaximumRowCount(5);
		setupComponent(gysQuanCheng, 1, 6, 3, 1, true);
		setupComponent(new JLabel("备注："), 0, 7, 1, 1, false);
		beiZhu = new JTextField();
		setupComponent(beiZhu, 1, 7, 3, 1, true);
		final JButton tjButton = new JButton();
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (baoZhuang.getText().equals("")
						|| chanDi.getText().equals("")
						|| danWei.getText().equals("")
						|| guiGe.getText().equals("")
						|| khdwCheng.getText().equals("")
						|| piHao.getText().equals("")
						|| wenHao.getText().equals("")
						|| quanCheng.getText().equals("")) {
					JOptionPane.showMessageDialog(ShangPinTianJiaPanel.this,
							"请完成未填写的信息。", "商品添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = ShuJuKu
						.query("select * from ShangPingXingXi where spname='"
								+ quanCheng.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(
								ShangPinTianJiaPanel.this, "商品信息添加失败，存在同名商品",
								"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
				ResultSet set = ShuJuKu.query("select max(id) from ShangPingXingXi");
				String id = null;
				try {
					if (set != null && set.next()) {
						String sid = set.getString(1);
						if (sid == null)
							id = "sp1001";
						else {
							String str = sid.substring(2);
							id = "sp" + (Integer.parseInt(str) + 1);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ShangPinXX spInfo = new ShangPinXX();
				spInfo.setId(id);
				spInfo.setBz(baoZhuang.getText().trim());
				spInfo.setPp(chanDi.getText().trim());
				spInfo.setScjy(danWei.getText().trim());
				spInfo.setGg(guiGe.getText().trim());
				if(gysQuanCheng.getSelectedItem() == null){
					JOptionPane.showMessageDialog(ShangPinTianJiaPanel.this,
							"请先选择供应商", "商品添加", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				spInfo.setGysname(gysQuanCheng.getSelectedItem().toString()
						.trim());
				spInfo.setGysdw(khdwCheng.getText().trim());
				spInfo.setZhuyi(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setScjk(wenHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				ShuJuKu.addSp(spInfo);
				JOptionPane.showMessageDialog(ShangPinTianJiaPanel.this,
						"商品信息已经成功添加", "商品添加", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});
		tjButton.setText("添加");
		setupComponent(tjButton, 1, 8, 1, 1, false);
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.weighty = 1.0;
		gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
		gridBagConstraints_20.gridy = 8;
		gridBagConstraints_20.gridx = 1;
		// 重添按钮的事件监听类
		resetButton = new JButton();
		setupComponent(tjButton, 3, 8, 1, 1, false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				baoZhuang.setText("");
				chanDi.setText("");
				danWei.setText("");
				guiGe.setText("");
				khdwCheng.setText("");
				beiZhu.setText("");
				piHao.setText("");
				wenHao.setText("");
				quanCheng.setText("");
			}
		});
		resetButton.setText("重添");
	}
	// 设置组件位置并添加到容器中
	private void setupComponent(JComponent component, int gridx, int gridy,
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
	// 初始化供应商下拉选择框
	public void initGysBox() {
		List gysInfo = ShuJuKu.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gysQuanCheng.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gysQuanCheng.addItem(item);
		}
	}
}