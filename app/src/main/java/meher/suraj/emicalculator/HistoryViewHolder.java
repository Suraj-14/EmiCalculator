package meher.suraj.emicalculator;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

        public TextView amount,tot_amt,int_per_month,duration,r_int,date,tot_amt_extra;
        public ImageView deleteContact;
        public ImageView editContact;
        public ImageView share;

    public HistoryViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.c_time);
            amount = (TextView) itemView.findViewById(R.id.tv_amt);
            r_int= (TextView) itemView.findViewById(R.id.tv_r_int);
            duration=itemView.findViewById(R.id.time);
            int_per_month= (TextView) itemView.findViewById(R.id.tv_tot_int);
            tot_amt = (TextView) itemView.findViewById(R.id.tv_tot_amt);
            tot_amt_extra = (TextView) itemView.findViewById(R.id.tv_tot_amt_extra);

            deleteContact = (ImageView) itemView.findViewById(R.id.delete_contact);
            //editContact = (ImageView) itemView.findViewById(R.id.edit_contact);
            share = (ImageView) itemView.findViewById(R.id.share);
        }
    }
