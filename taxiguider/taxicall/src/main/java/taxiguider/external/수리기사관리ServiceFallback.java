package taxiguider.external;

import org.springframework.stereotype.Component;

@Component
public class 수리기사관리ServiceFallback implements 수리기사관리Service {
	 
	//@Override
	//public void 수리기사할당요청(수리기사관리 수리기사관리)
	//{	
	//	System.out.println("Circuit breaker has been opened. Fallback returned instead.");
	//}
	
	
	@Override
	public void 수리기사할당요청(수리기사관리 수리기사관리) {
		// TODO Auto-generated method stub
		System.out.println("Circuit breaker has been opened. Fallback returned instead. " + 수리기사관리.getId());
	}

}
