package meher.suraj.emicalculator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> //implements Filterable

{

    private Context context;
    private ArrayList<Calc_data> listContacts;
    private ArrayList<Calc_data> mArrayList;

    private HistorySQLiteConnection mDatabase;

    long days = 0;
    int year, months, day;
    String str_amount, str_tot_int, str_tot_amt,time;

    public HistoryAdapter(Context context, ArrayList<Calc_data> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList=listContacts;
        mDatabase = new HistorySQLiteConnection(context);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        final Calc_data contacts = listContacts.get(position);

       /* Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(c.getTime());*/
        holder.date.setText(contacts.getDate());
        holder.amount.setText(contacts.getAmount());
        holder.r_int.setText(contacts.getR_int());
        holder.duration.setText(contacts.getDuration());
        holder.int_per_month.setText(contacts.getM_install());
        holder.tot_amt.setText(contacts.getTot_amt());
        holder.tot_amt_extra.setText(contacts.getExt_amt());

        holder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteContact(contacts.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = "\u20B9";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");

                holder.date.setText(contacts.getDate());
                holder.amount.setText(contacts.getAmount());
                holder.r_int.setText(contacts.getR_int());
                holder.duration.setText(contacts.getDuration()+" months");
                holder.int_per_month.setText(contacts.getM_install());
                holder.tot_amt.setText(contacts.getTot_amt());
                holder.tot_amt_extra.setText(contacts.getExt_amt());

                sendIntent.putExtra(Intent.EXTRA_TEXT, "Calculate Date :"+contacts.getDate() +"\n"+"Amount :"+string+" "+contacts.getAmount()+"\n"+ "Rate of Interest :"+" "+contacts.getR_int()+"\n"+
                        "Duration :"+contacts.getDuration()+" months"+"\n"+"Monthly Instalment :"+string+" "+contacts.getM_install() +"\n"+"Total Amount :"+string+" "+contacts.getTot_amt() +"\n"+"Total Extra Amount :"+string+" "+contacts.getExt_amt() );
                context.startActivity(sendIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }
}