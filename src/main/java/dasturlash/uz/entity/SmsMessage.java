package dasturlash.uz.entity;


import dasturlash.uz.dto.SmsMessageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sms_messages")
public class SmsMessage {
    @Id
    private String id;
    private String toNumber;
    private String fromNumber;
    private String messageContent;
    private LocalDateTime timestamp;
    private String status;

    public static SmsMessage fromDto(SmsMessageDto dto) {
        SmsMessage message = new SmsMessage();
        message.setId(UUID.randomUUID().toString());
        message.setToNumber(dto.getTo());
        message.setFromNumber(dto.getFrom());
        message.setMessageContent(dto.getMessage());
        message.setTimestamp(LocalDateTime.now());
        message.setStatus("DELIVERED");
        return message;
    }
}