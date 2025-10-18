package ThinkDesk.Infra.Gemini;

import com.google.genai.types.HttpOptions;
import org.springframework.beans.factory.annotation.Value;
import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfigs {
    @Value("${google.api.key}")
    private String apiKey;

    @Value("${gemini.api.model}")
    private String modelName;

    @Bean
    public Client geminiClient() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("A chave da API (google.api.key) não está configurada.");
        }

        HttpOptions httpOptions = HttpOptions.builder()
                .apiVersion("v1")
                .build();

        return Client.builder().apiKey(apiKey).httpOptions(httpOptions).build();
    }
}
