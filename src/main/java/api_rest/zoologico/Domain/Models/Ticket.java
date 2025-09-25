package api_rest.zoologico.Domain.Models;

import api_rest.zoologico.Application.DTOs.TicketDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Ticket(TicketDTO dto) {
        this.nome = dto.nome();
    }
}