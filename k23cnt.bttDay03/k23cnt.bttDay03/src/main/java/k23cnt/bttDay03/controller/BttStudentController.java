package k23cnt.bttDay03.controller;

import k23cnt.bttDay03.entity.BttStudent;
import k23cnt.bttDay03.service.BttServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BttStudentController {
    @Autowired
    public BttServiceStudent bttServiceStudent;

    @GetMapping("/student-list")
    public List<BttStudent> getAllStudents() {
        return  bttServiceStudent.getBttStudents();
    }

    @GetMapping("/student/{bttId}")
    public BttStudent getBttStudentById(@PathVariable String bttId)
    {
        Long param = Long.parseLong(bttId);
        return  bttServiceStudent.getBttStudent(param);
    }

}
