package k23cnt3.LabGuide03.service;

import k23cnt3.LabGuide03.entity.BttKhoa;

import java.util.List;

public interface BttKhoaService {
    List<BttKhoa> getAllBttKhoa();
    BttKhoa getBttKhoaById(String bttMaKhoa);
    BttKhoa addBttKhoa(BttKhoa khoa);
    BttKhoa updateBttKhoa(String bttMaKhoa, BttKhoa khoa);
    boolean deleteBttKhoa(String bttMaKhoa);
}
