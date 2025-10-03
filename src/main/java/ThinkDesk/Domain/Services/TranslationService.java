package ThinkDesk.Domain.Services;

import ThinkDesk.Infra.Gemini.GeminiApiClient;
import ThinkDesk.Infra.Gemini.DTO.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Autowired
    private GeminiApiClient geminiApiClient;

    public String translate(String text) {
        GeminiResponseDTO response = geminiApiClient.translate(text);
        if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
            return response.getCandidates().get(0).getContent().getParts().get(0).getText();
        }
        return "Translation not available";
    }
}
