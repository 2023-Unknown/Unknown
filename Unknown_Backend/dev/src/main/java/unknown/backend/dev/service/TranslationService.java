package unknown.backend.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import unknown.backend.dev.dto.TranslationRequest;
import unknown.backend.dev.dto.TranslationResponse;

@Service
@Slf4j

public class TranslationService {

    @Value("${deepl.api.key}")  // application.properties에서 DeepL API 키
    private String apiKey;
    private final String apiUrl = "https://api-free.deepl.com/v2/translate";

    private final RestTemplate restTemplate;
    @Autowired
    public TranslationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translateToKorean(String inputText) {
        String targetLanguage = "KO";
        String[] texts = {inputText};

        TranslationRequest request = new TranslationRequest(texts, targetLanguage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + apiKey);

        HttpEntity<TranslationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<TranslationResponse> responseEntity = restTemplate.postForEntity(apiUrl, entity, TranslationResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TranslationResponse translationResponse = responseEntity.getBody();
            if (translationResponse != null && translationResponse.getTranslations() != null && !translationResponse.getTranslations().isEmpty()) {
                return translationResponse.getTranslations().get(0).getText();
            }
        }

        return "번역 실패";

    }

    public String translateToEnglish(String inputText) {
        String targetLanguage = "EN";
        String[] texts = {inputText};

        TranslationRequest request = new TranslationRequest(texts, targetLanguage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + apiKey);

        HttpEntity<TranslationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<TranslationResponse> responseEntity = restTemplate.postForEntity(apiUrl, entity, TranslationResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TranslationResponse translationResponse = responseEntity.getBody();
            if (translationResponse != null && translationResponse.getTranslations() != null && !translationResponse.getTranslations().isEmpty()) {
                return translationResponse.getTranslations().get(0).getText();
            }
        }

        return "번역 실패";
    }
}