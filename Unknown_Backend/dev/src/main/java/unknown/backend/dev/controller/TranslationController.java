package unknown.backend.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unknown.backend.dev.dto.TranslationRequest;
import unknown.backend.dev.service.TranslationService;

@RestController
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping("/translate")
    public String translate(@RequestBody TranslationRequest request) {
        String inputText = request.getText();
        String targetLang = request.getTargetLang();
        String translatedText = translationService.translateToKorean(inputText);

        return translatedText;
    }
}
