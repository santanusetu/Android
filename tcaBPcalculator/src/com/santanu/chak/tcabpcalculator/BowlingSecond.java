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

public class BowlingSecond extends Activity
{
	Button targetEnteredBowl;
	EditText targetTextBowl;
	
	int targetBowl = 0;
	
	Double winbp1Bowl, winbp2Bowl, winbp4Bowl;
	Double lossbp1Bowl, lossbp2Bowl, lossbp4Bowl;
	
	TextView wbptv1Bowl,wbptv2Bowl,wbptv4Bowl, lbptv1Bowl, lbptv2Bowl, lbptv4Bowl;
	
	TextView opRRBowl;
	LinearLayout displayResultBowl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_bowling);
		
		targetEnteredBowl = (Button)findViewById(R.id.btSubmitTarget);
		displayResultBowl = (LinearLayout)findViewById(R.id.llCalcu);
		targetTextBowl = (EditText)findViewById(R.id.etTarget);
		
		opRRBowl = (TextView)findViewById(R.id.tvOppRR);
		
		wbptv1Bowl = (TextView) findViewById(R.id.tvWinBp1);
		wbptv2Bowl = (TextView) findViewById(R.id.tvWinBp2);
		wbptv4Bowl = (TextView) findViewById(R.id.tvWinBp4);
		
		lbptv1Bowl = (TextView) findViewById(R.id.tvLossBp1);
		lbptv2Bowl = (TextView) findViewById(R.id.tvLossBp2);
		lbptv4Bowl = (TextView) findViewById(R.id.tvLossBp4);
		
		targetEnteredBowl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(targetTextBowl.getText().toString().trim().length() > 0 )
				{
				String strTarget = targetTextBowl.getText().toString();
				targetBowl = Integer.parseInt(strTarget);
				
				displayResultBowl.setVisibility(View.VISIBLE);
				System.out.println("@@@ Score entered is "+targetBowl);
				
				Double oppRR = (targetBowl/20.0);  
				opRRBowl.setText(" Your Run Rate is                      "+ oppRR +"   runs/over ");
				
				winbp1Bowl = ((oppRR - 1) * 20);
				int run1 = (int)Math.ceil(winbp1Bowl);
				//winbp1Bowl = Double.parseDouble(new DecimalFormat("##.#").format(winbp1Bowl));
				wbptv1Bowl.setText("To get bonus point 1 restrict to           "+run1+"  runs ");
		
				winbp2Bowl = ((oppRR - 2) * 20);
				int run2 = (int)Math.ceil(winbp2Bowl);
				//winbp2Bowl = Double.parseDouble(new DecimalFormat("##.#").format(winbp2Bowl));
				wbptv2Bowl.setText("To get bonus point 2 restrict to           "+run2+"  runs ");
				
				winbp4Bowl = ((oppRR - 3) * 20);
				int run4 = (int)Math.ceil(winbp4Bowl);
				//winbp4Bowl = Double.parseDouble(new DecimalFormat("##.#").format(winbp4Bowl));
				wbptv4Bowl.setText("To get bonus point 4 restrict to           "+run4+"  runs ");
				
				lossbp1Bowl = (targetBowl / (oppRR + 0.74));						
				Double runBp1 = Double.parseDouble(new DecimalFormat("##.#").format(lossbp1Bowl)); 
				//(int)Math.ceil(lossbp1Bowl);
				lbptv1Bowl.setText("To get bonus point 1 take game till            "+runBp1+" overs ");
				
				lossbp2Bowl = (targetBowl / (oppRR + 0.49));
				Double runBp2 = Double.parseDouble(new DecimalFormat("##.#").format(lossbp2Bowl));
				//int runBp2 = (int)Math.ceil(lossbp2Bowl);
				lbptv2Bowl.setText("To get bonus point 2 take game till            "+runBp2+" overs ");
				
				lossbp4Bowl = (targetBowl / (oppRR + 0.24));
				Double runBp3 = Double.parseDouble(new DecimalFormat("##.#").format(lossbp4Bowl));
				//int runBp3 = (int)Math.ceil(lossbp4Bowl);
				lbptv4Bowl.setText("To get bonus point 4 take game till            "+runBp3+" overs ");
			}// end of if
				else{
					Toast.makeText(BowlingSecond.this, "Please Enter your Score...", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
}
