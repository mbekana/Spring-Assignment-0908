package com.whatsapp.services;

import com.whatsapp.entity.Chatroom;
import com.whatsapp.exceptions.ChatroomNotFoundException;
import com.whatsapp.exceptions.UserAlreadyInChatroomException;
import com.whatsapp.exceptions.UserNotFoundException;
import com.whatsapp.exceptions.UserNotInChatroomException;
import com.whatsapp.repositories.ChatroomRepository;
import com.whatsapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.whatsapp.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService {

    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;

    @Override
    public Chatroom createChatRoom(Chatroom chatroom) {
        // You should implement logic to create a chat room and store it in the database
        // You can use chatroomRepository.save(chatroom) to save the chat room
        // Return the created chatroom
        return chatroomRepository.save(chatroom);
    }

    @Override
    public Chatroom joinChatroom(Long chatroomId, Long userId) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new ChatroomNotFoundException("Chatroom not found"));

        // Find the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        try {
            // Check if the user is already a member of the chatroom
            if (chatroom.getMembers().contains(user)) {
                throw new UserAlreadyInChatroomException("User is already a member of this chatroom");
            }

            // Add the user to the chatroom's members list
            chatroom.getMembers().add(user);

            // Update the database to reflect the changes
            chatroomRepository.save(chatroom);

            return chatroom;
        } catch (UserAlreadyInChatroomException e) {
            // You can choose to log the exception here if needed
            throw e; // Re-throw the exception to propagate it to the controller
        }
    }


    @Override
    public Chatroom getChatroomById(Long chatRoomId) {
        return chatroomRepository.findByIdWithMembers(chatRoomId); // Handle the case where the chat room may not exist
    }


    @Override
    public Chatroom leaveChatroom(Long chatroomId, Long userId) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new ChatroomNotFoundException("Chatroom not found"));

        // Find the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Check if the user is a member of the chatroom
        if (!chatroom.getMembers().contains(user)) {
            throw new UserNotInChatroomException("User is not a member of this chatroom");
        }

        // Remove the user from the chatroom's members list
        chatroom.getMembers().remove(user);

        // Update the database to reflect the changes
        chatroomRepository.save(chatroom);

        return chatroom;
    }
}
