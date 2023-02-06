package br.ufpr.tads.mobile.pokedex.util;

import android.widget.EditText;

public class FormularioHelper {

    private FormularioHelper() {}

    public static String recuperarText(EditText input) {
        String inputText = input.getText().toString().trim();
        return inputText.length() > 0 ? inputText : "";
    }
}
