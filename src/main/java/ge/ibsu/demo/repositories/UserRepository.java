package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user where " +
            "(:searchText is null or email like :searchText)",
            countQuery = "select count(*) from users where" +
                    "(:searchText is null or email like :searchText)",
            nativeQuery = true)
    Slice<User> search(@Param("searchText") String searchText, Pageable pageable);

    Optional<User> findByEmail(String email);

}
