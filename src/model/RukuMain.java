package model;
import java.util.HashSet;
import java.util.Set;
public class RukuMain  implements java.io.Serializable {
	private String rkId;
	private String pzs;
	private String je;
	private String bz;
	private String gysname;
	private String rkdate;
	private String czy;
	private String jsr;
	private String jsfs;
	private Set<RukuDetail> tabRukuDetails = new HashSet<RukuDetail>(0);
    public RukuMain() {
    }
    public RukuMain(String rkId, String pzs, String je, String bz, String gysname, String rkdate, String czy, String jsr, String jsfs) {
        this.rkId = rkId;
        this.pzs = pzs;
        this.je = je;
        this.bz = bz;
        this.gysname = gysname;
        this.rkdate = rkdate;
        this.czy = czy;
        this.jsr = jsr;
        this.jsfs = jsfs;
    }
    public String getRkId() {
        return this.rkId;
    }
    public void setRkId(String rkId) {
        this.rkId = rkId;
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
    public void setBz(String sf) {
        this.bz = sf;
    }
    public String getGysname() {
        return this.gysname;
    }
    public void setGysname(String gysname) {
        this.gysname = gysname;
    }
    public String getRkdate() {
        return this.rkdate;
    }
    public void setRkdate(String rkdate) {
        this.rkdate = rkdate;
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
    public Set<RukuDetail> getTabRukuDetails() {
        return this.tabRukuDetails;
    }
    public void setTabRukuDetails(Set<RukuDetail> tabRukuDetails) {
        this.tabRukuDetails = tabRukuDetails;
    }
}