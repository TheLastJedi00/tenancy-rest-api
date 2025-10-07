package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.CnpjDto;
import ThinkDesk.Infra.Exception.CnpjException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CnpjService {
    private final String url = "https://publica.cnpj.ws/cnpj/";
    private final RestTemplate restTemplate;

    public CnpjService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CnpjDto getTenantByCnpj(String cnpj){
        try {
            return restTemplate.getForObject(url + cnpj, CnpjDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CnpjException("Tax ID not found: " + e);
        }
    }
}
