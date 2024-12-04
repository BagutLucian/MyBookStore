package view.model.builder;

import view.model.RightDTO;

public class RightDTOBuilder {
    private RightDTO rightDTO;

    public RightDTOBuilder() {
        this.rightDTO = new RightDTO();
    }

    public RightDTOBuilder setId(Long id) {
        rightDTO.setId(id);
        return this;
    }

    public RightDTOBuilder setRight(String right) {
        rightDTO.setRight(right);
        return this;
    }

    public RightDTO build() {
        return this.rightDTO;
    }
}
