package k23cnt3.LabGuide03.entity;

public class BttKhoa {
    private String bttMaKhoa;
    private String bttTenKhoa;

    public BttKhoa() {}

    public BttKhoa(String bttMaKhoa, String bttTenKhoa) {
        this.bttMaKhoa = bttMaKhoa;
        this.bttTenKhoa = bttTenKhoa;
    }

    public String getBttMaKhoa() { return bttMaKhoa; }
    public void setBttMaKhoa(String bttMaKhoa) { this.bttMaKhoa = bttMaKhoa; }

    public String getBttTenKhoa() { return bttTenKhoa; }
    public void setBttTenKhoa(String bttTenKhoa) { this.bttTenKhoa = bttTenKhoa; }
}
