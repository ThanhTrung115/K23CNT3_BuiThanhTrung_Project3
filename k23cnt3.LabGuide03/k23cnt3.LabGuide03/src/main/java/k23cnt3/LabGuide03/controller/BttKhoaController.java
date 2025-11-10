package k23cnt3.LabGuide03.controller;

import k23cnt3.LabGuide03.entity.BttKhoa;
import k23cnt3.LabGuide03.service.BttKhoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/btt/khoa")
public class BttKhoaController {

    private final BttKhoaService bttKhoaService;

    public BttKhoaController(BttKhoaService bttKhoaService) {
        this.bttKhoaService = bttKhoaService;
    }

    @GetMapping
    public List<BttKhoa> getAll() {
        return bttKhoaService.getAllBttKhoa();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BttKhoa> getById(@PathVariable("id") String id) {
        BttKhoa k = bttKhoaService.getBttKhoaById(id);
        return k != null ? ResponseEntity.ok(k) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BttKhoa> add(@RequestBody BttKhoa khoa) {
        return ResponseEntity.ok(bttKhoaService.addBttKhoa(khoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BttKhoa> update(@PathVariable("id") String id, @RequestBody BttKhoa khoa) {
        BttKhoa updated = bttKhoaService.updateBttKhoa(id, khoa);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        boolean removed = bttKhoaService.deleteBttKhoa(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
