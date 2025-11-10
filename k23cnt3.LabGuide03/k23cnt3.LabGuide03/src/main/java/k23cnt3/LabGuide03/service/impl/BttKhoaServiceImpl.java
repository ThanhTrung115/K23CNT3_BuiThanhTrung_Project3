package k23cnt3.LabGuide03.service.impl;

import k23cnt3.LabGuide03.entity.BttKhoa;
import k23cnt3.LabGuide03.service.BttKhoaService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BttKhoaServiceImpl implements BttKhoaService {
    private final List<BttKhoa> bttKhoas = new ArrayList<>();

    @PostConstruct
    public void init() {
        bttKhoas.add(new BttKhoa("K01", "Công nghệ thông tin"));
        bttKhoas.add(new BttKhoa("K02", "Điện tử viễn thông"));
        bttKhoas.add(new BttKhoa("K03", "Kinh tế"));
        bttKhoas.add(new BttKhoa("K04", "Cơ khí"));
        bttKhoas.add(new BttKhoa("K05", "Toán - Tin"));
    }

    @Override
    public List<BttKhoa> getAllBttKhoa() {
        return new ArrayList<>(bttKhoas);
    }

    @Override
    public BttKhoa getBttKhoaById(String bttMaKhoa) {
        return bttKhoas.stream().filter(k -> k.getBttMaKhoa().equals(bttMaKhoa)).findFirst().orElse(null);
    }

    @Override
    public BttKhoa addBttKhoa(BttKhoa khoa) {
        bttKhoas.add(khoa);
        return khoa;
    }

    @Override
    public BttKhoa updateBttKhoa(String bttMaKhoa, BttKhoa khoa) {
        BttKhoa found = getBttKhoaById(bttMaKhoa);
        if (found == null) return null;
        found.setBttTenKhoa(khoa.getBttTenKhoa());
        return found;
    }

    @Override
    public boolean deleteBttKhoa(String bttMaKhoa) {
        return bttKhoas.removeIf(k -> k.getBttMaKhoa().equals(bttMaKhoa));
    }
}
