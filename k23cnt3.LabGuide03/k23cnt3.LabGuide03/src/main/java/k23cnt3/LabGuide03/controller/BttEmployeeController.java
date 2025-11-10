package k23cnt3.LabGuide03.controller;

import k23cnt3.LabGuide03.entity.BttEmployee;
import k23cnt3.LabGuide03.service.BttEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/btt/employee")
public class BttEmployeeController {

    private final BttEmployeeService bttEmployeeService;

    public BttEmployeeController(BttEmployeeService bttEmployeeService) {
        this.bttEmployeeService = bttEmployeeService;
    }

    @GetMapping
    public List<BttEmployee> getAll() {
        return bttEmployeeService.getAllBttEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BttEmployee> getById(@PathVariable("id") String id) {
        BttEmployee e = bttEmployeeService.getBttEmployeeById(id);
        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BttEmployee> add(@RequestBody BttEmployee e) {
        return ResponseEntity.ok(bttEmployeeService.addBttEmployee(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BttEmployee> update(@PathVariable("id") String id, @RequestBody BttEmployee e) {
        BttEmployee updated = bttEmployeeService.updateBttEmployee(id, e);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        boolean removed = bttEmployeeService.deleteBttEmployee(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
