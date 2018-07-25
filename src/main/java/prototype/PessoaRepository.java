package prototype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
