package k23cnt3.LabGuide03.service.impl;

import k23cnt3.LabGuide03.entity.BttEmployee;
import k23cnt3.LabGuide03.service.BttEmployeeService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BttEmployeeServiceImpl implements BttEmployeeService {
    private final List<BttEmployee> bttEmployees = new ArrayList<>();

    @PostConstruct
    public void init() {
        bttEmployees.add(new BttEmployee("E01", "Nguyen Van A", "Male", 30, 1200.0));
        bttEmployees.add(new BttEmployee("E02", "Tran Thi B", "Female", 28, 1100.0));
    }

    @Override
    public List<BttEmployee> getAllBttEmployee() {
        return new ArrayList<>(bttEmployees);
    }

    @Override
    public BttEmployee getBttEmployeeById(String bttId) {
        return bttEmployees.stream().filter(e -> e.getBttId().equals(bttId)).findFirst().orElse(null);
    }

    @Override
    public BttEmployee addBttEmployee(BttEmployee e) {
        bttEmployees.add(e);
        return e;
    }

    @Override
    public BttEmployee updateBttEmployee(String bttId, BttEmployee e) {
        BttEmployee found = getBttEmployeeById(bttId);
        if (found == null) return null;
        found.setBttFullName(e.getBttFullName());
        found.setBttGender(e.getBttGender());
        found.setBttAge(e.getBttAge());
        found.setBttSalary(e.getBttSalary());
        return found;
    }

    @Override
    public boolean deleteBttEmployee(String bttId) {
        return bttEmployees.removeIf(e -> e.getBttId().equals(bttId));
    }
}
