package com.example.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Download {
    public static Bitmap downloadBitmap(String url) throws IOException {
        //faz o download da imagem
        Bitmap bitmap=null;
        InputStream in= new URL(url).openStream();
        //Converte a InputStream do Java para Bitmao
        bitmap = BitmapFactory.decodeStream(in);
        in.close();
        return bitmap;
    }
}