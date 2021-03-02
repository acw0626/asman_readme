package taxiguider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import taxiguider.config.kafka.KafkaProcessor;

@Service
public class 택시할당PolicyHandler{
	@Autowired 택시할당Repository 할당Repository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    private static String[][] driverBank = 
	{
		{"백영곤", "010-2345-6789", "34가4567"},
		{"안채우", "010-3345-7789", "44나4567"},
		{"임광현", "010-4345-8789", "54다4567"},
		{"장윤정", "010-5345-9789", "64라4567"},
		{"옥준삼", "010-6345-0789", "74마4567"},
		{"유승오", "010-7345-1789", "84사4567"}
	};
	
	public static 할당확인됨 get택시할당됨() {
		할당확인됨 할당확인됨 = new 할당확인됨();
		
		int randDriver = (int)(Math.random() * 6);
		할당확인됨.set택시기사이름(driverBank[randDriver][0]);
		할당확인됨.set택시기사전화번호(driverBank[randDriver][1]);
        할당확인됨.set택시번호(driverBank[randDriver][2]);
        return 할당확인됨;
	}
    
    //private String 호출상태; //호출,호출중,호출확정,호출취소
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당요청됨_(@Payload 택시할당요청됨 택시할당요청됨){
    	System.out.println("##### EVT TYPE[택시할당요청됨]  : " + 택시할당요청됨.getEventType());
        if(택시할당요청됨.isMe()){
            System.out.println("##### listener  : " + 택시할당요청됨.toJson());
            
            if(택시할당요청됨.get호출상태() != null  && 택시할당요청됨.get호출상태().equals("호출중"))
            {
            	
            	택시할당요청됨.set호출상태("호출확정");
            	//할당확인됨 할당확인됨 = Assigner.get택시할당됨();
            	//BeanUtils.copyProperties(택시할당요청됨, 할당확인됨);
            	//할당확인됨.setEventType("할당확인됨");
            	택시할당요청됨.publish();
            	
            	할당확인됨 할당확인됨 = Assigner.get택시할당됨();
            	할당확인됨.setId(택시할당요청됨.getId());
            	할당확인됨.set할당상태("할당확정");
                할당확인됨.set고객휴대폰번호(택시할당요청됨.get고객휴대폰번호());
                할당확인됨.set호출위치(택시할당요청됨.get고객위치());
            	할당확인됨.setEventType("할당확인됨");
            	//택시할당요청됨.publishAfterCommit();
            	할당확인됨.publish(); 
            }  
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever할당확인됨_(@Payload 할당확인됨 할당확인됨){
    	System.out.println("##### EVT TYPE[할당확인됨]  : " + 할당확인됨.getEventType());
        if(할당확인됨.isMe()){
            System.out.println("##### listener  : " + 할당확인됨.toJson());
            
            if(할당확인됨.get할당상태() != null  && 할당확인됨.get할당상태().equals("할당확정"))
            {
            	
//            	할당확인됨 할당확인됨 = Assigner.get택시할당됨();
//            	BeanUtils.copyProperties(택시할당요청됨, 할당확인됨);
//            	
//                //할당확인됨.setEventType("할당확인됨");
//            	할당확인됨.setEventType("할당확인됨");
//            	//택시할당요청됨.publishAfterCommit();
//            	할당확인됨.publish(); 
            }  
        }
    }
    
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당취소됨_(@Payload 택시할당취소됨 택시할당취소됨){
    	
        if(택시할당취소됨.isMe()){
            System.out.println("##### listener  : " + 택시할당취소됨.toJson());
            
            
            할당Repository.findById(Long.valueOf(택시할당취소됨.getId())).ifPresent((택시호출) -> {
				택시호출.set호출상태("할당취소");
				할당Repository.save(택시호출);
			});
            
            택시할당취소됨.publish();
        }
    }

}
