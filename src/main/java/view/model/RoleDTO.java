package view.model;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class RoleDTO {

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

    //pentru role

    private StringProperty role;

    public void setRole(String role)
    {
        roleProperty().set(role);
    }

    public String getRole()
    {
        return roleProperty().get();
    }
    public StringProperty roleProperty()
    {
        if(role == null)
            role = new SimpleStringProperty(this,"role");

        return role;
    }

    // pentru lista de rights (List<Right>)
    private ListProperty<RightDTO> rights;
    public void setRights(ObservableList<RightDTO> rights) {
        rightsProperty().set(rights);
    }
    public ObservableList<RightDTO> getRights() {
        return rightsProperty().get();
    }
    public ListProperty<RightDTO> rightsProperty() {
        if (rights == null)
            rights = new SimpleListProperty<>(this, "rights");

        return rights;
    }


}
