package taxiguider;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface 수리기사관리Repository extends PagingAndSortingRepository<수리기사관리, Long>{

	Optional<수리기사관리> findBy고객휴대폰번호(String get고객휴대폰번호);


}