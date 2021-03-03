package taxiguider;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface 수리기사호출Repository extends PagingAndSortingRepository<수리기사호출, Long>{

//	Optional<수리기사호출> findBy휴대폰번호(String 휴대폰번호);
}