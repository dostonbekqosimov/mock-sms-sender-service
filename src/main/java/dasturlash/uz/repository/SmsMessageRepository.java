package dasturlash.uz.repository;

import dasturlash.uz.entity.SmsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsMessageRepository extends JpaRepository<SmsMessage, String> {

    Optional<SmsMessage> findBytoNumber(String phone);
}