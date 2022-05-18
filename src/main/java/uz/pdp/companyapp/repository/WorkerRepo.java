package uz.pdp.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyapp.entity.Worker;

public interface WorkerRepo extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdIsNot(String phoneNumber,Integer id);
}
