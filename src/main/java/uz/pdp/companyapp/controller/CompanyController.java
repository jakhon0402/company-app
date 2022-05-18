package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.entity.Company;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.CompanyDto;
import uz.pdp.companyapp.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id){
        return companyService.getCompany(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCompany(@Valid @RequestBody CompanyDto companyDto,
                                                   @PathVariable Integer id){
        ApiResponse apiResponse = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        companyService.deleteCompany(id)
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
