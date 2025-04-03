package org.example.testtask1221.mapper;

import org.example.testtask1221.dto.UserDto;
import org.example.testtask1221.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User dtoToUser(UserDto userDto);

  User idToUser(Long id);
}
