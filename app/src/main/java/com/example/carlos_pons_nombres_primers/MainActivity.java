package com.example.carlos_pons_nombres_primers;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends Activity {
    private HashMap <Integer, Integer> primos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.language_es:
                        changeText(1);
                        return true;
                    case R.id.language_ca:
                        System.out.println("XD");
                        changeText(2);
                        return true;
                    case R.id.language_en:
                        changeText(3);
                        return true;
                }
                return false;
            }
        });
        primos = new HashMap<>();

    }

    public void x(View view) {
        TextView t = findViewById(R.id.textNumSelected);
        TextView t2 = findViewById(R.id.textNumFound);
        int num;
        try {
            num = Integer.parseInt(t.getText().toString());
            if (num > 9999 || num == 0) {
                Toast.makeText(this, "Porfavor Introduce un numero valido (1-9999)", Toast.LENGTH_SHORT).show();
            } else {
                if (primos.isEmpty()){
                    Toast.makeText(this, "Calculando...", Toast.LENGTH_SHORT).show();
                    calculatePrimeNumber(t2, num);
                }else {
                    if (primos.containsKey(num)) {
                        Toast.makeText(this, "Calculando...", Toast.LENGTH_SHORT).show();
                        t2.setText(primos.get(num) + "");
                    } else {
                        Toast.makeText(this, "Calculando...", Toast.LENGTH_SHORT).show();
                        calculatePrimeNumber(t2, num);
                    }
                }
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "Porfavor Introduce un numero valido (1-9999)", Toast.LENGTH_SHORT).show();
        }

    }

    public void calculatePrimeNumber(TextView t, int nPos){
        int cnp = 0;//contador numeros primos
        int num = 2;
        int numeroPrimo;
        while (cnp!=nPos){
            if (isPrimeNumber(num)){
                cnp++;
                numeroPrimo = num;
                primos.put(cnp, numeroPrimo);
            }
            num++;
        }
        System.out.println(cnp);
        t.setText(primos.get(cnp) + "");
    }

    public boolean isPrimeNumber(int num){
        int lastDigit = num%10;
        if (num==2 || num==5){
            return true;
        }
        if (lastDigit==0 || lastDigit==2 || lastDigit==4 || lastDigit==5 || lastDigit==6 ||
                lastDigit==8) {
            return false;
        }
        for (int i = 2; i < num / 2; i++) {
            if ((num % i)==0)
                return false;
        }
        return true;
    }
    private void changeText(int i) {
        TextView t = findViewById(R.id.textViewDesc);
        TextView t2 = findViewById(R.id.textViewDescNums);
        Button b = findViewById(R.id.buttonCalculate);

        if (i==1){//language_es
            t.setText(getString(R.string.calculatorDes_es));
            t2.setText(getString(R.string.numToEnter_es));
            b.setText(getString(R.string.calculate_es));

        }else if (i==2){//language_cat
            t.setText(getString(R.string.calculatorDes_ca));
            t2.setText(getString(R.string.numToEnter_ca));
            b.setText(getString(R.string.calculate_ca));
        }else{//language_en
            t.setText(getString(R.string.calculatorDes_en));
            t2.setText(getString(R.string.numToEnter_en));
            b.setText(getString(R.string.calculate_en));
        }
    }
}