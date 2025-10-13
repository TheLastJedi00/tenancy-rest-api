package ThinkDesk.Domain.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Technician> members;
}