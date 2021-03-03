package taxiguider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import taxiguider.config.kafka.KafkaProcessor;

@Service
public class TaxicallPolicyHandler {
	@Autowired
	TaxicallRepository txCallRepository;

	@StreamListener(KafkaProcessor.INPUT)
	public void onStringEventListener(@Payload String eventString) {

	}

	@StreamListener(KafkaProcessor.INPUT)
	public void wheneverTaxiassignCompleted_(@Payload TaxiassignCompleted txcompleted) {
		System.out.println("##### EVT TYPE[할당확인됨]  : " + txcompleted.getEventType());
		if (txcompleted.isMe() && txcompleted.getTel() != null) {

//           try {
//               // 원래 데이터가 트랜잭션 커밋되기도 전에 이벤트가 너무 빨리 도달하는 경우를 막기 위함
//               Thread.currentThread().sleep(3000); //  no good. --> pay 가 TX 를 마친 후에만 실행되도록 수정함
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
			System.out.println("##### listener[할당확인됨]  : " + txcompleted.toJson());
			

			// Correlation id 는 '고객휴대폰번호' 임
			if(txcompleted.getId() != null)
				txCallRepository.findById(Long.valueOf(txcompleted.getId())).ifPresent((taxicall) -> {
					taxicall.setStatus("호출확정");
					txCallRepository.save(taxicall);
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
	public void wheneverTaxiassignCancelled_(@Payload TaxiassignCancelled txcanceled) {
		System.out.println("##### EVT TYPE[할당취소됨]  : " + txcanceled.getEventType());
		if (txcanceled.isMe()) {
			System.out.println("##### listener[할당취소됨]  : " + txcanceled.toJson());
			txCallRepository.findById(Long.valueOf(txcanceled.getId())).ifPresent((수리기사호출) -> {
				수리기사호출.setStatus("호출취소");
				txCallRepository.save(수리기사호출);
			});
		}
	}

}
