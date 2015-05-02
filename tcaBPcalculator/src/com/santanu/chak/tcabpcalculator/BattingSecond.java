package com.santanu.chak.tcabpcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BattingSecond extends Activity
{
	Button targetEntered;
	EditText targetText;
	
	int target = 0;
	
	Double winbp1, winbp2, winbp4;
	Double lossbp1, lossbp2, lossbp4;
	
	TextView wbptv1,wbptv2,wbptv4, lbptv1, lbptv2, lbptv4;
	
	TextView opRR;
	LinearLayout displayResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_batting);
		
		targetEntered = (Button)findViewById(R.id.btSubmitTarget);
		displayResult = (LinearLayout)findViewById(R.id.llCalcu);
		targetText = (EditText)findViewById(R.id.etTarget);
		
		opRR = (TextView)findViewById(R.id.tvOppRR);
		
		wbptv1 = (TextView) findViewById(R.id.tvWinBp1);
		wbptv2 = (TextView) findViewById(R.id.tvWinBp2);
		wbptv4 = (TextView) findViewById(R.id.tvWinBp4);
		
		lbptv1 = (TextView) findViewById(R.id.tvLossBp1);
		lbptv2 = (TextView) findViewById(R.id.tvLossBp2);
		lbptv4 = (TextView) findViewById(R.id.tvLossBp4);
		
		targetEntered.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(targetText.getText().toString().trim().length() > 0 )
				{
				String strTarget = targetText.getText().toString();
				target = Integer.parseInt(strTarget);
				
				displayResult.setVisibility(View.VISIBLE);
				System.out.println("@@@ Target entered is "+target);
				
				Double oppRR = (target/20.0);  
				opRR.setText(" Opponent Run Rate is                      "+ oppRR +"   runs/over ");
				
				winbp1 = (target / (oppRR+1));
				winbp1 = Double.parseDouble(new DecimalFormat("##.#").format(winbp1));
				wbptv1.setText("To get bonus point 1 win before           "+winbp1+"  overs ");
		
				winbp2 = (target / (oppRR+2));
				winbp2 = Double.parseDouble(new DecimalFormat("##.#").format(winbp2));
				wbptv2.setText("To get bonus point 2 win before           "+winbp2+"  overs ");
				
				winbp4 = (target / (oppRR+3));
				winbp4 = Double.parseDouble(new DecimalFormat("##.#").format(winbp4));
				wbptv4.setText("To get bonus point 4 win before           "+winbp4+"  overs ");
				
				lossbp1 = ((oppRR - 0.74) * 20);
				int runBp1 = (int)Math.ceil(lossbp1);
				lbptv1.setText("To get bonus point 1 score                 "+runBp1+" runs ");
				
				lossbp2 = ((oppRR - 0.49) * 20);
				int runBp2 = (int)Math.ceil(lossbp2);
				lbptv2.setText("To get bonus point 2 score                 "+runBp2+" runs ");
				
				lossbp4 = ((oppRR - 0.24) * 20);
				int runBp3 = (int)Math.ceil(lossbp4);
				lbptv4.setText("To get bonus point 4 score                 "+runBp3+" runs ");
				}else{
					Toast.makeText(BattingSecond.this, "Please Enter opponent's Score...", Toast.LENGTH_LONG).show();
				}
			
			}
		});
	}
	
}
