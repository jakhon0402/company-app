package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Company;
import uz.pdp.companyapp.entity.Department;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.DepartmentDto;
import uz.pdp.companyapp.repository.CompanyRepo;
import uz.pdp.companyapp.repository.DepartmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    CompanyRepo companyRepo;

    public List<Department> getDepartments(){
        return departmentRepo.findAll();
    }

    public Department getDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepo.findById(departmentDto.getCompanyId());
        if(!optionalCompany.isPresent()){
            return new ApiResponse("Bunday idlik company mavjud emas !", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepo.save(department);
        return new ApiResponse("Departmnet qo'shildi!",true);
    }

    public ApiResponse editDepartment(DepartmentDto departmentDto, Integer id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday idlik department mavjud emas !", false);
        }
        Optional<Company> optionalCompany = companyRepo.findById(departmentDto.getCompanyId());
        if(!optionalCompany.isPresent()){
            return new ApiResponse("Bunday idlik company mavjud emas !", false);
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepo.save(department);
        return new ApiResponse("Department tahrirlandi !",true);
    }

    public ApiResponse deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday idlik deparment mavjud emas !",false);
        }
        departmentRepo.delete(optionalDepartment.get());
        return new ApiResponse("Department o'chirildi !",true);
    }
}
