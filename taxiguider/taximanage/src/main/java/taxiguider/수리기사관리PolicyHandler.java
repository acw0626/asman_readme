package taxiguider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import taxiguider.config.kafka.KafkaProcessor;

@Service
public class 수리기사관리PolicyHandler {
	@Autowired
	수리기사관리Repository 수리기사관리Repository;

	@StreamListener(KafkaProcessor.INPUT)
	public void onStringEventListener(@Payload String eventString) {

	}

	@StreamListener(KafkaProcessor.INPUT)
	public void whenever호출취소됨_(@Payload 호출취소됨 호출취소됨) {
		System.out.println("##### EVT TYPE[호출취소됨]  : " + 호출취소됨.getEventType());
		if (호출취소됨.isMe()) {
			System.out.println("##### listener  : " + 호출취소됨.toJson());

			if (호출취소됨.getId() != null)
				// Correlation id 는 '고객휴대폰번호' 임
				수리기사관리Repository.findById(Long.valueOf(호출취소됨.getId())).ifPresent((수리기사관리) -> {
					수리기사관리.set호출상태("호출요청취소됨");
					수리기사관리Repository.save(수리기사관리);
				});
		}
	}

	@StreamListener(KafkaProcessor.INPUT)
	public void whenever수리기사할당요청됨_(@Payload 수리기사할당요청됨 수리기사할당요청됨) {
		System.out.println("##### EVT TYPE[수리기사할당요청됨]  : " + 수리기사할당요청됨.getEventType());
		if (수리기사할당요청됨.isMe()) {
			System.out.println("##### listener[할당확인됨]  : " + 수리기사할당요청됨.toJson());

			if (수리기사할당요청됨.getId() != null)
				// Correlation id 는 '고객휴대폰번호' 임
				수리기사관리Repository.findById(Long.valueOf(수리기사할당요청됨.getId())).ifPresent((수리기사관리) -> {
					수리기사관리.set호출상태(수리기사할당요청됨.get호출상태());
					수리기사관리Repository.save(수리기사관리);
				});

//        	수리기사관리Repository.findBy고객휴대폰번호(수리기사할당요청됨.get고객휴대폰번호()).ifPresent((수리기사관리) -> {
//				System.out.println("수리기사할당요청됨 = " + 수리기사관리.get고객휴대폰번호());
//				수리기사관리.set호출상태(수리기사할당요청됨.get호출상태());
//				수리기사관리Repository.save(수리기사관리);
//			});
//            수리기사관리 관리 = new 수리기사관리();
//            관리.set호출상태(할당확인됨.get호출상태());
//            관리.set수리기사기사이름(할당확인됨.get수리기사기사이름());
//            관리.set수리기사기사전화번호(할당확인됨.get수리기사기사전화번호());
//            관리.set수리기사번호(할당확인됨.get수리기사번호());
//            수리기사관리Repository.save(관리);
		}
	}

//    @StreamListener(KafkaProcessor.INPUT)
//    public void whenever수리기사할당확인됨_(@Payload 할당확인됨 할당확인됨){
//    	System.out.println("##### EVT TYPE[할당확인됨]  : " + 할당확인됨.getEventType());
//        if(할당확인됨.isMe()){
//            System.out.println("##### listener  : " + 할당확인됨.toJson());
//            수리기사관리 관리 = new 수리기사관리();
//            관리.set호출상태(할당확인됨.get할당상태());
//            관리.set수리기사기사이름(할당확인됨.get수리기사기사이름());
//            관리.set수리기사기사전화번호(할당확인됨.get수리기사기사전화번호());
//            관리.set수리기사번호(할당확인됨.get수리기사번호());
//            수리기사관리Repository.save(관리);
//        }
//    }

}
