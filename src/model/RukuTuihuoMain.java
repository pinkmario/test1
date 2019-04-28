package model;
import java.util.HashSet;
import java.util.Set;
public class RukuTuihuoMain implements java.io.Serializable {
	private String rkthId;
	private String pzs;
	private String je;
	private String bz;
	private String gysname;
	private String rtdate;
	private String czy;
	private String jsr;
	private String jsfs;
	private Set rkthDetails = new HashSet(0);
	public RukuTuihuoMain() {
	}
	public RukuTuihuoMain(String rkthId, String pzs, String je, String bz,
			String gysname, String rtdate, String czy, String jsr, String jsfs) {
		this.rkthId=rkthId;
		this.pzs = pzs;
		this.je = je;
		this.bz = bz;
		this.gysname = gysname;
		this.rtdate = rtdate;
		this.czy = czy;
		this.jsr = jsr;
		this.jsfs = jsfs;
	}
	public String getRkthId() {
		return this.rkthId;
	}

	public void setRkthId(String rkthId) {
		this.rkthId = rkthId;
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

	public String getGysname() {
		return this.gysname;
	}

	public void setGysname(String gysname) {
		this.gysname = gysname;
	}

	public String getRtdate() {
		return this.rtdate;
	}

	public void setRtdate(String rtdate) {
		this.rtdate = rtdate;
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

	public Set getRkthDetails() {
		return this.rkthDetails;
	}

	public void setTbRkthDetails(Set tbRkthDetails) {
		this.rkthDetails = tbRkthDetails;
	}
}