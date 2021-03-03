
package taxiguider;

public class 할당확인됨 extends AbstractEvent {

    private Long id;
    private String 할당상태; //호출,호출중,호출확정,호출취소
    private String 수리기사번호;
    private String 수리기사기사이름;
    private String 수리기사기사전화번호;
    
    private String 고객휴대폰번호;
    private String 호출위치;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String get할당상태() {
		return 할당상태;
	}

	public void set할당상태(String 할당상태) {
		this.할당상태 = 할당상태;
	}

	public String get수리기사번호() {
		return 수리기사번호;
	}

	public void set수리기사번호(String 수리기사번호) {
		this.수리기사번호 = 수리기사번호;
	}

	public String get수리기사기사이름() {
		return 수리기사기사이름;
	}

	public void set수리기사기사이름(String 수리기사기사이름) {
		this.수리기사기사이름 = 수리기사기사이름;
	}

	public String get수리기사기사전화번호() {
		return 수리기사기사전화번호;
	}

	public void set수리기사기사전화번호(String 수리기사기사전화번호) {
		this.수리기사기사전화번호 = 수리기사기사전화번호;
	}

	public String get고객휴대폰번호() {
		return 고객휴대폰번호;
	}

	public void set고객휴대폰번호(String 고객휴대폰번호) {
		this.고객휴대폰번호 = 고객휴대폰번호;
	}

	public String get호출위치() {
		return 호출위치;
	}

	public void set호출위치(String 호출위치) {
		this.호출위치 = 호출위치;
	}

}
