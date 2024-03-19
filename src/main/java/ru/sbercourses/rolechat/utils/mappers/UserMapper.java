package ru.sbercourses.rolechat.utils.mappers;

import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.dto.UserDto;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.User;

import java.util.Set;

@Service
public class UserMapper {
    public static User toUser(UserDto userDto, RoleEntity roleEntity) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getUsername());
        user.setRoles(Set.of(roleEntity));
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
