package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.entity.Worker;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.WorkerDto;
import uz.pdp.companyapp.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public List<Worker> getWorkers(){
      return workerService.getWorkers();
    }

    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Integer id){
        return workerService.getWorker(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@Valid @RequestBody WorkerDto workerDto,
                                                  @PathVariable Integer id){
        ApiResponse apiResponse = workerService.editWorker(workerDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

}
