package unknown.backend.dev.dto;

import lombok.Data;

@Data
public class TranslationRequest {
    private String text;
    private String targetLang;

    public TranslationRequest(String text, String targetLang) {
        this.text = text;
        this.targetLang = targetLang;
    }
}
