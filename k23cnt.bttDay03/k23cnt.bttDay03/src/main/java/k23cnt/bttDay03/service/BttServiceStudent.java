package k23cnt.bttDay03.service;

import k23cnt.bttDay03.entity.BttStudent;
import org.springframework.stereotype.Service;

import  java.util.*;

/**
 * Service class: TvcServiceStudent
 * <p>Lớp dịch vụ thực hiện các chức năng thao tác với List
 Object Student</p>
 *
 * @author Chung Trịnh
 * @version 1.0
 * @Date 10/11/2025
 */

@Service
public class BttServiceStudent {
    List<BttStudent> bttStudents ;

    public  BttServiceStudent(){
        bttStudents = List.of(
                new BttStudent(1L, "Bùi Thành Trung", 46, true, "Hà Nội", "0969021769", "buitrung4212@gmail.com"),
                new BttStudent(2L, "Trần Thị Bình", 21, false, "Hải Phòng", "0987654321", "binh.tran@example.com"),
                new BttStudent(3L, "Lê Minh Cường", 22, true, "Đà Nẵng", "0905123456", "cuong.le@example.com"),
                new BttStudent(4L, "Phạm Thảo Vy", 19, false, "TP. Hồ Chí Minh", "0978123456", "vy.pham@example.com"),
                new BttStudent(5L, "Võ Đức Hoàng", 23, true, "Cần Thơ", "0932123456", "hoang.vo@example.com"),
                new BttStudent(6L, "Hoàng Lan Chi", 20, false, "Nam Định", "0945123123", "chi.hoang@example.com"),
                new BttStudent(7L, "Ngô Văn Tuấn", 24, true, "Thanh Hóa", "0915456789", "tuan.ngo@example.com"),
                new BttStudent(8L, "Đặng Mai Hương", 22, false, "Nghệ An", "0967345678", "huong.dang@example.com"),
                new BttStudent(9L, "Lý Quốc Bảo", 21, true, "Bình Dương", "0902345678", "bao.ly@example.com"),
                new BttStudent(10L, "Đỗ Thị Hạnh", 20, false, "Vĩnh Long", "0923456789", "hanh.do@example.com")
        );
    }
    // Các phương thức
    // Lấy toàn bộ danh sách sinh viên
    public List<BttStudent> getBttStudents() {
        return  bttStudents;
    }

    // Lấy sinh viên theo id
    public BttStudent getBttStudent(Long bttId) {
        return bttStudents.stream()
                .filter(student -> student
                        .getBttId() == bttId)
                .findFirst().orElse(null);
    }

}
