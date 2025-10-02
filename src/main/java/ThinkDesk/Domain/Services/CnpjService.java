package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.CnpjDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CnpjService {
    private final String url = "https://cnpj.ws/cnpj/";
    private final RestTemplate restTemplate;

    public CnpjService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CnpjDto getTenantByCnpj(String cnpj){
        return restTemplate.getForObject(url + cnpj, CnpjDto.class);
    }
}
