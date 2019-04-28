package model;
public class KuCun  implements java.io.Serializable {
     private String id;
     private String spname;
     private String Gysdw;
     private String pinpai;
     private String gg;
     private String bz;
     private String dw;
     private Double dj;
     private Integer kcsl;
    public KuCun() {
    }
    public KuCun(String id) {
        this.id = id;
    }
    public KuCun(String id, String spname, String Gysdw, String pinpai, String gg, String bz, String dw, Double dj, Integer kcsl) {
        this.id = id;
        this.spname = spname;
        this.Gysdw = Gysdw;
        this.pinpai = pinpai;
        this.gg = gg;
        this.bz = bz;
        this.dw = dw;
        this.dj = dj;
        this.kcsl = kcsl;
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
    public String getgysdw() {
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
    public String getScjy() {
        return this.dw;
    }
    public void setScjy(String dw) {
        this.dw = dw;
    }
    public Double getDj() {
        return this.dj;
    }
    public void setDj(Double dj) {
        this.dj = dj;
    }
    public Integer getKcsl() {
        return this.kcsl;
    }
    public void setKcsl(Integer kcsl) {
        this.kcsl = kcsl;
    }
	public String toString() {
		return getSpname();
	}
}