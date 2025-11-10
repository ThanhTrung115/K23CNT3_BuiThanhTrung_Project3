package k23cnt3.LabGuide03.controller;

import k23cnt3.LabGuide03.entity.BttMonHoc;
import k23cnt3.LabGuide03.service.BttMonHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/btt/monhoc")
public class BttMonHocController {

    private final BttMonHocService bttMonHocService;

    public BttMonHocController(BttMonHocService bttMonHocService) {
        this.bttMonHocService = bttMonHocService;
    }

    @GetMapping
    public List<BttMonHoc> getAll() {
        return bttMonHocService.getAllBttMonHoc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BttMonHoc> getById(@PathVariable("id") String id) {
        BttMonHoc m = bttMonHocService.getBttMonHocById(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BttMonHoc> add(@RequestBody BttMonHoc mh) {
        return ResponseEntity.ok(bttMonHocService.addBttMonHoc(mh));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BttMonHoc> update(@PathVariable("id") String id, @RequestBody BttMonHoc mh) {
        BttMonHoc updated = bttMonHocService.updateBttMonHoc(id, mh);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        boolean removed = bttMonHocService.deleteBttMonHoc(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
