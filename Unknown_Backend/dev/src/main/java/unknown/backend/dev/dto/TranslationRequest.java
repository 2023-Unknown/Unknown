package unknown.backend.dev.dto;

import lombok.Data;

@Data
public class TranslationRequest {
    private String[] text;
    private String target_lang;

    public TranslationRequest(String[] text, String target_lang) {
        this.text = text;
        this.target_lang = target_lang;
    }
}
