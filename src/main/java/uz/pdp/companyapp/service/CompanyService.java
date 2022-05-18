package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.entity.Company;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.CompanyDto;
import uz.pdp.companyapp.repository.AddressRepo;
import uz.pdp.companyapp.repository.CompanyRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    AddressRepo addressRepo;

    public List<Company> getCompanies(){
        return companyRepo.findAll();
    }

    public Company getCompany(Integer id){
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto){
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepo.save(address);
        Company company = new Company();
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        company.setAddress(savedAddress);
        companyRepo.save(company);
        return new ApiResponse("Company qo'shildi !",true);
    }

    public ApiResponse editCompany(CompanyDto companyDto,Integer id){
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if(!optionalCompany.isPresent()){
            return new ApiResponse("Bunday idlik company mavjud emas !", false);
        }
        Address address = optionalCompany.get().getAddress();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepo.save(address);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepo.save(company);
        return new ApiResponse("Company tahrirlandi !", true);
    }

    public ApiResponse deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if(!optionalCompany.isPresent()){
            return new ApiResponse("Bunday idlik company mavjud emas !", false);
        }
        companyRepo.delete(optionalCompany.get());
        return new ApiResponse("Company o'childi !", true);
    }
}
