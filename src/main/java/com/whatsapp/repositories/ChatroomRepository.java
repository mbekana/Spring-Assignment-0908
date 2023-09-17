package com.whatsapp.repositories;

import com.whatsapp.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    @Query("SELECT c FROM Chatroom c LEFT JOIN FETCH c.members WHERE c.id = :chatRoomId")
    Chatroom findByIdWithMembers(@Param("chatRoomId") Long chatRoomId);

}
