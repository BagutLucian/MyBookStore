package model.builder;

import java.util.List;
import model.Role;

public class RoleBuilder {

    private Role role;

    public RoleBuilder(){
        role = new Role();
    }

    public RoleBuilder setId(Long id){
        role.setId(id);
        return this;
    }

    public RoleBuilder setRole(String name){
        role.setRole(name);
        return this;
    }

    public Role build(){
        return role;
    }

}