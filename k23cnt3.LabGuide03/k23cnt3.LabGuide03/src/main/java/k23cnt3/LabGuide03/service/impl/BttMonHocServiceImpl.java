package k23cnt3.LabGuide03.service.impl;

import k23cnt3.LabGuide03.entity.BttMonHoc;
import k23cnt3.LabGuide03.service.BttMonHocService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BttMonHocServiceImpl implements BttMonHocService {
    private final List<BttMonHoc> bttMonHocs = new ArrayList<>();

    @PostConstruct
    public void init() {
        bttMonHocs.add(new BttMonHoc("MH01", "Toán Cao Cấp", 3));
        bttMonHocs.add(new BttMonHoc("MH02", "Lập Trình Java", 4));
        bttMonHocs.add(new BttMonHoc("MH03", "Cơ Sở Dữ Liệu", 3));
        bttMonHocs.add(new BttMonHoc("MH04", "Mạng Máy Tính", 3));
    }

    @Override
    public List<BttMonHoc> getAllBttMonHoc() {
        return new ArrayList<>(bttMonHocs);
    }

    @Override
    public BttMonHoc getBttMonHocById(String bttMaMH) {
        return bttMonHocs.stream().filter(m -> m.getBttMaMH().equals(bttMaMH)).findFirst().orElse(null);
    }

    @Override
    public BttMonHoc addBttMonHoc(BttMonHoc mh) {
        bttMonHocs.add(mh);
        return mh;
    }

    @Override
    public BttMonHoc updateBttMonHoc(String bttMaMH, BttMonHoc mh) {
        BttMonHoc found = getBttMonHocById(bttMaMH);
        if (found == null) return null;
        found.setBttTenMH(mh.getBttTenMH());
        found.setBttSotinchi(mh.getBttSotinchi());
        return found;
    }

    @Override
    public boolean deleteBttMonHoc(String bttMaMH) {
        return bttMonHocs.removeIf(m -> m.getBttMaMH().equals(bttMaMH));
    }
}
