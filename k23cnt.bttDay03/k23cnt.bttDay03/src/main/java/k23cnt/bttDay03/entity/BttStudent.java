package k23cnt.bttDay03.entity;

public class BttStudent {
    long bttId;
    String bttName;
    int bttAge;
    boolean bttGender;
    String bttAddress;
    String bttPhone;
    String bttEmail;

    public BttStudent() {
    }

    public BttStudent(long bttId, String bttName, int bttAge, boolean bttGender, String bttAddress, String bttPhone, String bttEmail) {
        this.bttId = bttId;
        this.bttName = bttName;
        this.bttAge = bttAge;
        this.bttGender = bttGender;
        this.bttAddress = bttAddress;
        this.bttPhone = bttPhone;
        this.bttEmail = bttEmail;
    }

    public long getBttId() {
        return bttId;
    }

    public void setBttId(long bttId) { this.bttId = bttId; }

    public String getBttName() { return bttName; }

    public void setBttName(String bttName) {
        this.bttName = bttName;
    }

    public int getBttAge() {
        return bttAge;
    }

    public void setBttAge(int bttAge) {
        this.bttAge = bttAge;
    }

    public boolean isBttGender() {
        return bttGender;
    }

    public void setBttGender(boolean bttGender) {
        this.bttGender = bttGender;
    }

    public String getBttAddress() {
        return bttAddress;
    }

    public void setBttAddress(String bttAddress) {
        this.bttAddress = bttAddress;
    }

    public String getBttPhone() {
        return bttPhone;
    }

    public void setBttPhone(String bttPhone) {
        this.bttPhone = bttPhone;
    }

    public String getBttEmail() {
        return bttEmail;
    }

    public void setBttEmail(String bttEmail) {
        this.bttEmail = bttEmail;
    }
}
