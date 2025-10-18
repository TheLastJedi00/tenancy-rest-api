package ThinkDesk.Domain.Services;

import ThinkDesk.Infra.Gemini.GeminiApiClient;
import ThinkDesk.Infra.Gemini.DTO.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    private final GeminiApiClient geminiApiClient;

    public TranslationService(GeminiApiClient geminiApiClient) {
        this.geminiApiClient = geminiApiClient;
    }

    public String translate(String text) {
        GeminiResponseDTO response = geminiApiClient.translate(text);
        if (response != null && response.message() != null && !response.message().isEmpty()) {
            return response.message();
        }
        return "Translation not available";
    }
}
