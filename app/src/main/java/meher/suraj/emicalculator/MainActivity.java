package meher.suraj.emicalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText amt,time,si;
    TextView emi;
    float a,p,r,n,m,rate;
    float b,pow,e;

    String date;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = (EditText)findViewById(R.id.amt);
        time =(EditText)findViewById(R.id.time);
        si=(EditText)findViewById(R.id.si);
        emi=(TextView)findViewById(R.id.emi);
        Button btn=(Button)findViewById(R.id.button);

        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    p= Float.parseFloat(amt.getText().toString()) ;
                    r= Float.parseFloat(si.getText().toString()) ;
                    n= Float.parseFloat(time.getText().toString()) ;

                    //E = P×r×(1 + r)^n/((1 + r)^n - 1)
                    m=n*12;
                    rate=r/12/100;
                    pow = (float) Math.pow((1+rate),m);
                    a=p*rate*pow;
                    b=pow-1;
                    e=a/b;
                    emi.setText(String.format("%.2f",e));

                    Intent i = new Intent(MainActivity.this, Result.class );
                    i.putExtra("date", date);
                    i.putExtra("amount", p);
                    i.putExtra("interest", r);
                    i.putExtra("tenure", n);
                    i.putExtra("per_month", e);
                    i.putExtra("time", m);
                    startActivity(i);
                    //Toast.makeText(MainActivity.this, ""+e*m, Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    System.out.print(e);
                }

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.history) {
            // Toast.makeText(this, "Clicked " , Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,History.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}