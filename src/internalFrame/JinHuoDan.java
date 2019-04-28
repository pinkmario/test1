package internalFrame;
import first.denglu.DengLu;
import first.shujuku.ShuJuKu;
import internalFrame.guanli.Item;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import model.GongYingShangGL;
import model.RukuDetail;
import model.RukuMain;
import model.ShangPinXX;
import model.Userlist;
public class JinHuoDan extends JInternalFrame {
	private final JTable table;
	private Userlist user = DengLu.getUser(); 			// 登录用户信息
	private final JTextField jhsj = new JTextField(); 	// 进货时间
	private final JTextField jsr = new JTextField(); 	// 经手人
	private final JComboBox jsfs = new JComboBox(); 	// 计算方式
	private final JTextField ltel1 = new JTextField(); 	// 联系电话
	private final JComboBox gys = new JComboBox(); 		// 供应商
	private final JTextField piaoHao = new JTextField();// 号码
	private final JTextField pzs = new JTextField("0"); // 品种数量
	private final JTextField hpzs = new JTextField("0");// 货品总数
	private final JTextField hjje = new JTextField("0");// 合计金额
	private final JTextField bz = new JTextField(); 	// 备注
	private final JTextField czy = new JTextField(user.getName());// 操作员
	private Date jhsjDate;
	private JComboBox sp;
	public JinHuoDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("进货单");
		setBounds(50, 50, 800, 400);

		setupComponet(new JLabel("进货号码："), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 140, true);

		setupComponet(new JLabel("供应商："), 2, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		// 供应商下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 3, 0, 1, 1, true);

		setupComponet(new JLabel("联系电话："), 4, 0, 1, 0, false);
		ltel1.setFocusable(false);
		setupComponet(ltel1, 5, 0, 1, 80, true);

		setupComponet(new JLabel("结算方式："), 0, 1, 1, 0, false);
		jsfs.addItem("现金");
		jsfs.addItem("支付宝");
		jsfs.addItem("微信");
		jsfs.addItem("银行转账");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);

		setupComponet(new JLabel("进货时间："), 2, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 3, 1, 1, 1, true);

		setupComponet(new JLabel("经手人："), 4, 1, 1, 0, false);
		setupComponet(jsr, 5, 1, 1, 1, true);

		sp = new JComboBox();
		setupComponet(new JLabel("所属商品："), 0, 2, 1, 0, false);
		setupComponet(sp, 1, 2, 100, 1, true);
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShangPinXX info = (ShangPinXX) sp.getSelectedItem();
				// 如果选择有效就更新表格
				//System.out.println(info);
				if (info != null && info.getId() != null) {
					//System.out.println(info.getSpname());
					updateTable();
				}
			}
		});

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// 添加事件完成品种数量、货品总数、合计金额的计算
		table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 3, 6, 1, true);

		setupComponet(new JLabel("品种数量："), 0, 4, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 4, 1, 1, true);

		setupComponet(new JLabel("货品总数："), 2, 4, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 4, 1, 1, true);

		setupComponet(new JLabel("合计金额："), 4, 4, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 4, 1, 1, true);

		setupComponet(new JLabel("备注："), 0, 5, 1, 0, false);
		setupComponet(bz, 1, 5, 1, 1, true);

		setupComponet(new JLabel("操作人员："), 2, 5, 1, 0, false);
		czy.setFocusable(false);
		setupComponet(czy, 3, 5, 1, 1, true);

		// 单击添加按钮在表格中添加新的一行
		JButton tjButton = new JButton("删除");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 4, 5, 1, 1, false);

		// 单击入库按钮保存进货信息
		JButton rkButton = new JButton("入库");
		rkButton.addActionListener(new RkActionListener());
		setupComponet(rkButton, 5, 5, 1, 1, false);
		// 添加窗体监听器，完成初始化
		addInternalFrameListener(new initTasks());
	}
	// 初始化表格
	private void initTable() {
		String[] columnNames = {"商品名称", "商品编号", "品牌", "生产企业", "规格", "包装", "单价",
				"数量", "质保时间", "输出接口"};
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		final DefaultCellEditor editor = new DefaultCellEditor(sp);
		editor.setClickCountToStart(2);
		column.setCellEditor(editor);
	}
	// 初始化商品下拉选择框
	private void initSpBox() {
		List list = new ArrayList();
		ResultSet set = ShuJuKu.query("select * from ShangPingXingXi where gysName='"
				+ gys.getSelectedItem() + "'");
		sp.removeAllItems();
		//sp.addItem(new TbSpinfo());
//		for (int i = 0; table != null && i < table.getRowCount(); i++) {
//			TbSpinfo tmpInfo = (TbSpinfo) table.getValueAt(i, 0);
//			if (tmpInfo != null && tmpInfo.getId() != null)
//				list.add(tmpInfo.getId());
//		}
//		table.removeAll();
		try {
			while (set.next()) {
				
				ShangPinXX spinfo = new ShangPinXX();
				spinfo.setId(set.getString("id").trim());
				// 如果表格中以存在同样商品，商品下拉框中就不再模糊查询该商品
				if (list.contains(spinfo.getId()))
					continue;
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
				sp.addItem(spinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		getContentPane().add(component, gridBagConstrains);
	}
	// 供应商选择时更新联系电话字段
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		GongYingShangGL gysInfo = ShuJuKu.getGysInfo(item);
		ltel1.setText(gysInfo.getLtel1());
		initSpBox();
	}
	class RkActionListener implements ActionListener {	// 入库按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			// 结束表格中没有编写的单元
			stopTableCellEditing();
			// 清除空行
			clearEmptyRow();
			String hpzsStr = hpzs.getText(); // 货品总数
			String pzsStr = pzs.getText(); // 品种数
			String jeStr = hjje.getText(); // 合计金额
			String jsfsStr = jsfs.getSelectedItem().toString(); // 结算方式
			String jsrStr = jsr.getText().trim(); // 经手人
			String czyStr = czy.getText(); // 操作员
			String rkDate = jhsjDate.toLocaleString(); // 入库时间
			String bzStr = bz.getText().trim(); // 备注
			String id = piaoHao.getText(); // 号码
			String gysName = gys.getSelectedItem().toString();// 供应商名字
			if (jsrStr == null || jsrStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "请填写经手人");
				return;
			}
			if (bzStr == null || bzStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "填写备注");
				return;
			}
			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "填加入库商品");
				return;
			}
			RukuMain ruMain = new RukuMain(id, pzsStr, jeStr, bzStr,
					gysName, rkDate, czyStr, jsrStr, jsfsStr);
			Set<RukuDetail> set = ruMain.getTabRukuDetails();
			int rows = table.getRowCount();
			for (int i = 0; i < rows; i++) {
				//TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);
				String djStr = table.getValueAt(i, 6).toString();
				String slStr = table.getValueAt(i, 7).toString();
				Double dj = Double.valueOf(djStr);
				Integer sl = Integer.valueOf(slStr);
				RukuDetail detail = new RukuDetail();
				//detail.setTabSpinfo(spinfo.getId());
				detail.setTabSpinfo(table.getValueAt(i, 1).toString());
				detail.setTabRukuMain(ruMain.getRkId());
				detail.setDj(dj);
				detail.setSl(sl);
				set.add(detail);
			}
			boolean rs = ShuJuKu.insertRukuInfo(ruMain);
			if (rs) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "入库完成");
				DefaultTableModel dftm = new DefaultTableModel();
				table.setModel(dftm);
				initTable();
				pzs.setText("0");
				hpzs.setText("0");
				hjje.setText("0");
				initPiaoHao();
			}
		}
	}
	class TjActionListener implements ActionListener {	// 删除按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			/*
			// 初始化号码
			initPiaoHao();
			// 结束表格中没有编写的单元
			stopTableCellEditing();
			// 如果表格中还模糊查询空行，就再添加新行
			for (int i = 0; i < table.getRowCount(); i++) {
				TbSpinfo info = (TbSpinfo) table.getValueAt(i, 0);
				if (table.getValueAt(i, 0) == null)
					return;
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Vector());
			//initSpBox();
			 */
			int row = table.getSelectedRow();
			if(row >= 0){
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(row);
				int rows = table.getRowCount();
				int count = 0;
				double money = 0.0;
				// 计算品种数量
//				TbSpinfo column = null;
//				if (rows > 0)
//					column = (TbSpinfo) table.getValueAt(rows - 1, 0);
//				if (rows > 0 && (column == null || column.getId().isEmpty()))
//					rows--;
				// 计算货品总数和金额
				for (int i = 0; i < rows; i++) {
					String column7 = (String) table.getValueAt(i, 7);
					String column6 = (String) table.getValueAt(i, 6);
					int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer
							.parseInt(column7);
					float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float
							.parseFloat(column6);
					count += c7;
					money += c6 * c7;
				}

				pzs.setText(rows + "");
				hpzs.setText(count + "");
				hjje.setText(money + "");
			}else{
				JOptionPane.showMessageDialog(JinHuoDan.this, "请选择要删除的一行");
			}
		}
	}
	// 在事件中计算品种数量、货品总数、合计金额
	private final class computeInfo implements ContainerListener {
		public void componentRemoved(ContainerEvent e) {
			// 清除空行
			clearEmptyRow();
			// 计算代码
			int rows = table.getRowCount();
			int count = 0;
			double money = 0.0;
			// 计算品种数量
//			TbSpinfo column = null;
//			if (rows > 0)
//				column = (TbSpinfo) table.getValueAt(rows - 1, 0);
//			if (rows > 0 && (column == null || column.getId().isEmpty()))
//				rows--;
			// 计算货品总数和金额
			for (int i = 0; i < rows; i++) {
				String column7 = (String) table.getValueAt(i, 7);
				String column6 = (String) table.getValueAt(i, 6);
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer
						.parseInt(column7);
				float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float
						.parseFloat(column6);
				count += c7;
				money += c6 * c7;
			}

			pzs.setText(rows + "");
			hpzs.setText(count + "");
			hjje.setText(money + "");
			// /////////////////////////////////////////////////////////////////
		}
		public void componentAdded(ContainerEvent e) {
		}
	}
	// 窗体的初始化任务
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
			initPiaoHao();
			initSpBox();
		}
		private void initGysField() {// 初始化供应商字段
			List gysInfos = ShuJuKu.getGysInfos();
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {
				List list = (List) iter.next();
				Item item = new Item();
				item.setId(list.get(0).toString().trim());
				item.setName(list.get(1).toString().trim());
				gys.addItem(item);
			}
			doGysSelectAction();
		}
		private void initTimeField() {// 启动进货时间线程
			new Thread(new Runnable() {
				public void run() {
					try {
						while (true) {
							jhsjDate = new Date();
							jhsj.setText(jhsjDate.toLocaleString());
							Thread.sleep(100);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	// 初始化号码文本框的方法
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(jhsjDate.getTime());
		String maxId = ShuJuKu.getRuKuMainMaxId(date);
		piaoHao.setText(maxId);
	}
	// 根据商品下拉框的选择，更新表格当前行的内容
	private synchronized void updateTable() {
		ShangPinXX spinfo = (ShangPinXX) sp.getSelectedItem();
		
		//检查表格中是否已经添加该商品
		for(int i=0; i<table.getRowCount();i++){
			String spidString = table.getValueAt(i, 1).toString();
			if(spidString.equals(spinfo.getId())){
				return;
			}
		}
		if(spinfo != null){
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Vector tmpVector = new Vector();
			tmpVector.add(spinfo.getSpname());
			tmpVector.add(spinfo.getId());
			tmpVector.add(spinfo.getPp());
			tmpVector.add(spinfo.getScjy());
			tmpVector.add(spinfo.getGg());
			tmpVector.add(spinfo.getBz());
			tmpVector.add("0");
			tmpVector.add("0");
			tmpVector.add(spinfo.getPh());
			tmpVector.add(spinfo.getScjk());
			model.addRow(tmpVector);;
			table.editCellAt(model.getRowCount()-1, 6);
		}
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		model.addRow(new Vector());
//		int row = table.getRowCount();
//		if (row >= 0 && spinfo != null) {
//			table.setValueAt(spinfo.getSpname(), row, 0);
//			table.setValueAt(spinfo.getId(), row, 1);
//			table.setValueAt(spinfo.getPp(), row, 2);
//			table.setValueAt(spinfo.getScjy(), row, 3);
//			table.setValueAt(spinfo.getGg(), row, 4);
//			table.setValueAt(spinfo.getBz(), row, 5);
//			table.setValueAt("0", row, 6);
//			table.setValueAt("0", row, 7);
//			table.setValueAt(spinfo.getPh(), row, 8);
//			table.setValueAt(spinfo.getScjk(), row, 9);
//			table.editCellAt(row, 6);
//		}
	}
	// 清除空行
	private synchronized void clearEmptyRow() {
//		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
//		for (int i = 0; i < table.getRowCount(); i++) {
//			TbSpinfo info2 = (TbSpinfo) table.getValueAt(i, 0);
//			if (info2 == null || info2.getId() == null
//					|| info2.getId().isEmpty()) {
//				dftm.removeRow(i);
//			}
//		}
	}
	// 停止表格单元的编辑
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
