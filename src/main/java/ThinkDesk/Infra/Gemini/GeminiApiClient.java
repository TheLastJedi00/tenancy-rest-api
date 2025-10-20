package ThinkDesk.Infra.Gemini;

import ThinkDesk.Infra.Gemini.DTO.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeminiApiClient {

    private final Client geminiClient;

    @Value("${gemini.api.model}")
    private String modelName;

    @Autowired
    public GeminiApiClient(Client geminiClient) {
        this.geminiClient = geminiClient;
    }

    public GeminiResponseDTO translate(String text) {
        String prompt =
                "Se esse chamado estiver muito técnico, traduza brevemente os termos técnicos para se tornar mais compreensível, " +
                "se caso estiver muito vago avalie e encontre alguma lógica. " +
                "Em ambos os casos descreva, de forma direta e objetiva, como proceder para atender esse chamado: " +
                "(escreva em um texto curto)" + text;

        try {
            GenerateContentResponse response = this.geminiClient.models.generateContent(
                    modelName,
                    prompt,
                    null
            );

            String responseText = response.text();

            if (responseText.length() > 255) {
                responseText = responseText.substring(0, 255);
            }

            return new GeminiResponseDTO(responseText);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API do Gemini: " + e.getMessage(), e);
        }
    }

}
