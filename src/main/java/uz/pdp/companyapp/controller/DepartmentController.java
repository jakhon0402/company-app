package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.entity.Department;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.DepartmentDto;
import uz.pdp.companyapp.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id){
        return departmentService.getDepartment(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@Valid @RequestBody DepartmentDto departmentDto,
                                                     @PathVariable Integer id){
        ApiResponse apiResponse = departmentService.editDepartment(departmentDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
