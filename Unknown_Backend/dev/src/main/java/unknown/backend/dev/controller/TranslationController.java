package unknown.backend.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unknown.backend.dev.dto.TranslationRequest;
import unknown.backend.dev.service.TranslationService;

@RestController
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping("/toKoreantranslate")
    public String translateToKorean(@RequestBody TranslationRequest request) {
        String[] inputTextArray = request.getText();
        String target_Lang = request.getTarget_lang();
        String inputText = String.join(" ", inputTextArray);
        String translatedText = translationService.translateToKorean(inputText);

        return translatedText;
    }

    @PostMapping("/toEnglishtranslate")
    public String translateToEnglish(@RequestBody TranslationRequest request) {
        String[] inputTextArray = request.getText();
        String target_Lang = request.getTarget_lang();
        String inputText = String.join(" ", inputTextArray);
        String translatedText = translationService.translateToEnglish(inputText);

        return translatedText;
    }
}
