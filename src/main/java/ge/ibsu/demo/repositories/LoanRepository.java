package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Loan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query(value = "select * from loan where user_id = :userId",
            countQuery = "select count(*) from user where user_id = :userId",
            nativeQuery = true)
    Slice<Loan> searchByUserId(@Param("userId") Long userId, Pageable pageable);

}
