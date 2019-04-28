package model;
public class RukuTuihuoDetail implements java.io.Serializable {
	private Integer id;
	private String rkthMain;
	private String spid;
	private Double dj;
	private Integer sl;
	public RukuTuihuoDetail() {
	}
	public RukuTuihuoDetail(String tbRkthMain, String spid, Double dj, Integer sl) {
		this.rkthMain = tbRkthMain;
		this.spid = spid;
		this.dj = dj;
		this.sl = sl;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRkthMain() {
		return this.rkthMain;
	}

	public void setRkthMain(String tbRkthMain) {
		this.rkthMain = tbRkthMain;
	}

	public String getSpid() {
		return this.spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public Double getDj() {
		return this.dj;
	}

	public void setDj(Double dj) {
		this.dj = dj;
	}

	public Integer getSl() {
		return this.sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}
}