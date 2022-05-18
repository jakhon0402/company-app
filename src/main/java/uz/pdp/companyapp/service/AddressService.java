package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.payload.AddressDto;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.repository.AddressRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    public List<Address> getAddresses(){
        return addressRepo.findAll();
    }

    public Address getAddressById(Integer id){
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return  optionalAddress.orElse(null);
    }

    public ApiResponse addAddress(AddressDto addressDto){
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepo.save(address);
        return  new ApiResponse("Address qo'shildi", true);
    }
    public ApiResponse editAddress(AddressDto addressDto,Integer id){
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if(!optionalAddress.isPresent()){
            return new ApiResponse("Bunday idlik address mavjud emas!",false);
        }
        Address address = optionalAddress.get();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepo.save(address);
        return new ApiResponse("Address tahrirlandi",true);
    }

    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if(!optionalAddress.isPresent()){
            return new ApiResponse("Bunday idlik address mavjud emas!",false);
        }
        Address address = optionalAddress.get();
        addressRepo.delete(address);
        return new ApiResponse("Address o'chirildi !", true);
    }

}
