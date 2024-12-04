package mapper;

import model.User;
import view.model.UserDTO;
import view.model.builder.UserDTOBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO convertUserToUserDTO(User user) {
        return new UserDTOBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setRoles(RoleMapper.convertRoleListToRoleDTOList(user.getRoles())
                        .stream()
                        .collect(Collectors.toCollection(javafx.collections.FXCollections::observableArrayList)))
                .build();
    }

    public static User convertUserDTOToUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                RoleMapper.convertRoleDTOListToRoleList(userDTO.getRoles())
        );
    }

    public static List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    public static List<User> convertUserDTOListToUserList(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(UserMapper::convertUserDTOToUser)
                .collect(Collectors.toList());
    }
}
