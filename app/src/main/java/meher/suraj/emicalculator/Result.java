package meher.suraj.emicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity implements View.OnClickListener {
    private TextView amount,tot_int,tot_amt,int_per_day,int_per_month,int_per_year,time,r_int,today,tot_amt_extra;
    private Float str_amount,str_interest,str_tenure,str_per_month;
    private String str_date;
    private int str_time;
    private Button buttonBack, buttonShare;
    private HistorySQLiteConnection mDatabase;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mDatabase = new HistorySQLiteConnection(this);
        amount = (TextView) findViewById(R.id.tv_amt);
        tot_int= (TextView) findViewById(R.id.tv_tot_int);
        r_int= (TextView) findViewById(R.id.tv_r_int);
        tot_amt = (TextView) findViewById(R.id.tv_tot_amt);
        tot_amt_extra = (TextView) findViewById(R.id.tv_tot_amt_extra);
        today = (TextView) findViewById(R.id.c_time);
        time=findViewById(R.id.time);

        int_per_day = (TextView) findViewById(R.id.tv_int_per_day);
        int_per_month= (TextView) findViewById(R.id.tv_int_per_month);
        int_per_year = (TextView) findViewById(R.id.tv_int_per_year);

        buttonBack = findViewById(R.id.back);
        buttonShare =findViewById(R.id.share);
        buttonBack.setOnClickListener(this);
        buttonShare.setOnClickListener(this);

        Intent i=getIntent();
        str_date=i.getStringExtra("date");
        str_amount=i.getFloatExtra("amount",0);
        str_interest=i.getFloatExtra("interest",0);
        str_tenure=i.getFloatExtra("tenure",0);
        str_per_month=i.getFloatExtra("per_month",0);
        str_time= (int) i.getFloatExtra("time",0);

        today.setText(str_date);
        r_int.setText(""+str_interest);
        String string = "\u20B9";

        amount.setText(""+string+" "+str_amount+" /-");
        time.setText(""+str_time+"  "+"months");
        tot_int.setText(""+string+" "+str_per_month+" /-");
        tot_amt .setText( ""+string+" "+str_per_month*str_time+" /-");
        tot_amt_extra .setText( ""+string+" "+((str_per_month*str_time)-str_amount)+" /-");

        int_per_day.setText(""+string+" "+str_per_month/30+" /-");
        int_per_month.setText(""+string+" "+str_per_month+" /-");
        int_per_year.setText( ""+string+" "+str_per_month*12+" /-");

        Calc_data newContact = new Calc_data(str_date, ""+str_amount, ""+str_interest,""+str_time+" months",""+str_per_month,""+str_per_month*str_time,""+((str_per_month*str_time)-str_amount));
        mDatabase.addContacts(newContact);
    }

    @Override
    public void onClick(View v) {
    if(v==buttonBack){
        Intent i=new Intent(Result.this,MainActivity.class);
        startActivity(i);
    }
    if(v==buttonShare){
        String string = "\u20B9";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
       /* sendIntent.putExtra(Intent.EXTRA_TEXT, "Start Date :"+start_date +"\n"+"End date :"+end_date +"\n"+"Time :"+str_time +"\n"+"Amount :"+string+" "+str_amount+"\n"+"Total Interest :"+string+" "+str_tot_int
                +"\n"+ "Total Amount :"+string+" "+str_tot_amt     +"\n"+ "Interest Per Month :"+string+" "+str_int_per_month);*/
        startActivity(sendIntent);

         }
         }
@Override
public void onBackPressed() {
        super.onBackPressed();
        }
        }
