package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV, resultsTV;
    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }

    private void initTextView() {
        workingsTV = (TextView) findViewById(R.id.workingText);
        resultsTV = (TextView) findViewById(R.id.resultText);
    }

    private void  setWorkings(String givenValues){
        workings = workings + givenValues;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPow();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null){
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }

    }

    private void checkForPow() {

        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i <workings.length(); i++){
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);

        }

        formula = workings;
        tempFormula = workings;

        for(Integer index: indexOfPowers){
            changeFormula(index);
        }
        formula = tempFormula;

    }

    private void changeFormula(Integer index) {

        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i < workings.length(); i++){
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--){
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);

    }

    private boolean isNumeric(char c){
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBrackets = true;
    }

    boolean leftBrackets = true;

    public void bracketsOnClick(View view) {
        if(leftBrackets){
            setWorkings("(");
            leftBrackets = false;
        } else {
            setWorkings(")");
            leftBrackets = true;
        }
    }

    public void powerOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void commaOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }


}