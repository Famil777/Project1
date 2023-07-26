package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto usersToUserDto(Users user);
    Users userDtoToUsers(UserDto userDto);
}
