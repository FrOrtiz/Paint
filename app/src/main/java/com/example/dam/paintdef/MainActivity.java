package com.example.dam.paintdef;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button btPath;
    private android.widget.Button btRect;
    private android.widget.Button btCircle;
    private android.widget.Button btStroke;
    private android.widget.Button btFill;
    private android.widget.Button btNarrow;
    private android.widget.Button btWider;
    private android.widget.Button btBlack;
    private android.widget.Button btBlue;
    private android.widget.Button btRed;
    private android.widget.Button btWhite;
    private android.widget.Button btLine;
    private android.widget.TextView tvInfo;

    static Modo modo = Modo.LIBRE;

    enum Modo {
        LINEA, RECTANGULO, CIRCULO, LIBRE
    }

    private void events(){
        btLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modo = Modo.LINEA;
                pintaInfo();
            }
        });

        btPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modo = Modo.LIBRE;
                pintaInfo();
            }
        });

        btCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modo = Modo.CIRCULO;
                pintaInfo();
            }
        });

        btRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modo = Modo.RECTANGULO;
                pintaInfo();
            }
        });

        btStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.style = Paint.Style.STROKE;
                pintaInfo();
            }
        });

        btFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.style = Paint.Style.FILL;
                pintaInfo();
            }
        });

        btNarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PaintView.strokeWidth >= 5) {
                    PaintView.strokeWidth = PaintView.strokeWidth - 5;
                }
                pintaInfo();
            }
        });

        btWider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.strokeWidth = PaintView.strokeWidth + 5;
                pintaInfo();
            }
        });

        btBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.BLACK;
                pintaInfo();
            }
        });

        btBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.BLUE;
                pintaInfo();
            }
        });

        btRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.RED;
                pintaInfo();
            }
        });

        /*"Goma"*/
        btWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.WHITE;
                pintaInfo();
            }
        });
        pintaInfo();
    }

    private void pintaInfo(){
        switch (PaintView.getColor()){
            case Color.BLACK:
                tvInfo.setText("Informacion del pincel: BLACK" + " " + PaintView.getStyle() + " " + modo + " " + String.valueOf((int) PaintView.strokeWidth));
                break;
            case Color.BLUE:
                tvInfo.setText("Informacion del pincel: BLUE" + " " + PaintView.getStyle() + " " + modo + " " + String.valueOf((int) PaintView.strokeWidth));
                break;
            case Color.RED:
                tvInfo.setText("Informacion del pincel: RED" + " " + PaintView.getStyle() + " " + modo + " " + String.valueOf((int) PaintView.strokeWidth));
                break;
            case Color.WHITE:
                tvInfo.setText("Informacion del pincel: WHITE" + " " + PaintView.getStyle() + " " + modo + " " + String.valueOf((int) PaintView.strokeWidth));
                break;
        }
    }

    private void init(){
        this.tvInfo = findViewById(R.id.tvInfo);
        this.btLine = findViewById(R.id.btLine);
        this.btWhite = findViewById(R.id.btWhite);
        this.btRed = findViewById(R.id.btRed);
        this.btBlue = findViewById(R.id.btBlue);
        this.btBlack = findViewById(R.id.btBlack);
        this.btWider = findViewById(R.id.btWider);
        this.btNarrow = findViewById(R.id.btNarrow);
        this.btFill = findViewById(R.id.btFill);
        this.btStroke = findViewById(R.id.btStroke);
        this.btCircle = findViewById(R.id.btCircle);
        this.btRect = findViewById(R.id.btRect);
        this.btPath = findViewById(R.id.btPath);

        events();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
}
