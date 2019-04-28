package internalFrame;
import first.shujuku.ShuJuKu;
import internalFrame.guanli.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.GongYingShangGL;
public class GongYingShangChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionOperation;
	private JComboBox conditionName;
	public GongYingShangChaXun() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ӧ����Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(60, 100, 750, 375);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{"��Ӧ��ID", "��Ӧ��ȫ��", "��Ӧ�̵�λ", "��������",
				"��ַ", "��ϵ��1", "��ϵ��2", "��ϵ�绰1", "��ϵ�绰2", "��������", "�����˻�"};
		dftm.setColumnIdentifiers(tableHeads);

		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"��Ӧ��ȫ��",
				"��Ӧ�̵�λ"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);

		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"��׼��ѯ",
				"ģ����ѯ"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("��ѯ");

		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = ShuJuKu.getGysInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		GongYingShangGL gysInfo;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(1));
			gysInfo = ShuJuKu.getGysInfo(item);
			Vector rowData = new Vector();
			rowData.add(gysInfo.getId());
			rowData.add(gysInfo.getName());
			rowData.add(gysInfo.getGysdw());
			rowData.add(gysInfo.getyouxiang());
			rowData.add(gysInfo.getAddress());
			rowData.add(gysInfo.getLname1());
			rowData.add(gysInfo.getlname2());
			rowData.add(gysInfo.getLtel1());
			rowData.add(gysInfo.getLtel2());
			rowData.add(gysInfo.getYh());
			rowData.add(gysInfo.gethao());
			dftm.addRow(rowData);
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
	private final class queryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private queryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String conName, conOperation, content;
			List list = null;
			conName = conditionName.getSelectedItem().toString().trim();
			conOperation = conditionOperation.getSelectedItem().toString()
					.trim();
			content = conditionContent.getText().trim();
			String sql = "select * from GongYingShangXingXi where ";
			if (conName.equals("��Ӧ��ȫ��")) {
				if (conOperation.equals("��׼��ѯ"))
					list = ShuJuKu.findForList(sql + "name='" + content + "'");
				else
					list = ShuJuKu.findForList(sql + "name like '%" + content
							+ "%'");
			} else {
				if (conOperation.equals("��׼��ѯ"))
					list = ShuJuKu.findForList(sql + "Gysdw='" + content + "'");
				else
					list = ShuJuKu.findForList(sql + "Gysdw like '%" + content + "%'");
			}
			updateTable(list, dftm);
		}
	}
}
