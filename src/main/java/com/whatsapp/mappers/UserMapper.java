package com.whatsapp.mappers;

import com.whatsapp.dtos.UserDTO;
import com.whatsapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "chatrooms", ignore = true)
    UserDTO toUserDTO(User user);

//    @Mapping(target = "chatrooms", ignore = true)
    User toUser(UserDTO userDTO);
}
