package taxiguider.external;

import org.springframework.stereotype.Component;

@Component
public class TaximanageServiceFallback implements TaximanageService {
	 
	//@Override
	//public void 수리기사할당요청(수리기사관리 수리기사관리)
	//{	
	//	System.out.println("Circuit breaker has been opened. Fallback returned instead.");
	//}
	
	
	@Override
	public void requestTaxiAssign(Taximanage txMange) {
		// TODO Auto-generated method stub
		System.out.println("Circuit breaker has been opened. Fallback returned instead. " 
				+ txMange.getId());
	}

}
