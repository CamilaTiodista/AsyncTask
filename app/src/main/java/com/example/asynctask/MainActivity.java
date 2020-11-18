package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://i.pinimg.com/originals/25/d6/7e/25d67e1ab8761a9fce706554245b2bdb.png";
    private ProgressBar progress;
    private ImageView imgView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);

        //faz o download
        downloadImagem();
    }

    //Faz o download da imagem em uma nova thread
    private void downloadImagem(){
        //Cria uma asynctask
        DownloadTask task = new DownloadTask();
        //Executa a task/thread
        task.execute();
    }


    private class DownloadTask extends AsyncTask<Void,Void,Bitmap> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }


        @SuppressLint("LongLogTag")
        @Override
        protected Bitmap doInBackground(Void... params){
            publishProgress();
            //Faz o download em uma thread e retorna o bitmap
            try {
                bitmap = Download.downloadBitmap(URL);
                } catch (Exception e) {
                Log.e("Erro ao fazer o download: ", e.getMessage(), e);
            }

            return bitmap;
        }

        protected void onProgressUpdate (Void... progress){
            super.onProgressUpdate();
            Toast.makeText(MainActivity.this, "Carregando", Toast.LENGTH_SHORT).show();
            Log.i("Carregando", "Carregando");
        }


        protected void onPostExecute(Bitmap bitmap){
            if(bitmap != null){
                //atualiza a imagem na UI Thread
                imgView.setImageBitmap(bitmap);
                //esconde o progress
                progress.setVisibility(View.INVISIBLE);
            }
        }
    }
    public Context getContext() {return this;}
}