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
import model.KuCun;
import model.RukuTuihuoDetail;
import model.RukuTuihuoMain;
import model.RukuDetail;
import model.RukuMain;
import model.ShangPinXX;
import model.Userlist;
public class JinHuoTuiHuo extends JInternalFrame {
	private final JTable table;
	private Userlist user = DengLu.getUser(); // 登录用户信息
	private final JTextField jhsj = new JTextField(); // 进货时间
	private final JTextField jsr = new JTextField(); // 经手人
	private final JComboBox jsfs = new JComboBox(); // 计算方式
	private final JTextField ltel1 = new JTextField(); // 联系电话
	private final JComboBox gys = new JComboBox(); // 供应商
	private final JTextField piaoHao = new JTextField(); // 号码
	private final JTextField pzs = new JTextField("0"); // 品种数量
	private final JTextField hpzs = new JTextField("0"); // 货品总数
	private final JTextField hjje = new JTextField("0"); // 合计金额
	private final JTextField bz = new JTextField(); // 备注
	private final JTextField czy = new JTextField(user.getName());// 操作员
	private Date jhsjDate;
	private JComboBox sp;
	public JinHuoTuiHuo() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("进货退货");
		setBounds(50, 50, 700, 400);

		setupComponet(new JLabel("退货号码："), 0, 0, 1, 0, false);
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
		ltel1.setPreferredSize(new Dimension(80,21));
		setupComponet(ltel1, 5, 0, 1, 0, true);

		setupComponet(new JLabel("结算方式："), 0, 1, 1, 0, false);
		jsfs.addItem("现金");
		jsfs.addItem("支付宝");
		jsfs.addItem("微信");
		jsfs.addItem("银行转账");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);

		setupComponet(new JLabel("退货时间："), 2, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 3, 1, 1, 1, true);

		setupComponet(new JLabel("经手人："), 4, 1, 1, 0, false);
		setupComponet(jsr, 5, 1, 1, 1, true);

		sp = new JComboBox();
		setupComponet(new JLabel("所属商品："), 0, 2, 1, 0, false);
		setupComponet(sp, 1, 2, 100, 1, true);
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KuCun info = (KuCun) sp.getSelectedItem();
				// 如果选择有效就更新表格
				if (info != null && info.getId() != null) {
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
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 初始化号码
//				initPiaoHao();
//				// 结束表格中没有编写的单元
//				stopTableCellEditing();
//				// 如果表格中还模糊查询空行，就不再添加新行
//				for (int i = 0; i < table.getRowCount(); i++) {
//					TbKucun info = (TbKucun) table.getValueAt(i, 0);
//					if (info == null || info.getId().isEmpty())
//						return;
//				}
//				DefaultTableModel model = (DefaultTableModel) table.getModel();
//				model.addRow(new Vector());
//				initSpBox();
				int row = table.getSelectedRow();
				if(row >= 0){
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(row);
					int rows = table.getRowCount();
					int count = 0;
					double money = 0.0;
					// 计算品种数量
//					TbSpinfo column = null;
//					if (rows > 0)
//						column = (TbSpinfo) table.getValueAt(rows - 1, 0);
//					if (rows > 0 && (column == null || column.getId().isEmpty()))
//						rows--;
					// 计算货品总数和金额
					for (int i = 0; i < rows; i++) {
						String column7 = table.getValueAt(i, 7).toString();
						String column6 = table.getValueAt(i, 6).toString();
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
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "请选择要删除的一行");
				}
			}
		});
		setupComponet(tjButton, 4, 5, 1, 1, false);

		// 单击入库按钮保存进货信息
		JButton rkButton = new JButton("退货");
		rkButton.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "请填写经手人");
					return;
				}
				if (bzStr == null || bzStr.isEmpty()) {
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "填写备注");
					return;
				}
				if (table.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "填加退货商品");
					return;
				}
				RukuTuihuoMain rkthMain = new RukuTuihuoMain(id, pzsStr, jeStr, bzStr,
						gysName, rkDate, czyStr, jsrStr, jsfsStr);
				Set<RukuTuihuoDetail> set = rkthMain.getRkthDetails();
				int rows = table.getRowCount();
				for (int i = 0; i < rows; i++) {
					//TbKucun kucun = (TbKucun) table.getValueAt(i, 0);
					String djStr = table.getValueAt(i, 6).toString();
					String slStr = table.getValueAt(i, 7).toString();
					Double dj = Double.valueOf(djStr);
					Integer sl = Integer.valueOf(slStr);
					RukuTuihuoDetail detail = new RukuTuihuoDetail();
					detail.setSpid(table.getValueAt(i, 1).toString());
					detail.setRkthMain(rkthMain.getRkthId());
					detail.setDj(dj);
					detail.setSl(sl);
					set.add(detail);
				}
				boolean rs = ShuJuKu.insertRkthInfo(rkthMain);
				if (rs) {
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "退货完成");
					DefaultTableModel dftm = new DefaultTableModel();
					table.setModel(dftm);
					initTable();
					pzs.setText("0");
					hpzs.setText("0");
					hjje.setText("0");
					initPiaoHao();
				}
			}
		});
		setupComponet(rkButton, 5, 5, 1, 1, false);
		// 添加窗体监听器，完成初始化
		addInternalFrameListener(new initTasks());
	}
	// 初始化表格
	private void initTable() {
		String[] columnNames = {"商品名称", "商品编号", "品牌", "生产企业", "规格", "包装", "单价",
				"数量"};
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
		ResultSet set = ShuJuKu.query("select * from KuCun where "
				+ "id in(select id from ShangPingXingXi where gysName='"
				+ gys.getSelectedItem() + "')");
		sp.removeAllItems();
//		sp.addItem(new TbKucun());
//		for (int i = 0; table != null && i < table.getRowCount(); i++) {
//			TbKucun tmpInfo = (TbKucun) table.getValueAt(i, 0);
//			if (tmpInfo != null && tmpInfo.getId() != null)
//				list.add(tmpInfo.getId());
//		}
		try {
			while (set.next()) {
				KuCun kucun = new KuCun();
				kucun.setId(set.getString("id").trim());
				// 如果表格中以存在同样商品，商品下拉框中就不再模糊查询该商品
				if (list.contains(kucun.getId()))
					continue;
				kucun.setSpname(set.getString("spname").trim());
				kucun.setPp(set.getString("pinpai").trim());
				kucun.setGysdw(set.getString("Gysdw").trim());
				kucun.setScjy(set.getString("scqy").trim());
				kucun.setGg(set.getString("gg").trim());
				kucun.setBz(set.getString("bz").trim());
				kucun.setDj(Double.valueOf(set.getString("dj").trim()));
				kucun.setKcsl(Integer.valueOf(set.getString("kcsl").trim()));
				sp.addItem(kucun);
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
//			TbKucun column = null;
//			if (rows > 0)
//				column = (TbKucun) table.getValueAt(rows - 1, 0);
//			if (rows > 0 && (column == null || column.getId().isEmpty()))
//				rows--;
			// 计算货品总数和金额
			for (int i = 0; i < rows; i++) {
				String column7 = table.getValueAt(i, 7).toString();
				String column6 = table.getValueAt(i, 6).toString();
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer
						.parseInt(column7);
				Double c6 = (column6 == null || column6.isEmpty()) ? 0 : Double
						.valueOf(column6);
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
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(jhsjDate.getTime());
		String maxId = ShuJuKu.getRkthMainMaxId(date);
		piaoHao.setText(maxId);
	}
	// 根据商品下拉框的选择，更新表格当前行的内容
	private synchronized void updateTable() {
//		TbKucun kucun = (TbKucun) sp.getSelectedItem();
//		int row = table.getSelectedRow();
//		if (row >= 0 && kucun != null) {
//			table.setValueAt(kucun.getId(), row, 1);
//			table.setValueAt(kucun.getPp(), row, 2);
//			table.setValueAt(kucun.getScjy(), row, 3);
//			table.setValueAt(kucun.getGg(), row, 4);
//			table.setValueAt(kucun.getBz(), row, 5);
//			table.setValueAt(kucun.getDj().toString(), row, 6);
//			table.setValueAt(kucun.getKcsl().toString(), row, 7);
//			table.editCellAt(row, 7);
//		}
		KuCun kucun = (KuCun) sp.getSelectedItem();
		
		//检查表格中是否已经添加该商品
		for(int i=0; i<table.getRowCount();i++){
			String spidString = table.getValueAt(i, 1).toString();
			if(spidString.equals(kucun.getId())){
				return;
			}
		}
		if(kucun != null){
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Vector tmpVector = new Vector();
			tmpVector.add(kucun.getSpname());
			tmpVector.add(kucun.getId());
			tmpVector.add(kucun.getPp());
			tmpVector.add(kucun.getScjy());
			tmpVector.add(kucun.getGg());
			tmpVector.add(kucun.getBz());
			tmpVector.add(kucun.getDj());
			tmpVector.add(kucun.getKcsl());
			model.addRow(tmpVector);;
			table.editCellAt(model.getRowCount()-1, 7);
		}
	}
	// 清除空行
	private synchronized void clearEmptyRow() {
//		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
//		for (int i = 0; i < table.getRowCount(); i++) {
//			TbKucun kucun = (TbKucun) table.getValueAt(i, 0);
//			if (kucun == null || kucun.getId() == null
//					|| kucun.getId().isEmpty()) {
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
