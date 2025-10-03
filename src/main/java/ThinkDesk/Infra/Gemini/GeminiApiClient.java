package ThinkDesk.Infra.Gemini;

import ThinkDesk.Infra.Gemini.DTO.GeminiRequestDTO;
import ThinkDesk.Infra.Gemini.DTO.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeminiApiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public GeminiResponseDTO translate(String text) {
        String url = apiUrl + "?key=" + apiKey;
        GeminiRequestDTO request = new GeminiRequestDTO("Translate the following text to English: " + text);
        return restTemplate.postForObject(url, request, GeminiResponseDTO.class);
    }
}
