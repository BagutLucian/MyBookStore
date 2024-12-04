package view.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RightDTO {

    //pentru id\\

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

    //pentru right\\

    private StringProperty right;

    public void setRight(String right)
    {
        rightProperty().set(right);
    }

    public String getRight()
    {
        return rightProperty().get();
    }
    public StringProperty rightProperty()
    {
        if(right == null)
            right = new SimpleStringProperty(this,"right");

        return right;
    }
}
