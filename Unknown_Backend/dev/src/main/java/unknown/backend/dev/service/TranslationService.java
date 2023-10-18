package unknown.backend.dev.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unknown.backend.dev.dto.TranslationRequest;
import unknown.backend.dev.dto.TranslationResponse;

@Service
@Slf4j
@RequiredArgsConstructor

public class TranslationService {

    @Value("${deepl.api.key}")  // application.properties에서 DeepL API 키
    private String apiKey;
    public String translateToKorean(String inputText) {
        String apiUrl = "https://api.deepl.com/v2/translate";
        String targetLanguage = "KO";

        RestTemplate restTemplate = new RestTemplate();
        // 수정 필요
        String translatedText = restTemplate.postForObject(apiUrl,
                new TranslationRequest(inputText, targetLanguage),
                TranslationResponse.class, apiKey).getTranslations().get(0).getText();

        return translatedText;
    }
}