package com.whatsapp.mappers;

import com.whatsapp.dtos.MediaDTO;
import com.whatsapp.entity.Media;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    Media toMidea(MediaDTO mediaDTO);
    MediaDTO toMideaDTO(Media video);
}


