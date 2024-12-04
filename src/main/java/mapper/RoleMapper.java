package mapper;
import model.Role;
import view.model.RoleDTO;
import view.model.builder.RoleDTOBuilder;
import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDTO convertRoleToRoleDTO(Role role) {
        return new RoleDTOBuilder()
                .setId(role.getId())
                .setRole(role.getRole())
                .setRights(RightMapper.convertRightListToRightDTOList(role.getRights())
                        .stream()
                        .collect(Collectors.toCollection(javafx.collections.FXCollections::observableArrayList)))
                .build();
    }

    public static Role convertRoleDTOToRole(RoleDTO roleDTO) {
        return new Role(
                roleDTO.getId(),
                roleDTO.getRole(),
                RightMapper.convertRightDTOListToRightList(roleDTO.getRights())
        );
    }
    public static List<RoleDTO> convertRoleListToRoleDTOList(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::convertRoleToRoleDTO)
                .collect(Collectors.toList());
    }

    public static List<Role> convertRoleDTOListToRoleList(List<RoleDTO> roleDTOs) {
        return roleDTOs.stream()
                .map(RoleMapper::convertRoleDTOToRole)
                .collect(Collectors.toList());
    }
}
