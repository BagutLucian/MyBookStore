package view.model.builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.model.RoleDTO;
import view.model.UserDTO;

public class UserDTOBuilder {
    private UserDTO userDTO;

    public UserDTOBuilder() {
        this.userDTO = new UserDTO();
    }

    public UserDTOBuilder setId(Long id) {
        userDTO.setId(id);
        return this;
    }

    public UserDTOBuilder setUsername(String username) {
        userDTO.setUsername(username);
        return this;
    }

    public UserDTOBuilder setPassword(String password) {
        userDTO.setPassword(password);
        return this;
    }

    public UserDTOBuilder setRoles(ObservableList<RoleDTO> roles) {
        userDTO.setRoles(roles);
        return this;
    }
    public UserDTO build() {
        return this.userDTO;
    }
}
