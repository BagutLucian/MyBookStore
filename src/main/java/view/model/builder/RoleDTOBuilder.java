package view.model.builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.model.RightDTO;
import view.model.RoleDTO;

public class RoleDTOBuilder {
    private RoleDTO roleDTO;

    public RoleDTOBuilder() {
        this.roleDTO = new RoleDTO();
    }

    public RoleDTOBuilder setId(Long id) {
        roleDTO.setId(id);
        return this;
    }

    public RoleDTOBuilder setRole(String role) {
        roleDTO.setRole(role);
        return this;
    }

    public RoleDTOBuilder setRights(ObservableList<RightDTO> rights) {
        roleDTO.setRights(rights);
        return this;
    }
    public RoleDTO build() {
        return this.roleDTO;
    }
}
