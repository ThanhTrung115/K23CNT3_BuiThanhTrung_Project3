package k23cnt3.LabGuide03.service;

import k23cnt3.LabGuide03.entity.BttEmployee;

import java.util.List;

public interface BttEmployeeService {
    List<BttEmployee> getAllBttEmployee();
    BttEmployee getBttEmployeeById(String bttId);
    BttEmployee addBttEmployee(BttEmployee e);
    BttEmployee updateBttEmployee(String bttId, BttEmployee e);
    boolean deleteBttEmployee(String bttId);
}
