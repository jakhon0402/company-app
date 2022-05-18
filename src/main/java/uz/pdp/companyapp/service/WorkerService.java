package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.entity.Department;
import uz.pdp.companyapp.entity.Worker;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.WorkerDto;
import uz.pdp.companyapp.repository.AddressRepo;
import uz.pdp.companyapp.repository.DepartmentRepo;
import uz.pdp.companyapp.repository.WorkerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    public List<Worker> getWorkers(){
        return workerRepo.findAll();
    }

    public Worker getWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        return optionalWorker.orElse(null);
    }

    public ApiResponse addWorker(WorkerDto workerDto){
        Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
        if(!optionalAddress.isPresent()){
            return new ApiResponse("Bunday idlik address mavjud emas!", false);
        }
        Optional<Department> optionalDepartment = departmentRepo.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday idlik department mavjud emas!",false);
        }
        if(workerRepo.existsByPhoneNumber(workerDto.getPhoneNumber())){
            return new ApiResponse("Bunday phoneNumberlik worker mavjud !",false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepo.save(worker);
        return new ApiResponse("Worker qo'shildi !", true);
    }

    public ApiResponse editWorker(WorkerDto workerDto,Integer id){
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if(!optionalWorker.isPresent()){
            return new ApiResponse("Bunday idlik Worker mavjud emas!", false);
        }
        Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
        if(!optionalAddress.isPresent()){
            return new ApiResponse("Bunday idlik address mavjud emas!", false);
        }
        Optional<Department> optionalDepartment = departmentRepo.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday idlik department mavjud emas!",false);
        }
        if(workerRepo.existsByPhoneNumberAndIdIsNot(workerDto.getPhoneNumber(),id)){
            return new ApiResponse("Bunday phoneNumberlik worker mavjud !",false);
        }
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepo.save(worker);
        return new ApiResponse("Worker tahrirlandi!", true);
    }

    public ApiResponse deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if(!optionalWorker.isPresent()){
            return new ApiResponse("Bunday idlik Worker mavjud emas!", false);
        }
        workerRepo.delete(optionalWorker.get());
        return new ApiResponse("Worker o'chirildi !",true);
    }
}
