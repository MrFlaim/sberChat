package ru.sbercourses.rolechat.utils.mappers;

import ru.sbercourses.rolechat.dto.UserDto;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.User;

import java.util.Set;

public class UserMapper {

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLogin(userDto.getUsername());
        user.setRoles(Set.of(new RoleEntity(userDto.getRole())));
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
