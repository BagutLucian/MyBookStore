package view.model;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    //pentru id

    private LongProperty id;
    public void setId(Long id)
    {
        idProperty().set(id);
    }
    public Long getId()
    {
        return idProperty().get();
    }
    public LongProperty idProperty()
    {
        if(id == null)
            id = new SimpleLongProperty(this,"id");

        return id;
    }

    //pentru username

    private StringProperty username;

    public void setUsername(String username)
    {
        usernameProperty().set(username);
    }

    public String getUsername()
    {
        return usernameProperty().get();
    }
    public StringProperty usernameProperty()
    {
        if(username == null)
            username = new SimpleStringProperty(this,"username");

        return username;
    }

    //pentru password

    private StringProperty password;

    public void setPassword(String password)
    {
        passwordProperty().set(password);
    }
    public String getPassword()
    {
        return passwordProperty().get();
    }
    public StringProperty passwordProperty()
    {
        if(password == null)
            password = new SimpleStringProperty(this,"password");

        return password;
    }

    // pentru lista de roles (List<Roles>)
    private ListProperty<RoleDTO> roles;
    public void setRoles(ObservableList<RoleDTO> roles) {
       rolesProperty().set(roles);
    }
    public ObservableList<RoleDTO> getRoles() {
        return rolesProperty().get();
    }

    public List<String> getRoleNames() {
        return rolesProperty().get().stream().map(RoleDTO::getRole).toList();
    }
    public ListProperty<RoleDTO> rolesProperty() {
        if (roles == null)
            roles = new SimpleListProperty<>(this, "roles");

        return roles;
    }



}
