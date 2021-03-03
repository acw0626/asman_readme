package taxiguider.external;


public class TaximanageServiceFallback implements TaximanageService {
    
	@Override
	public void 수리기사할당요청(Taximanage 수리기사관리) //, fallback = 결제이력ServiceFallback.class)
	{
		System.out.println("Circuit breaker has been opened. Fallback returned instead.");
	}
}
