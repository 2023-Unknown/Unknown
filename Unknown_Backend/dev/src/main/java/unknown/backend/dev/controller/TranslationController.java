package unknown.backend.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unknown.backend.dev.dto.TranslationRequest;
import unknown.backend.dev.service.TranslationService;

@RestController
@RequestMapping("/api/v1")
public class TranslationController {

    private final TranslationService translationService;
    @Autowired
    public TranslationController(TranslationService translationService){
        this.translationService = translationService;
    }
    @PostMapping("/translate")
    public String translate(@RequestBody TranslationRequest request) {
        String[] inputTextArray = request.getText();
        String targetLang = request.getTarget_lang();
        return translationService.translate(inputTextArray,targetLang);
    }
}
