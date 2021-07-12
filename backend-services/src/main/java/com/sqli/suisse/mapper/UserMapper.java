package com.sqli.suisse.mapper;

import com.sqli.suisse.dao.model.User;
import com.sqli.suisse.dto.UserViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "firstName", target = "firstName")
    UserViewDTO userToUserViewDto(User user);
}
