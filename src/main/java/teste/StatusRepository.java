package teste;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
  
  public Status findById(Long id);
  public List<Status> findAll();
}

