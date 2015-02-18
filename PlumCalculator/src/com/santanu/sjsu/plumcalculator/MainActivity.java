package com.santanu.sjsu.plumcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener
{

	private TextView mCalculatorDisplay;
	
	private static final String DIGITS = "0123456789";
	//private static final String OPERATORS = "+-";
	//private static final String EQUALS = "=";
	private static final String CLEAR = "C";
	private static final String ADD = "+";
	private static final String SUBTRACT= "-"; 
	
	private int operand = 0;
	private int waitingOperand =0;
	private String waitingOperator= "";
	
	private Boolean IsOperatorButtonPressed =false;
	private Boolean userIsTypingANumber = false;
	private static int PRESSCOUNT = 1;
	
	private static final String TAG = "MainActivity";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
		mCalculatorDisplay = (TextView) findViewById(R.id.tvDisplay);

		// setting up the on click listeners for each calculator button
		findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        
        findViewById(R.id.btnEquals).setOnClickListener(this);
        
        findViewById(R.id.btnClearALl).setOnClickListener(this);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	} //finish of onCreate

	@Override
	public void onClick(View v) 
	{
		try {
			String buttonPressed = ((Button) v).getText().toString();
			
			// CLEAR ALL button functionality
			if (CLEAR.contains(buttonPressed)) 
			{
				performClearOperation();
				PRESSCOUNT = 1;
				Log.i(TAG, "@@ After CLEAR value operand --> "+operand + " waitingOperand --> "+waitingOperand);
			}
			
			// Number button press functionality
			else if (DIGITS.contains(buttonPressed))
			{
				int displayValue = Integer.parseInt(mCalculatorDisplay.getText().toString()); 
				
				if(buttonPressed.equals("0") && (displayValue) == 0)
				{
					Log.i(TAG, "@@ Inserted value is still 0");
				}
				else 
				{
					if(userIsTypingANumber)
					{
						if (displayValue == 0) {
							mCalculatorDisplay.setText(buttonPressed);
						}else{
							PRESSCOUNT++;
							mCalculatorDisplay.append(buttonPressed);
							
							if (PRESSCOUNT<8) {
								Log.i(TAG, "@@ Within digit limits -- presscount value "+PRESSCOUNT);
							}
							else{
								Log.i(TAG, "@@ Exceeded digit limits -- presscount value "+PRESSCOUNT);
								Toast.makeText(getApplicationContext(), "LIMIT REACHED !! YOU HAVE ALREADY ENTERED 7 DIGITS...", Toast.LENGTH_SHORT).show();
							}
						}
					}else{
					
							mCalculatorDisplay.setText(buttonPressed);	
				}
				userIsTypingANumber = true;
				IsOperatorButtonPressed = false;
				}
			}
			
			else {
				PRESSCOUNT = 1;
				Log.i(TAG, "@@ Operator button Pressed :: "+buttonPressed);
				
			//Check if the last button pressed is not also an operator 
				
			 if(! IsOperatorButtonPressed)
			 {
			  IsOperatorButtonPressed = true;
			  if (userIsTypingANumber) 
				{
					operand = Integer.parseInt(mCalculatorDisplay.getText().toString());
					userIsTypingANumber = false;
					
					if (waitingOperator.equals(ADD))
					{
						operand = waitingOperand + operand;
						
						if(operand > 9999999 || operand < -999999){
							Toast.makeText(getApplicationContext(), "SORRY !! THE OPERATION HAS CROSSED 7 DIGITS. RESETTING VALUES...", Toast.LENGTH_SHORT).show();
							performClearOperation();
						}
						else{
							mCalculatorDisplay.setText(""+operand);
						}
					}
					else if(waitingOperator.equals(SUBTRACT))
					{
						operand = waitingOperand - operand;

						if(operand > 9999999 || operand < -999999)
						{
							Toast.makeText(getApplicationContext(), "SORRY !! THE OPERATION HAS CROSSED 7 DIGITS. RESETTING VALUES...", Toast.LENGTH_SHORT).show();
							performClearOperation();
						}
						else	
						{
							mCalculatorDisplay.setText(""+operand);
						}
					}
					
					waitingOperator = buttonPressed;
					waitingOperand = operand;
					Log.i(TAG, "@@ waiting operand value -> "+waitingOperand + "waiting operator value -> "+waitingOperator);
				}
			 }
			 
			 // if it was an operator -- discard the last vale and update the new operator value
			 else if(IsOperatorButtonPressed = true)
			  	{
				  waitingOperator = buttonPressed;
				  Log.i(TAG, "@@ Updated waiting operator -> "+waitingOperator);
			  	}
			  
			}	// finish of operator press
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	// finish of OnCLick

	
	/**
	 * Function that performs CLEAR ALL operation
	 */
	private void performClearOperation() 
	{
		try {
			//taking all the variables to their initial value
			operand = 0;
			waitingOperand = 0;
			waitingOperator = "";
			mCalculatorDisplay.setText("0");
			} catch (Exception e) {
			e.printStackTrace();
			}
	} //finish of performClearOperation

}
