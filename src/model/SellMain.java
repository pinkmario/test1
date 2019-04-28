package model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public class SellMain implements java.io.Serializable {
	private String sellId;
	private String pzs;
	private String je;
	private String bz;
	private String khname;
	private String xsdate;
	private String czy;
	private String jsr;
	private String jsfs;
	private Set tbSellDetails = new HashSet(0);
	public SellMain() {
	}
	public SellMain(String sellId, String pzs, String je, String bz,
			String khname, String xsdate, String czy, String jsr, String jsfs) {
		this.sellId = sellId;
		this.pzs = pzs;
		this.je = je;
		this.bz = bz;
		this.khname = khname;
		this.xsdate = xsdate;
		this.czy = czy;
		this.jsr = jsr;
		this.jsfs = jsfs;
		this.tbSellDetails = tbSellDetails;
	}
	public String getSellId() {
		return this.sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getPzs() {
		return this.pzs;
	}

	public void setPzs(String pzs) {
		this.pzs = pzs;
	}

	public String getJe() {
		return this.je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getKhname() {
		return this.khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public String getXsdate() {
		return this.xsdate;
	}

	public void setXsdate(String xsdate) {
		this.xsdate = xsdate;
	}

	public String getCzy() {
		return this.czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsfs() {
		return this.jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public Set getTbSellDetails() {
		return this.tbSellDetails;
	}

	public void setTbSellDetails(Set tbSellDetails) {
		this.tbSellDetails = tbSellDetails;
	}
}