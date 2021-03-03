package taxiguider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import taxiguider.config.kafka.KafkaProcessor;

@Service
public class 수리기사호출PolicyHandler {
	@Autowired
	수리기사호출Repository 수리기사호출Repository;

	@StreamListener(KafkaProcessor.INPUT)
	public void onStringEventListener(@Payload String eventString) {

	}

	@StreamListener(KafkaProcessor.INPUT)
	public void whenever할당확인됨_(@Payload 할당확인됨 할당확인됨) {
		System.out.println("##### EVT TYPE[할당확인됨]  : " + 할당확인됨.getEventType());
		if (할당확인됨.isMe() && 할당확인됨.get고객휴대폰번호() != null) {

//           try {
//               // 원래 데이터가 트랜잭션 커밋되기도 전에 이벤트가 너무 빨리 도달하는 경우를 막기 위함
//               Thread.currentThread().sleep(3000); //  no good. --> pay 가 TX 를 마친 후에만 실행되도록 수정함
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
			System.out.println("##### listener[할당확인됨]  : " + 할당확인됨.toJson());
			

			// Correlation id 는 '고객휴대폰번호' 임
			if(할당확인됨.getId() != null)
				수리기사호출Repository.findById(Long.valueOf(할당확인됨.getId())).ifPresent((수리기사호출) -> {
					수리기사호출.set호출상태("호출확정");
					수리기사호출Repository.save(수리기사호출);
				});
//			수리기사호출Repository.findBy휴대폰번호(할당확인됨.get고객휴대폰번호()).ifPresent((수리기사호출) -> {
//				System.out.println("할당확인됨 = " + 할당확인됨.get고객휴대폰번호());
//				수리기사호출.set호출상태("호출확정");
//				수리기사호출Repository.save(수리기사호출);
//			});
		}

//		if (할당확인됨.isMe()) {
//			수리기사호출 호출 = new 수리기사호출();
//			호출.set호출상태(할당확인됨.get할당상태());
//			수리기사호출Repository.save(호출);
//
//			System.out.println("##### listener[할당확인됨]  : " + 할당확인됨.toJson());
//		}
	}

	@StreamListener(KafkaProcessor.INPUT)
	public void whenever할당취소됨_(@Payload 할당취소됨 할당취소됨) {
		System.out.println("##### EVT TYPE[할당취소됨]  : " + 할당취소됨.getEventType());
		if (할당취소됨.isMe()) {
			System.out.println("##### listener[할당취소됨]  : " + 할당취소됨.toJson());
			수리기사호출Repository.findById(Long.valueOf(할당취소됨.getId())).ifPresent((수리기사호출) -> {
				수리기사호출.set호출상태("호출취소");
				수리기사호출Repository.save(수리기사호출);
			});
		}
	}

}
