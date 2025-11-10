package k23cnt3.LabGuide03.service;

import k23cnt3.LabGuide03.entity.BttMonHoc;

import java.util.List;

public interface BttMonHocService {
    List<BttMonHoc> getAllBttMonHoc();
    BttMonHoc getBttMonHocById(String bttMaMH);
    BttMonHoc addBttMonHoc(BttMonHoc mh);
    BttMonHoc updateBttMonHoc(String bttMaMH, BttMonHoc mh);
    boolean deleteBttMonHoc(String bttMaMH);
}
