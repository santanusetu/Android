package com.faiz.drinkndrive;

import java.text.DecimalFormat;
import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText enteredWeight;
	RadioGroup weightType;
	RadioGroup sex;
	Button minus, plus;
	EditText noOfDrinks;
	Spinner hour, minute;
	RadioGroup amPmGroup;
	Button canI;
	double gendenCons = 0.0, metabolism;
	double weightInLbs = 0.0, drinkNoInNumber = 0.5;
	ImageView ivInfo, ivLaw;
	Typeface type, type_regular;
	private static final int CONTACT_PICKER_RESULT = 1001;
	RadioButton weightTypeRadio, sexTypeRadio, ampmRadio;
	String msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		enteredWeight = (EditText) findViewById(R.id.etWeight);
		weightType = (RadioGroup) findViewById(R.id.rgWeightType);
		sex = (RadioGroup) findViewById(R.id.rgSex);
		minus = (Button) findViewById(R.id.btMinus);
		noOfDrinks = (EditText) findViewById(R.id.etNoofDrinks);
		plus = (Button) findViewById(R.id.btPlus);
		hour = (Spinner) findViewById(R.id.spHour);
		minute = (Spinner) findViewById(R.id.spMinute);
		amPmGroup = (RadioGroup) findViewById(R.id.rgTime);
		canI = (Button) findViewById(R.id.btCanI);
		ivInfo = (ImageView) findViewById(R.id.ivInfo);
		ivLaw = (ImageView) findViewById(R.id.ivLaw);
		type = Typeface
				.createFromAsset(getAssets(), "fonts/Exo_ExtraLight.ttf");
		minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String drinkNo = noOfDrinks.getText().toString();
				drinkNoInNumber = Double.parseDouble(drinkNo);
				if (drinkNoInNumber > 0.5) {
					drinkNoInNumber = drinkNoInNumber - 0.5;
					noOfDrinks.setText("" + drinkNoInNumber);
				}

			}
		});
		ivInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myAlertBox("1 Drink = <br>1.25 oz. Brandy<br>1.25 oz. Liquor/Mix<br>12 oz. Beer<br>7 oz. Malt<br>4-5 oz. Wine<br>10 oz. Wine Cooler");
			}
		});
		ivLaw.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myAlertBox("Under Section 185 of<br>Indian Motor Vehicles Act, 1988,<br>anything more than 30 mg of alcohol in<br>100 ml of blood (.03% of B.A.C.) for the<br>first time would invite penalty in the form of imprisonment<br>for a term upto 6 months or with fine<br>upto Rs. 2000 or both.<br>Repeated offenders are punishable with imprisonment<br>for a term upto 2 years or with fine upto Rs.3000/- or with both.");
			}
		});

		plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String drinkNo = noOfDrinks.getText().toString();
				drinkNoInNumber = Double.parseDouble(drinkNo);
				drinkNoInNumber = drinkNoInNumber + 0.5;
				noOfDrinks.setText("" + drinkNoInNumber);
			}
		});

		// String weight;
		// int weightInNum;

		canI.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String weight = enteredWeight.getText().toString();
				if (!weight.equalsIgnoreCase("")) {
					int weightInNum = Integer.parseInt(weight);
					Log.i("EditText", "Weight is :: " + weightInNum);

					// get selected radio button from radioGroup
					int selectedWeightId = weightType.getCheckedRadioButtonId();
					weightTypeRadio = (RadioButton) findViewById(selectedWeightId);
					Log.i("RadioGroup", "@@@@ Selected WEIGHT id is :: "
							+ weightTypeRadio.getText());
					CharSequence weightType = weightTypeRadio.getText();

					if (weightType.equals("Kgs")) {
						weightInLbs = weightInNum * 1.0;
					} else {
						weightInLbs = weightInNum * 0.45359237;
					}

					// get selected radio button from radioGroup
					int selectedSexId = sex.getCheckedRadioButtonId();
					sexTypeRadio = (RadioButton) findViewById(selectedSexId);
					Log.i("RadioGroup", "@@@@ Selected SEX id is :: "
							+ sexTypeRadio.getText());
					// men is .73 and for women is .66.
					if (sexTypeRadio.getText().equals("Male")) {
						gendenCons = 0.58;
						metabolism = 0.015;
					} else {
						gendenCons = 0.49;
						metabolism = 0.017;
					}

					String drinkNo = noOfDrinks.getText().toString();
					drinkNoInNumber = Double.parseDouble(drinkNo);

					// get selected radio button from radioGroup
					int selectedAmpm = amPmGroup.getCheckedRadioButtonId();
					ampmRadio = (RadioButton) findViewById(selectedAmpm);
					Log.i("RadioGroup", "@@@@ Selected AMPM id is :: "
							+ ampmRadio.getText());

					String hourValue = String.valueOf(hour.getSelectedItem());
					String minuteValue = String.valueOf(minute
							.getSelectedItem());

					Calendar c = Calendar.getInstance();
					int hourNow = c.get(Calendar.HOUR_OF_DAY);
					int hourDiff = 0;
					int hourValueEntered = Integer.parseInt(hourValue);

					Log.i("hourNow", "@@@@@@@@@@@@ hourNow :: " + hourNow);
					Log.i("hourNow", "@@@@@@@@@@@@ hourValueEntered "
							+ hourValueEntered);

					if (ampmRadio.getText().equals("AM")) {
						hourValueEntered = hourValueEntered;
					} else {
						hourValueEntered = hourValueEntered + 12;
					}

					Log.i("hourNow", "@@@@@@@@@@@@ hourValueEntered modi "
							+ hourValueEntered);
					if (hourNow > hourValueEntered) {
						hourDiff = hourNow - hourValueEntered;
					} else if (hourNow < hourValueEntered) {
						hourDiff = (hourNow + 24) - hourValueEntered;
					}
					Log.i("EditText", "@@@@ hourDiff is :: " + hourDiff);

					Double bacCount = 0.0;

					// http://www.progressive.com/vehicle-resources/blood-alcohol-calculator/
					bacCount = calculateBAC(drinkNoInNumber, weightInLbs,
							gendenCons, hourDiff);
					String dd = new DecimalFormat("#.###").format(bacCount);
					double dddd = Double.parseDouble(dd);
					bacCount = dddd;
					System.out.println("ddddddddddddddddddddddddd========="
							+ dddd);

					final Dialog dialog1 = new Dialog(MainActivity.this);
					dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog1.setContentView(R.layout.result_dialog);

					Button ok = (Button) dialog1.findViewById(R.id.button1);
					Button no = (Button) dialog1.findViewById(R.id.button2);
					Button yes = (Button) dialog1.findViewById(R.id.button3);
					ImageView ivLaw = (ImageView) dialog1
							.findViewById(R.id.ivLaw);
					TextView tv = (TextView) dialog1.findViewById(R.id.tv1);
					if (bacCount >= 0.03) {
						ivLaw.setImageResource(R.drawable.wine3);
						yes.setVisibility(view.GONE);
						System.out.println("bacCount=========" + bacCount);
						msg = "Your BAC ="
								+ bacCount
								+ "<br>Under Indian Motor Vehicles Act<br>If you will drive<br>You would invite penalty";
					} else {
						System.out.println("BAC bacCount=" + bacCount);
						msg = "Your BAC ="
								+ bacCount
								+ "<br>You are able to Drive<br>Under Indian Motor Vehicles Act";
						ivLaw.setImageResource(R.drawable.wine7);
						ok.setVisibility(view.GONE);
						no.setVisibility(view.GONE);
						yes.setVisibility(view.VISIBLE);
					}
					tv.setTypeface(type_regular);
					tv.setText(Html.fromHtml(msg));
					dialog1.show();
					ok.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							dialog1.dismiss();
							Intent browserIntent1 = new Intent(
									"android.intent.action.VIEW",
									// Uri.parse("http://www.taxiautofare.com/Bengaluru-taxi-service"));
									Uri.parse("http://www.savaari.com/campaign/india?utm_source=Google&utm_campaign=IndiaDigi&utm_medium=cpc&utm_term=Taxi%20in%20india&Network={Search}&utm_content=37361138409&SiteTarget=&affiliate_id=15&campaign_id=1&adgroup_id=07"));

							startActivity(browserIntent1);
						}
					});
					no.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							dialog1.dismiss();
							Intent contactPickerIntent = new Intent(
									Intent.ACTION_PICK, Contacts.CONTENT_URI);
							startActivityForResult(contactPickerIntent,
									CONTACT_PICKER_RESULT);

						}
					});
					yes.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							dialog1.dismiss();

						}
					});
				} else {
					myAlertBox(" Please Enter Your Weight");
				}
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		String phone = "";
		Cursor contacts = null;
		try {
			if (resultCode == RESULT_OK) {
				switch (requestCode) {
				case CONTACT_PICKER_RESULT:
					// gets the uri of selected contact
					Uri result = data.getData();
					// get the contact id from the Uri (last part is contact id)
					String id = result.getLastPathSegment();
					// queries the contacts DB for phone no
					contacts = getContentResolver().query(Phone.CONTENT_URI,
							null, Phone.CONTACT_ID + "=?", new String[] { id },
							null);
					// gets index of phone no
					int phoneIdx = contacts.getColumnIndex(Phone.DATA);
					if (contacts.moveToFirst()) {
						// gets the phone no
						phone = contacts.getString(phoneIdx);
						Intent iCall = new Intent(Intent.ACTION_CALL,
								Uri.parse("tel:" + phone));
						startActivity(iCall);
					} else {
						// Toast.makeText(this, "error", 100).show();
					}
					break;
				}

			} else {
				// gracefully handle failure
			}
		} catch (Exception e) {
			// Toast.makeText(this, e.getMessage(), 50).show();
		} finally {
			if (contacts != null) {
				contacts.close();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Double calculateBAC(double drinkNo, Double weightInLbs,
			Double genderCons, int hourDiff) {
		Double bac;

		Log.i("ABCD", "@@@@@@@@@@ drinkNo :: " + drinkNo + " weightLbs :: "
				+ weightInLbs + " genderCons :: " + genderCons
				+ " hourDiff :: " + hourDiff + "metabolism :: " + metabolism);
		// http://en.wikipedia.org/wiki/Blood_alcohol_content
		bac = ((0.806 * drinkNo * 1.2) / (weightInLbs * genderCons))
				- (metabolism * hourDiff);
		System.out.println("bac===========================" + bac);
		if (bac < 0) {
			bac = 0.00;
		}
		return bac;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void myAlertBox(String msg) {
		final Dialog dialog1 = new Dialog(MainActivity.this);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog1.setContentView(R.layout.my_custom_popup_alert);

		Button ok = (Button) dialog1.findViewById(R.id.button1);
		TextView tv = (TextView) dialog1.findViewById(R.id.tv1);
		tv.setTypeface(type_regular);
		// articleTextView.setText(Html.fromHtml(textForTextView));
		tv.setText(Html.fromHtml(msg));
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog1.dismiss();

			}
		});
		dialog1.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit_function("Do You Want to Exit ?");
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	public void exit_function(String msg) {
		final Dialog dialog1 = new Dialog(MainActivity.this);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog1.setContentView(R.layout.my_custom_popup_alert);

		Button ok = (Button) dialog1.findViewById(R.id.button1);
		Button no = (Button) dialog1.findViewById(R.id.button2);
		no.setVisibility(View.VISIBLE);
		TextView tv = (TextView) dialog1.findViewById(R.id.tv1);
		tv.setTypeface(type_regular);
		tv.setText(msg);
		dialog1.show();
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog1.dismiss();
				finish();
			}
		});
		no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog1.dismiss();
			}
		});

	}
}
