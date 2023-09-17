package com.whatsapp.repositories;

import com.whatsapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatroom_IdOrderBySentAtAsc(Long chatroomId);
}
