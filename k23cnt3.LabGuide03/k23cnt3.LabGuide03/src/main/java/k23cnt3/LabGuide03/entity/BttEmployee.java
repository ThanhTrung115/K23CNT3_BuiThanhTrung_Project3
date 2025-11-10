package k23cnt3.LabGuide03.entity;

public class BttEmployee {
    private String bttId;
    private String bttFullName;
    private String bttGender;
    private int bttAge;
    private double bttSalary;

    public BttEmployee() {}

    public BttEmployee(String bttId, String bttFullName, String bttGender, int bttAge, double bttSalary) {
        this.bttId = bttId;
        this.bttFullName = bttFullName;
        this.bttGender = bttGender;
        this.bttAge = bttAge;
        this.bttSalary = bttSalary;
    }

    public String getBttId() { return bttId; }
    public void setBttId(String bttId) { this.bttId = bttId; }

    public String getBttFullName() { return bttFullName; }
    public void setBttFullName(String bttFullName) { this.bttFullName = bttFullName; }

    public String getBttGender() { return bttGender; }
    public void setBttGender(String bttGender) { this.bttGender = bttGender; }

    public int getBttAge() { return bttAge; }
    public void setBttAge(int bttAge) { this.bttAge = bttAge; }

    public double getBttSalary() { return bttSalary; }
    public void setBttSalary(double bttSalary) { this.bttSalary = bttSalary; }
}
