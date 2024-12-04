package mapper;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import model.Right;
import view.model.RightDTO;
import view.model.builder.RightDTOBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RightMapper {

    public static RightDTO convertRightToRightDTO(Right right) {
        return new RightDTOBuilder()
                .setId(right.getId())
                .setRight(right.getRight())
                .build();
    }

    public static Right convertRightDTOToRight(RightDTO rightDTO) {
        return new Right(
                rightDTO.getId(),
                rightDTO.getRight()
        );
    }
    public static List<RightDTO> convertRightListToRightDTOList(List<Right> rights) {
        if(rights==null)
        {
            return Collections.emptyList();

        }
        return rights.stream()
                .map(RightMapper::convertRightToRightDTO)
                .collect(Collectors.toList());
    }
    public static List<Right> convertRightDTOListToRightList(List<RightDTO> rightDTOs) {
        return rightDTOs.stream()
                .map(RightMapper::convertRightDTOToRight)
                .collect(Collectors.toList());
    }
}
