package dasturlash.uz.service;

import dasturlash.uz.dto.SmsMessageDto;

import dasturlash.uz.dto.SmsResponseDto;
import dasturlash.uz.entity.SmsMessage;
import dasturlash.uz.repository.SmsMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MockSmsService {

    @Autowired
    private SmsMessageRepository smsMessageRepository;

    public SmsResponseDto sendSms(SmsMessageDto smsDto) {
        log.info("Sending mock SMS to: {}", smsDto.getTo());

        // Create and save the message
        SmsMessage message = SmsMessage.fromDto(smsDto);
        smsMessageRepository.save(message);

        return new SmsResponseDto(
                message.getId(),
                message.getStatus(),
                message.getTimestamp()
        );
    }

    public List<SmsMessage> getAllMessages() {
        return smsMessageRepository.findAll();
    }

    public Optional<SmsMessage> getMessage(String id) {
        return smsMessageRepository.findById(id);
    }

    public void deleteAllMessages() {
        smsMessageRepository.deleteAll();
    }

    public Optional<SmsMessage> getMessageByPhoneNumber(String phone) {
        phone = "+" + phone;
        return smsMessageRepository.findBytoNumber(phone);
    }
}