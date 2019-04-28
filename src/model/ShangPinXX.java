package model;
public class ShangPinXX implements java.io.Serializable {
	private String id;
	private String spname;
	private String Gysdw;
	private String pinpai;
	private String dw;
	private String gg;
	private String bz;
	private String zhibao;
	private String Scjk;
	private String zhuyi;
	private String gysname;
	public ShangPinXX() {
	}

	public ShangPinXX(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpname() {
		return this.spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String geGysdw() {
		return this.Gysdw;
	}

	public void setGysdw(String Gysdw) {
		this.Gysdw = Gysdw;
	}

	public String getPp() {
		return this.pinpai;
	}

	public void setPp(String pinpai) {
		this.pinpai = pinpai;
	}

	public String getScjy() {
		return this.dw;
	}

	public void setScjy(String dw) {
		this.dw = dw;
	}

	public String getGg() {
		return this.gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPh() {
		return this.zhibao;
	}

	public void setPh(String zhibao) {
		this.zhibao = zhibao;
	}

	public String getScjk() {
		return this.Scjk;
	}

	public void setScjk(String Scjk) {
		this.Scjk = Scjk;
	}

	public String getZhuyi() {
		return this.zhuyi;
	}

	public void setZhuyi(String zhuyi) {
		this.zhuyi = zhuyi;
	}

	public String getGysname() {
		return this.gysname;
	}

	public void setGysname(String gysname) {
		this.gysname = gysname;
	}

	public String toString() {
		return getSpname();
	}
	
}