package first.denglu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//�ṩ������awt��������ĸ����¼��Ľӿں���
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;//�ı���
import javax.swing.WindowConstants;
//�ṩ�������������������Щ���������ƽ̨�ϵĹ�����ʽ��ͬ
import model.Userlist;
import first.main.MainFrame;
import first.shujuku.ShuJuKu;
public class DengLu extends JFrame {
	private JLabel userLabel;
	private JLabel passLabel;
	private JButton exit;
	private JButton login;
	private static Userlist user;
	public DengLu() {
		setTitle("�ڳ��������۹���ϵͳ");
		final JPanel panel = new DengLuJieMian();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		userLabel = new JLabel();
		userLabel.setText("�û�����");
		userLabel.setBounds(100, 135, 200, 18);//���Ͻ����ꣻ��ȣ��߶�
		panel.add(userLabel);
		final JTextField userName = new JTextField();//final��ֹ�ñ������ı�
		userName.setBounds(150, 135, 200, 18);
		panel.add(userName);
		passLabel = new JLabel();
		passLabel.setText("��  �룺");
		passLabel.setBounds(100, 165, 200, 18);
		panel.add(passLabel);
		final JPasswordField userPassword = new JPasswordField();
		userPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		userPassword.setBounds(150, 165, 200, 18);
		panel.add(userPassword);
		login = new JButton();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				user = ShuJuKu.getUser(userName.getText(), userPassword.getText());
				//user = new TbUserlist();
				//user.setName("admin");
				//user.setUsername("llq");
				if (user.getUsername() == null || user.getName() == null) {
					userName.setText(null);
					userPassword.setText(null);
					return;
				}
				setVisible(false);
				new MainFrame();
			}
		});
		login.setText("��¼");
		login.setBounds(180, 195, 60, 18);
		panel.add(login);
		exit = new JButton();
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setText("�˳�");
		exit.setBounds(260, 195, 60, 18);
		panel.add(exit);
		setVisible(true);//ͼ�ν�������Ϊ�ɼ�
		setResizable(false);//���ɵĴ����û����ɸı�
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	public static Userlist getUser() {
		return user;
	}
	public static void setUser(Userlist user) {
		DengLu.user = user;
	}
}
