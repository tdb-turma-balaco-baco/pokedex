package br.ufpr.tads.mobile.pokedex.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageHelper {

    public static String encodeBitmapToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64ToBitmap(String base64) {
        byte[] decoded = Base64.decode(base64, 0);
        return BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
    }

    public static String recuperarAbsolutePathImagem(File[] galeria, String nomeArquivo) {
        for (File f : galeria) {
            if (f.getName().equals(nomeArquivo)) {
                Log.i("FOTO CAMERA", "onActivityResult 0: " + f.getName());
                return f.getAbsolutePath();
            }
        }
        return ("");
    }
}
