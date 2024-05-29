package ru.rendaxx.lab8server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.rendaxx.lab8server.dto.CoordinatesDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coordinates")
@Transactional
public class Coordinates implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(CoordinatesDto coordinatesDto) {
        this(coordinatesDto.getX(), coordinatesDto.getY());
    }
}
