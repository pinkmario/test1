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
	private Userlist user = DengLu.getUser(); 			// ��¼�û���Ϣ
	private final JTextField jhsj = new JTextField(); 	// ����ʱ��
	private final JTextField jsr = new JTextField(); 	// ������
	private final JComboBox jsfs = new JComboBox(); 	// ���㷽ʽ
	private final JTextField ltel1 = new JTextField(); 	// ��ϵ�绰
	private final JComboBox gys = new JComboBox(); 		// ��Ӧ��
	private final JTextField piaoHao = new JTextField();// ����
	private final JTextField pzs = new JTextField("0"); // Ʒ������
	private final JTextField hpzs = new JTextField("0");// ��Ʒ����
	private final JTextField hjje = new JTextField("0");// �ϼƽ��
	private final JTextField bz = new JTextField(); 	// ��ע
	private final JTextField czy = new JTextField(user.getName());// ����Ա
	private Date jhsjDate;
	private JComboBox sp;
	public JinHuoDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("������");
		setBounds(50, 50, 800, 400);

		setupComponet(new JLabel("�������룺"), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 140, true);

		setupComponet(new JLabel("��Ӧ�̣�"), 2, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		// ��Ӧ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 3, 0, 1, 1, true);

		setupComponet(new JLabel("��ϵ�绰��"), 4, 0, 1, 0, false);
		ltel1.setFocusable(false);
		setupComponet(ltel1, 5, 0, 1, 80, true);

		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		jsfs.addItem("�ֽ�");
		jsfs.addItem("֧����");
		jsfs.addItem("΢��");
		jsfs.addItem("����ת��");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);

		setupComponet(new JLabel("����ʱ�䣺"), 2, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 3, 1, 1, 1, true);

		setupComponet(new JLabel("�����ˣ�"), 4, 1, 1, 0, false);
		setupComponet(jsr, 5, 1, 1, 1, true);

		sp = new JComboBox();
		setupComponet(new JLabel("������Ʒ��"), 0, 2, 1, 0, false);
		setupComponet(sp, 1, 2, 100, 1, true);
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShangPinXX info = (ShangPinXX) sp.getSelectedItem();
				// ���ѡ����Ч�͸��±��
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
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 3, 6, 1, true);

		setupComponet(new JLabel("Ʒ��������"), 0, 4, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 4, 1, 1, true);

		setupComponet(new JLabel("��Ʒ������"), 2, 4, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 4, 1, 1, true);

		setupComponet(new JLabel("�ϼƽ�"), 4, 4, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 4, 1, 1, true);

		setupComponet(new JLabel("��ע��"), 0, 5, 1, 0, false);
		setupComponet(bz, 1, 5, 1, 1, true);

		setupComponet(new JLabel("������Ա��"), 2, 5, 1, 0, false);
		czy.setFocusable(false);
		setupComponet(czy, 3, 5, 1, 1, true);

		// ������Ӱ�ť�ڱ��������µ�һ��
		JButton tjButton = new JButton("ɾ��");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 4, 5, 1, 1, false);

		// ������ⰴť���������Ϣ
		JButton rkButton = new JButton("���");
		rkButton.addActionListener(new RkActionListener());
		setupComponet(rkButton, 5, 5, 1, 1, false);
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
	}
	// ��ʼ�����
	private void initTable() {
		String[] columnNames = {"��Ʒ����", "��Ʒ���", "Ʒ��", "������ҵ", "���", "��װ", "����",
				"����", "�ʱ�ʱ��", "����ӿ�"};
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		final DefaultCellEditor editor = new DefaultCellEditor(sp);
		editor.setClickCountToStart(2);
		column.setCellEditor(editor);
	}
	// ��ʼ����Ʒ����ѡ���
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
				// ���������Դ���ͬ����Ʒ����Ʒ�������оͲ���ģ����ѯ����Ʒ
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
	// �������λ�ò���ӵ�������
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
	// ��Ӧ��ѡ��ʱ������ϵ�绰�ֶ�
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		GongYingShangGL gysInfo = ShuJuKu.getGysInfo(item);
		ltel1.setText(gysInfo.getLtel1());
		initSpBox();
	}
	class RkActionListener implements ActionListener {	// ��ⰴť���¼�������
		public void actionPerformed(ActionEvent e) {
			// ���������û�б�д�ĵ�Ԫ
			stopTableCellEditing();
			// �������
			clearEmptyRow();
			String hpzsStr = hpzs.getText(); // ��Ʒ����
			String pzsStr = pzs.getText(); // Ʒ����
			String jeStr = hjje.getText(); // �ϼƽ��
			String jsfsStr = jsfs.getSelectedItem().toString(); // ���㷽ʽ
			String jsrStr = jsr.getText().trim(); // ������
			String czyStr = czy.getText(); // ����Ա
			String rkDate = jhsjDate.toLocaleString(); // ���ʱ��
			String bzStr = bz.getText().trim(); // ��ע
			String id = piaoHao.getText(); // ����
			String gysName = gys.getSelectedItem().toString();// ��Ӧ������
			if (jsrStr == null || jsrStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "����д������");
				return;
			}
			if (bzStr == null || bzStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "��д��ע");
				return;
			}
			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "��������Ʒ");
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
				JOptionPane.showMessageDialog(JinHuoDan.this, "������");
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
	class TjActionListener implements ActionListener {	// ɾ����ť���¼�������
		public void actionPerformed(ActionEvent e) {
			/*
			// ��ʼ������
			initPiaoHao();
			// ���������û�б�д�ĵ�Ԫ
			stopTableCellEditing();
			// �������л�ģ����ѯ���У������������
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
				// ����Ʒ������
//				TbSpinfo column = null;
//				if (rows > 0)
//					column = (TbSpinfo) table.getValueAt(rows - 1, 0);
//				if (rows > 0 && (column == null || column.getId().isEmpty()))
//					rows--;
				// �����Ʒ�����ͽ��
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
				JOptionPane.showMessageDialog(JinHuoDan.this, "��ѡ��Ҫɾ����һ��");
			}
		}
	}
	// ���¼��м���Ʒ����������Ʒ�������ϼƽ��
	private final class computeInfo implements ContainerListener {
		public void componentRemoved(ContainerEvent e) {
			// �������
			clearEmptyRow();
			// �������
			int rows = table.getRowCount();
			int count = 0;
			double money = 0.0;
			// ����Ʒ������
//			TbSpinfo column = null;
//			if (rows > 0)
//				column = (TbSpinfo) table.getValueAt(rows - 1, 0);
//			if (rows > 0 && (column == null || column.getId().isEmpty()))
//				rows--;
			// �����Ʒ�����ͽ��
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
	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
			initPiaoHao();
			initSpBox();
		}
		private void initGysField() {// ��ʼ����Ӧ���ֶ�
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
		private void initTimeField() {// ��������ʱ���߳�
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
	// ��ʼ�������ı���ķ���
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(jhsjDate.getTime());
		String maxId = ShuJuKu.getRuKuMainMaxId(date);
		piaoHao.setText(maxId);
	}
	// ������Ʒ�������ѡ�񣬸��±��ǰ�е�����
	private synchronized void updateTable() {
		ShangPinXX spinfo = (ShangPinXX) sp.getSelectedItem();
		
		//��������Ƿ��Ѿ���Ӹ���Ʒ
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
	// �������
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
	// ֹͣ���Ԫ�ı༭
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
