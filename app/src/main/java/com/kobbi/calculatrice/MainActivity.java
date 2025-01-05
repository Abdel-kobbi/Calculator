package com.kobbi.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private TextView output;
    private double number1;
    private double number2;
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
    }

    public void clear(View v){
        output.setText("");
        number1 = 0;
        number2 = 0;
    }

    public void getNumber(View view){
        // btn of numbers
        Button btnNumber = (Button) view;
        String number = btnNumber.getText().toString();
        String newValue = output.getText().toString() + number;
        output.setText(newValue);
    }

    public void dot(View v){
        String number = output.getText().toString();
        if(number.isEmpty()){
            output.setText("0.");
        }else if(!number.contains(".")){
            String newValue = output.getText().toString() + ".";
            output.setText(newValue);
        }
    }

    public void getOperation(View view){
        try {
            // btn of operations mathematics
            Button btnOp = (Button) view;
            operation = btnOp.getText().toString();
            number1 = Double.parseDouble(output.getText().toString());
            output.setText("");
        } catch (Exception ignored) {
            clear(view);
        }

    }

    public void equal(View view){
        try {
            number2 = Double.parseDouble(output.getText().toString());
            double res = 0;
            switch (operation){
                case "+":
                    res = number1 + number2;
                    break;
                case "-":
                    res = number1 - number2;
                    break;
                case "X":
                    res = number1 * number2;
                    break;
                case "/":
                    if(number2 == 0){
                        throw new ArithmeticException("On ne peut pas diviser par 0");
                    }
                    res = number1 / number2;
                    break;
            }

            if(isInteger(res)){
                output.setText(String.valueOf((int)res));
            }else{
                output.setText(String.valueOf(res));
            }
        } catch (ArithmeticException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            clear(view);
        } catch (Exception ignored){
            clear(view);
        }finally {
            number1 = 0;
            number2 = 0;
        }

    }

    private boolean isInteger(double number) {
        return number % 1 == 0;
    }

    public void percentage(View view){
        try {
            String value = output.getText().toString();
            double number = Double.parseDouble(value);
            double res = 0;
            if(number1 != 0){
                switch (operation){
                    case "+":
                        res = number1 + (number1 * number / 100) ;
                        break;
                    case "-":
                        res = number1 - (number1 * number / 100);
                        break;
                    case "X":
                        res = (number1 * number / 100);
                        break;
                    case "/":
                        res = (number1 / number / 100);
                        break;
                }
            }else {
                res = number / 100;
            }
            output.setText(isInteger(res) ? String.valueOf((int)res) : String.valueOf(res));
        } catch (Exception ignored) {
            clear(view);
        }

    }

    public void delete(View view){
        try {
            int length = output.getText().toString().length() - 1;
            String newText = output.getText().toString().substring(0,length);
            output.setText(newText);
        }catch (Exception ignored){
            clear(view);
        }
    }

}