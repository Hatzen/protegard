package controller.generative;

import ai.djl.Model;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;

import java.io.IOException;

public class GPT2Adventure {

    public static void main(String[] args) throws IOException, ModelException, TranslateException {
        // Pfad zum lokalen Verzeichnis, wo das Modell gespeichert ist
        String modelDir = "src/main/resources/models/gpt2-large";

        // Lade das GPT-2 Modell aus dem lokalen Verzeichnis
        Model model = Model.newInstance(modelDir, "pytorch");

        // Translator für die Textgenerierung konfigurieren
        Translator<String, String> translator = new Gpt2Translator(100);
                // .optMaxLength(100) // Maximale Länge der generierten Ausgabe
                // .optTokenize(false)
                // .build();

        // Erstelle einen Predictor für die Textgenerierung
        try (Predictor<String, String> predictor = model.newPredictor(translator)) {
            // Eingabetext, den das Modell weiterführen soll
            String input = "Once upon a time";

            // Text generieren
            String result = predictor.predict(input);

            // Ausgabe des generierten Textes
            System.out.println("Generated Text: " + result);
        }
    }
}