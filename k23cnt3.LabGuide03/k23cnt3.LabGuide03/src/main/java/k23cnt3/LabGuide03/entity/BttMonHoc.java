package k23cnt3.LabGuide03.entity;

public class BttMonHoc {
    private String bttMaMH;
    private String bttTenMH;
    private int bttSotinchi;

    public BttMonHoc() {}

    public BttMonHoc(String bttMaMH, String bttTenMH, int bttSotinchi) {
        this.bttMaMH = bttMaMH;
        this.bttTenMH = bttTenMH;
        this.bttSotinchi = bttSotinchi;
    }

    public String getBttMaMH() { return bttMaMH; }
    public void setBttMaMH(String bttMaMH) { this.bttMaMH = bttMaMH; }

    public String getBttTenMH() { return bttTenMH; }
    public void setBttTenMH(String bttTenMH) { this.bttTenMH = bttTenMH; }

    public int getBttSotinchi() { return bttSotinchi; }
    public void setBttSotinchi(int bttSotinchi) { this.bttSotinchi = bttSotinchi; }
}
