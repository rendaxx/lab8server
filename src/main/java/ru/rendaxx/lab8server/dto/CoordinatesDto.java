package ru.rendaxx.lab8server.dto;

import lombok.Data;
import ru.rendaxx.lab8server.entity.Coordinates;

import java.io.Serializable;

@Data
public class CoordinatesDto implements Serializable {
    private Double x;
    private Double y;

    public CoordinatesDto(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }
}
