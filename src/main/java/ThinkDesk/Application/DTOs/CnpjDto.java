package ThinkDesk.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CnpjDto(
        @JsonProperty("cnpj_raiz")
        String cnpjRaiz,

        @JsonProperty("razao_social")
        String razaoSocial,

        @JsonProperty("capital_social")
        String capitalSocial,

        @JsonProperty("responsavel_federativo")
        String responsavelFederativo,

        @JsonProperty("criado_em")
        String criadoEm,

        @JsonProperty("atualizado_em")
        String atualizadoEm
) {}
