package ru.vasic2000.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        imgRoundCorners();

        FullScreen();

        previewDialog();

        backFromPreview();

        backFromLevel();
    }

    private void backFromLevel() {
        Button btn_Back = findViewById(R.id.btnBack);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Level1.this, GameLevels.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception exc) {
//                Здесь кода нет
                    }
                    dialog.dismiss();
                }
        });
    }

    private void backFromPreview() {
        Button btnContinue = dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void previewDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрытие заголовка
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false);  //окно нельзя закрыыть кнопкой назад

        textBtnBack();
        
        buttonContinue();

        dialog.show();
    }

    private void buttonContinue() {
        Button btnContinue = dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void textBtnBack() {
        TextView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
//                Здесь кода нет
                }
                dialog.dismiss();
            }
        });
    }

    private void imgRoundCorners() {
        final ImageView imgLeft = findViewById(R.id.imgLeft);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imgLeft.setClipToOutline(true);
        }
        final ImageView imgRight = findViewById(R.id.imgRight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imgRight.setClipToOutline(true);
        }
    }

    private void FullScreen() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
