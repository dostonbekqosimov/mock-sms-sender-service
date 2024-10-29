package dasturlash.uz.controller;

import dasturlash.uz.dto.SmsMessageDto;
import dasturlash.uz.dto.SmsResponseDto;
import dasturlash.uz.entity.SmsMessage;
import dasturlash.uz.service.MockSmsService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
@Slf4j
public class MockSmsController {

    @Autowired
    private MockSmsService mockSmsService;

    @PostMapping("/send")
    public ResponseEntity<SmsResponseDto> sendSms(@RequestBody SmsMessageDto smsDto) {
        if (StringUtils.isEmpty(smsDto.getTo()) || StringUtils.isEmpty(smsDto.getMessage())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number and message are required");
        }

        SmsResponseDto response = mockSmsService.sendSms(smsDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<SmsMessage>> getAllMessages() {
        return ResponseEntity.ok(mockSmsService.getAllMessages());
    }



    @GetMapping("/messages/{id}")
    public ResponseEntity<SmsMessage> getMessage(@PathVariable String id) {
        return mockSmsService.getMessage(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    @GetMapping("/messages/phone")
    public ResponseEntity<SmsMessage> getMessageByPhoneNumber(@RequestParam String phone) {
        return mockSmsService.getMessageByPhoneNumber(phone)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    @DeleteMapping("/messages")
    public ResponseEntity<Void> clearMessages() {
        mockSmsService.deleteAllMessages();
        return ResponseEntity.ok().build();
    }
}
