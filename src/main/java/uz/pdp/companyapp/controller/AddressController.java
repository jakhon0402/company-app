package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.payload.AddressDto;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * Barcha address ma'lumotlarini olish
     * @return
     */
    @GetMapping
    public List<Address> getAddresses(){
        return addressService.getAddresses();
    }

    /**
     * Address ni id orqali olish
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id){
        return addressService.getAddressById(id);
    }


    /**
     * Yangi address qo'shish
     * @param addressDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addAddress(@Valid @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Address ni id orqali edit qilish
     * @param addressDto
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAddress(@Valid @RequestBody AddressDto addressDto,
                                                   @PathVariable Integer id){
        ApiResponse apiResponse = addressService.editAddress(addressDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    /**
     * Addressni id orqali o'chirish
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
