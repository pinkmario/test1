package first.shujuku;
import internalFrame.guanli.Item;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.GongYingShangGL;
import model.KehuXX;
import model.KuCun;
import model.RukuTuihuoDetail;
import model.RukuTuihuoMain;
import model.RukuDetail;
import model.RukuMain;
import model.SellDetail;
import model.SellMain;
import model.ShangPinXX;
import model.Userlist;
import model.XiaoshouTuiHuoDetail;
import model.XiaoshouTuiHuoMain;
public class ShuJuKu {
	private static String dbUrl="jdbc:mysql://localhost:3306/xiao_shou?characterEncoding=utf8"; // 数据库连接地址
	private static String dbUserName="root"; // 用户名
	private static String dbPassword="root"; // 密码
	private static String jdbcName="com.mysql.jdbc.Driver"; // 驱动名称
	protected static String second = null;
	public static Connection conn = null;
	static {
		try {
			if (conn == null) {
				Class.forName(jdbcName);
				conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	private ShuJuKu() {
	}
	// 读取所有客户信息
	public static List getKhInfos() {
		List list = findForList("select id,khname from KeHuXingXi");
		return list;
	}
	// 读取所有供应商信息
	public static List getGysInfos() {
		List list = findForList("select id,name from GongYingShangXingXi");
		return list;
	}
	// 读取客户信息
	public static KehuXX getKhInfo(Item item) {
		String where = "khname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		KehuXX info = new KehuXX();
		ResultSet set = findForResultSet("select * from KeHuXingXi where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setKhname(set.getString("khname").trim());
				info.setKhdw(set.getString("khdw").trim());
				info.setAddress(set.getString("address").trim());
				info.setyouxiang(set.getString("youxiang").trim());
				info.setlname2(set.getString("lname2").trim());
				info.setHao(set.getString("hao").trim());
				info.setLian(set.getString("ltel1").trim());
				info.setLtel2(set.getString("ltel2").trim());
				info.setMail(set.getString("khbeizhu").trim());
				info.setLname1(set.getString("lname1").trim());
				info.setyinhang(set.getString("yinhang").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	// 读取指定供应商信息
	public static GongYingShangGL getGysInfo(Item item) {
		String where = "name='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		GongYingShangGL info = new GongYingShangGL();
		ResultSet set = findForResultSet("select * from GongYingShangXingXi where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setAddress(set.getString("address").trim());
				info.setyouxiang(set.getString("youxiang").trim());
				info.setlname2(set.getString("lname2").trim());
				info.setGysdw(set.getString("Gysdw").trim());
				info.setLtel1(set.getString("ltel1").trim());
				info.setLtel2(set.getString("ltel2").trim());
				info.sethao(set.getString("hao").trim());
				info.setName(set.getString("name").trim());
				info.setLname1(set.getString("lname1").trim());
				info.setYh(set.getString("yh").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	// 读取用户
	public static Userlist getUser(String name, String password) {
		Userlist user = new Userlist();
		ResultSet rs = findForResultSet("select * from YongHuBiao where username='"
				+ name + "'");
		try {
			if (rs.next()) {
				user.setUsername(name);
				user.setPass(rs.getString("pass"));
				if (user.getPass().equals(password)) {
					user.setName(rs.getString("name"));
					user.setQuan(rs.getString("quan"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	// 执行指定查询
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}
	// 执行删除
	public static int delete(String sql) {
		return update(sql);
	}
	// 添加客户信息的方法
	public static boolean addKeHu(KehuXX khinfo) {
		if (khinfo == null)
			return false;
		return insert("insert KeHuXingXi values('" + khinfo.getId() + "','"
				+ khinfo.getKhname() + "','" + khinfo.getKhdw() + "','"
				+ khinfo.getAddress() + "','" + khinfo.getyouxiang() + "','"
				+ khinfo.getLname1() + "','" + khinfo.getlname2() + "','"
				+ khinfo.getLtel1() + "','" + khinfo.getLtel2() + "','"
				+ khinfo.getMail() + "','" + khinfo.getyinhang() + "','"
				+ khinfo.getHao() + "')");
	}
	// 修改客户信息的方法
	public static int updateKeHu(KehuXX khinfo) {
		return update("update KeHuXingXi set khdw='" + khinfo.getKhdw()
				+ "',address='" + khinfo.getAddress() + "',youxiang='"
				+ khinfo.getyouxiang() + "',lname1='" + khinfo.getLname1() + "',lname2='"
				+ khinfo.getlname2() + "',ltel1='" + khinfo.getLtel1() + "',ltel2='"
				+ khinfo.getLtel2() + "',khbeizhu='" + khinfo.getMail()
				+ "',yinhang='" + khinfo.getyinhang() + "',hao='"
				+ khinfo.getHao() + "' where id='" + khinfo.getId() + "'");
	}
	// 修改库存的方法
	public static int updateKucunDj(KuCun kcInfo) {
		return update("update KuCun set dj=" + kcInfo.getDj()
				+ " where id='" + kcInfo.getId() + "'");
	}
	// 修改供应商信息的方法
	public static int updateGys(GongYingShangGL gysInfo) {
		return update("update GongYingShangXingXi set Gysdw='" + gysInfo.getGysdw()
				+ "',address='" + gysInfo.getAddress() + "',youxiang='"
				+ gysInfo.getyouxiang() + "',lname1='" + gysInfo.getLname1()
				+ "',lname2='" + gysInfo.getlname2() + "',ltel1='" + gysInfo.getLtel1()
				+ "',ltel2='" + gysInfo.getLtel2() + "',hao='"
				+ gysInfo.gethao() + "',yh='" + gysInfo.getYh()
				+ "' where id='" + gysInfo.getId() + "'");
	}
	// 添加供应商信息的方法
	public static boolean addGys(GongYingShangGL gysInfo) {
		if (gysInfo == null)
			return false;
		return insert("insert GongYingShangXingXi values('" + gysInfo.getId() + "','"
				+ gysInfo.getName() + "','" + gysInfo.getGysdw() + "','"
				+ gysInfo.getAddress() + "','" + gysInfo.getyouxiang() + "','"
				+ gysInfo.getLname1() + "','" + gysInfo.getlname2() + "','"
				+ gysInfo.getLtel1() + "','" + gysInfo.getLtel2() + "','"
				+ gysInfo.gethao() + "','" + gysInfo.getYh() + "')");
	}
	// 添加商品
	public static boolean addSp(ShangPinXX spInfo) {
		if (spInfo == null)
			return false;
		return insert("insert ShangPingXingXi values('" + spInfo.getId() + "','"
				+ spInfo.getSpname() + "','" + spInfo.geGysdw() + "','"
				+ spInfo.getPp() + "','" + spInfo.getScjy() + "','"
				+ spInfo.getGg() + "','" + spInfo.getBz() + "','"
				+ spInfo.getPh() + "','" + spInfo.getScjk() + "','"
				+ spInfo.getZhuyi() + "','" + spInfo.getGysname() + "')");
	}
	// 更新商品
	public static int updateSp(ShangPinXX spInfo) {
		return update("update ShangPingXingXi set Gysdw='" + spInfo.geGysdw() + "',pinpai='"
				+ spInfo.getPp() + "',scqy='" + spInfo.getScjy() + "',gg='"
				+ spInfo.getGg() + "',bz='" + spInfo.getBz() + "',zhibao='"
				+ spInfo.getPh() + "',Scjk='" + spInfo.getScjk() + "',zhuyi='"
				+ spInfo.getZhuyi() + "',gysname='" + spInfo.getGysname()
				+ "' where id='" + spInfo.getId() + "'");
	}
	// 读取商品信息
	public static ShangPinXX getSpInfo(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from ShangPingXingXi where "
				+ where);
		ShangPinXX spInfo = new ShangPinXX();
		try {
			if (rs.next()) {
				spInfo.setId(rs.getString("id").trim());
				spInfo.setBz(rs.getString("bz").trim());
				spInfo.setPp(rs.getString("pinpai").trim());
				spInfo.setScjy(rs.getString("scqy").trim());
				spInfo.setGg(rs.getString("gg").trim());
				spInfo.setGysname(rs.getString("gysname").trim());
				spInfo.setGysdw(rs.getString("Gysdw").trim());
				spInfo.setZhuyi(rs.getString("zhuyi").trim());
				spInfo.setPh(rs.getString("zhibao").trim());
				spInfo.setScjk(rs.getString("Scjk").trim());
				spInfo.setSpname(rs.getString("spname").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spInfo;
	}
	// 获取所有商品信息
	public static List getSpInfos() {
		List list = findForList("select * from ShangPingXingXi");
		return list;
	}
	// 获取库存商品信息
	public static KuCun getKucun(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from KuCun where " + where);
		KuCun kucun = new KuCun();
		try {
			if (rs.next()) {
				kucun.setId(rs.getString("id"));
				kucun.setSpname(rs.getString("spname"));
				kucun.setGysdw(rs.getString("Gysdw"));
				kucun.setBz(rs.getString("bz"));
				kucun.setPp(rs.getString("pinpai"));
				kucun.setDj(rs.getDouble("dj"));
				kucun.setScjy(rs.getString("scqy"));
				kucun.setGg(rs.getString("gg"));
				kucun.setKcsl(rs.getInt("kcsl"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kucun;
	}
	// 获取入库单的最大ID，即最大入库号码
	public static String getRuKuMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "RuKu_main", "RK", "rkid");
	}
	// 在事务中添加入库信息
	public static boolean insertRukuInfo(RukuMain ruMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库主表记录
			insert("insert into RuKu_main values('" + ruMain.getRkId()
					+ "','" + ruMain.getPzs() + "'," + ruMain.getJe() + ",'"
					+ ruMain.getBz() + "','" + ruMain.getGysname() + "','"
					+ ruMain.getRkdate() + "','" + ruMain.getCzy() + "','"
					+ ruMain.getJsr() + "','" + ruMain.getJsfs() + "')");
			Set<RukuDetail> rkDetails = ruMain.getTabRukuDetails();
			for (Iterator<RukuDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				RukuDetail details = iter.next();
				// 添加入库次表记录
				insert("insert into RuKu_detail values('" + ruMain.getRkId()
						+ "','" + details.getTabSpinfo() + "',"
						+ details.getDj() + "," + details.getSl() + ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getTabSpinfo());
				ShangPinXX spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					KuCun kucun = getKucun(item);
					if (kucun.getId() == null || kucun.getId().isEmpty()) {
						insert("insert into KuCun values('" + spInfo.getId()
								+ "','" + spInfo.getSpname() + "','"
								+ spInfo.geGysdw() + "','" + spInfo.getPp()
								+ "','" + spInfo.getGg() + "','"
								+ spInfo.getBz() + "','" + spInfo.getScjy()
								+ "'," + details.getDj() + ","
								+ details.getSl() + ")");
					} else {
						int sl = kucun.getKcsl() + details.getSl();
						update("update KuCun set kcsl=" + sl + ",dj="
								+ details.getDj() + " where id='"
								+ kucun.getId() + "'");
					}
					//添加销售信息
					String sqlString = "insert into RuKu_view values('"
							+ ruMain.getRkId() + "','" + spInfo.getId()
							+ "','" + spInfo.getSpname() + "','" + spInfo.getGg()
							+ "'," + details.getDj() + "," + details.getSl() + 
							"," + (Double.valueOf(details.getDj()) * Integer.valueOf(details.getSl())) + ",'" + ruMain.getGysname() + "','" + 
							ruMain.getRkdate() + "','" + ruMain.getCzy() + "','" + 
							ruMain.getJsr() + "','" + ruMain.getJsfs() + "','" + ruMain.getBz()+ "'" +
							")";
					//System.out.println(sqlString);
					insert(sqlString);
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return true;
	}

	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 获取退货最大ID
	public static String getRkthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "RuKuTuiHuo_main", "RT", "rkthId");
	}
	// 在事务中添加入库退货信息
	public static boolean insertRkthInfo(RukuTuihuoMain rkthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库退货主表记录
			insert("insert into RuKuTuiHuo_main values('" + rkthMain.getRkthId()
					+ "','" + rkthMain.getPzs() + "'," + rkthMain.getJe()
					+ ",'" + rkthMain.getBz() + "','" + rkthMain.getGysname()
					+ "','" + rkthMain.getRtdate() + "','" + rkthMain.getCzy()
					+ "','" + rkthMain.getJsr() + "','" + rkthMain.getJsfs()
					+ "')");
			Set<RukuTuihuoDetail> rkDetails = rkthMain.getRkthDetails();
			for (Iterator<RukuTuihuoDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				RukuTuihuoDetail details = iter.next();
				// 添加入库次表记录
				insert("insert into RuKuTuiHuo_detail values('"
						+ rkthMain.getRkthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				ShangPinXX spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					KuCun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update KuCun set kcsl=" + sl + " where id='"
								+ kucun.getId() + "'");
					}
				}
				//添加销售信息
				String sqlString = "insert into RuKuTuiHuo_view values('"
						+ rkthMain.getRkthId() + "','" + spInfo.getId()
						+ "','" + spInfo.getSpname() + "','" + spInfo.getGg()
						+ "'," + details.getDj() + "," + details.getSl() + 
						"," + (Double.valueOf(details.getDj()) * Integer.valueOf(details.getSl())) + ",'" + rkthMain.getGysname() + "','" + 
						rkthMain.getRtdate() + "','" + rkthMain.getCzy() + "','" + 
						rkthMain.getJsr() + "','" + rkthMain.getJsfs() + "','"+rkthMain.getBz()+ "'" +
						")";
				//System.out.println(sqlString);
				insert(sqlString);
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	// 获取销售主表最大ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "sell_main", "XS", "sellID");
	}
	// 在事务中添加销售信息
	public static boolean insertSellInfo(SellMain sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售主表记录
			insert("insert into sell_main values('" + sellMain.getSellId()
					+ "','" + sellMain.getPzs() + "'," + sellMain.getJe()
					+ ",'" + sellMain.getBz() + "','" + sellMain.getKhname()
					+ "','" + sellMain.getXsdate() + "','" + sellMain.getCzy()
					+ "','" + sellMain.getJsr() + "','" + sellMain.getJsfs()
					+ "')");
			Set<SellDetail> rkDetails = sellMain.getTbSellDetails();
			for (Iterator<SellDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				SellDetail details = iter.next();
				// 添加销售次表记录
				insert("insert into sell_detail values('"
						+ sellMain.getSellId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				ShangPinXX spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					KuCun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update KuCun set kcsl=" + sl + " where id='"
								+ kucun.getId() + "'");
					}
				}
				//添加销售信息
				String sqlString = "insert into Sell_view values('"
						+ sellMain.getSellId() + "','" + details.getSpid()
						+ "','" + spInfo.getSpname() + "','" + spInfo.getGg()
						+ "'," + details.getDj() + "," + details.getSl() + 
						"," + (Double.valueOf(details.getDj()) * Integer.valueOf(details.getSl())) + ",'" + sellMain.getKhname() + "','" + 
						sellMain.getXsdate() + "','" + sellMain.getCzy() + "','" + 
						sellMain.getJsr() + "','" + sellMain.getJsfs() + "','" + sellMain.getBz()+"'" +
						")";
				//System.out.println(sqlString);
				insert(sqlString);
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	// 获取更类主表最大ID
	private static String getMainTypeTableMaxId(Date date, String table,
			String idChar, String idName) {
		String dateStr = date.toString().replace("-", "");
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table + " where "
				+ idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baseId = baseId == null ? "000" : baseId.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}
	public static String getXsthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "XiaoShouTuiHuo_main", "XT", "xsthID");
	}
	public static List getKucunInfos() {
		List list = findForList("select id,spname,dj,kcsl from KuCun");
		return list;
	}
	// 在事务中添加销售退货信息
	public static boolean insertXsthInfo(XiaoshouTuiHuoMain xsthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售退货主表记录
			insert("insert into XiaoShouTuiHuo_main values('" + xsthMain.getXsthId()
					+ "','" + xsthMain.getPzs() + "'," + xsthMain.getJe()
					+ ",'" + xsthMain.getBz() + "','" + xsthMain.getKhname()
					+ "','" + xsthMain.getThdate() + "','" + xsthMain.getCzy()
					+ "','" + xsthMain.getJsr() + "','" + xsthMain.getJsfs()
					+"')");
			Set<XiaoshouTuiHuoDetail> xsthDetails = xsthMain.getTbXsthDetails();
			for (Iterator<XiaoshouTuiHuoDetail> iter = xsthDetails.iterator(); iter
					.hasNext();) {
				XiaoshouTuiHuoDetail details = iter.next();
				// 添加销售退货次表记录
				insert("insert into XiaoShouTuiHuo_detail values('"
						+ xsthMain.getXsthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				ShangPinXX spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					KuCun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() + details.getSl();
						update("update KuCun set kcsl=" + sl + " where id='"
								+ kucun.getId() + "'");
					}
				}
				//添加销售信息
				String sqlString = "insert into XiaoShouTuiHuo_view values('"
						+ xsthMain.getXsthId() + "','" + details.getSpid()
						+ "','" + spInfo.getSpname() + "','" + spInfo.getGg()
						+ "'," + details.getDj() + "," + details.getSl() + 
						"," + (Double.valueOf(details.getDj()) * Integer.valueOf(details.getSl())) + ",'" + xsthMain.getKhname() + "','" + 
						xsthMain.getThdate() + "','" + xsthMain.getCzy() + "','" + 
						xsthMain.getJsr() + "','" + xsthMain.getJsfs() + "','" + xsthMain.getBz()+ "'" +
						")";
				//System.out.println(sqlString);
				insert(sqlString);
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	// 添加用户
	public static int addUser(Userlist ul) {
		return update("insert YongHuBiao values('" + ul.getUsername() + "','"
				+ ul.getName() + "','" + ul.getPass() + "','" + ul.getQuan()
				+ "')");
	}
	public static List getUsers() {
		List list = findForList("select * from YongHuBiao");
		return list;
	}
	// 修改用户方法
	public static int updateUser(Userlist user) {
		return update("update YongHuBiao set username='" + user.getUsername()
				+ "',name='" + user.getName() + "',pass='" + user.getPass()
				+ "',quan='" + user.getQuan() + "' where name='"
				+ user.getName() + "'");
	}
	// 获取用户对象的方法
	public static Userlist getUser(Item item) {
		String where = "username='" + item.getName() + "'";
		if (item.getId() != null)
			where = "name='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from YongHuBiao where "
				+ where);
		Userlist user = new Userlist();
		try {
			if (rs.next()) {
				user.setName(rs.getString("name").trim());
				user.setUsername(rs.getString("username").trim());
				user.setPass(rs.getString("pass").trim());
				user.setQuan(rs.getString("quan").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}