package controller.generative;

import ai.djl.Model;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.translate.TranslateException;

import java.io.IOException;

public class LlamaExample {
    /*public static void main(String[] args) throws IOException, ModelException, TranslateException {
        // Modell laden
        HuggingFaceGenerativeTranslator translator = HuggingFaceGenerativeTranslator.builder()
                .optTokenizer("meta-llama/Llama-2-7b-hf")
                .build();

        try (Model model = Model.newInstance("Llama")) {
            model.setBlock(translator);
            try (Predictor<String, String> predictor = model.newPredictor(translator)) {
                String output = predictor.predict("What is the capital of France?");
                System.out.println(output);
            }
        }
    }*/
}