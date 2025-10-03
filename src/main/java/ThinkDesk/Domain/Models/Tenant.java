package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.CnpjDto;
import ThinkDesk.Application.DTOs.TenantDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tradingName;
    private String legalName;
    private String taxID; //CNPJ
    private LocalDateTime createdAt;
    private boolean active;
    private String settings; //Layout customizations JSON

    public Tenant(TenantDto dto, CnpjDto data) {
        this.tradingName = dto.tradingName();
        this.legalName = data.razaoSocial();
        this.taxID = dto.taxID();
        this.createdAt = dto.createdAt();
        this.active = true;
        this.settings = dto.settings();
    }
}
