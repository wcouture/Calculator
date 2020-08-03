package main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

/*
    The main controller of the program.
 */

public class Controller {
    @FXML
    Label numDisplay;

    ArrayList<Integer> num1 = new ArrayList<Integer>();
    ArrayList<Integer> num2 = new ArrayList<Integer>();
    double num2Set = 0;
    double num1Set = 0;

    char operator = '!';

    double result =  0;

    boolean num1_IsSet = false;
    boolean firstResult = false;
    boolean inputting = true;

    /*
        Adjusts either the first or secondary number based on the new digit
        the user has pressed.
        @param - the button event
     */
    @FXML
    public void adjustNum(Event e){
        if(!inputting){
            reset();
        }

        Button temp = (Button) e.getSource();
        String btnPressed = temp.getId();
        int digit = (int)btnPressed.charAt(1) - 48;
        String numDisp = "";

        if(!num1_IsSet){
            num1.add(0, digit);
            for(int i = num1.size() - 1; i >= 0; i--) {
                numDisp += num1.get(i);
            }
        }
        else{
            num2.add(0, digit);
            for(int i = num2.size() - 1; i >= 0; i--) {
                numDisp += num2.get(i);
            }
        }

        numDisplay.setText(numDisp);
    }

    /*
        Takes in a base and an exponent and returns the result.
        @param - base num
        @param - exponent num
        @return - resultant of the exponential expression
     */
    private int exponential(int base, int exponent){
        if(exponent == 0){
            return 1;
        }
        else{
            for(int i = 1; i < exponent; i++){
                base *= base;
            }
            return base;
        }
    }

    /*
        Sets the value of either the first or secondary numbers
        @param - button event
     */
    @FXML
    public void setNum(Event e){
        Button temp = (Button) e.getSource();
        String bttnPressed = temp.getId();

        switch(bttnPressed){
            case "mp": operator = '*';
                num2 = new ArrayList<Integer>();
                inputting = true;
                break;
            case "dv": operator = '/';
                num2 = new ArrayList<Integer>();
                inputting = true;
                break;
            case "pl": operator = '+';
                num2 = new ArrayList<Integer>();
                inputting = true;
                break;
            case "mn": operator = '-';
                num2 = new ArrayList<Integer>();
                inputting = true;
                break;
        }

        if(!num1_IsSet){

            for(int i = 0; i < num1.size(); i++) {
                num1Set += num1.get(i) * exponential(10, i);
            }

            num1_IsSet = true;
            numDisplay.setText("0");
        }
        else{
            num2Set = 0;
            for(int i = 0; i < num2.size(); i ++) {
                num2Set += num2.get(i) * exponential(10, i);
            }
            if(bttnPressed.equals("eq")){
                inputting = false;
                operate();
            }
            else{
                inputting = true;
            }
        }
    }

    /*
        Does the specified operation and displays the result.
     */
    private void operate(){
        switch(operator){
            case '+':
                if(!firstResult){
                    result = num1Set + num2Set;
                    firstResult = true;
                }
                else
                    result += num2Set;

                break;
            case '-':
                if(!firstResult){
                    result = num1Set - num2Set;
                    firstResult = true;
                }
                else
                    result -= num2Set;
                break;
            case '*':
                if(!firstResult){
                    result = num1Set * num2Set;
                    firstResult = true;
                }
                else
                    result *= num2Set;
                break;
            case '/':
                if(!firstResult){
                    result = num1Set / num2Set;
                    firstResult = true;
                }
                else
                    result /= num2Set;

                break;
        }
        numDisplay.setText(Double.toString(result));
    }

    /*
        Resets the number variables and state tracking booleans.
     */
    private void reset(){
        System.out.println("Resetting");
        num1_IsSet = false;
        while(num1.size() > 0){
            num1.remove(0);
        }
        while(num2.size() > 0){
            num2.remove(0);
        }
        num1Set = 0;
        num2Set = 0;
        firstResult = false;
        inputting = true;
    }

}
