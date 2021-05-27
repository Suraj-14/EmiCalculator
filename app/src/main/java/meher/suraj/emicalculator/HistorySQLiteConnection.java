package meher.suraj.emicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HistorySQLiteConnection extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	1;
    private	static final String DATABASE_NAME = "History.db";
    private	static final String TABLE_NAME = "History";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Date = "date";
    private static final String COLUMN_Amount = "amount";
    private static final String COLUMN_R_Interest = "interest";
    private static final String COLUMN_Duration = "duration";
    private static final String COLUMN_M_Installment = "monthly_installment";
    private static final String COLUMN_Tot_Amt = "tot_amt";
    private static final String COLUMN_Extra_Amt = "extra_amount";

    public HistorySQLiteConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE	TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_Date + " TEXT," + COLUMN_Amount + " TEXT," + COLUMN_R_Interest+ " TEXT," + COLUMN_Duration + " TEXT," + COLUMN_M_Installment + " TEXT,"  +COLUMN_Tot_Amt + " TEXT,"+ COLUMN_Extra_Amt + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Calc_data> listContacts(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Calc_data> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String date = cursor.getString(1);
                String amount = cursor.getString(2);
                String r_int = cursor.getString(3);
                String duration = cursor.getString(4);
                String m_install = cursor.getString(5);
                String tot_amt = cursor.getString(6);
                String ext_aamt = cursor.getString(7);
                storeContacts.add(new Calc_data(id,date , amount,r_int,duration,m_install,tot_amt,ext_aamt));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }

    public void addContacts(Calc_data contacts){

        ContentValues values = new ContentValues();
        values.put(COLUMN_Date, contacts.getDate());
        values.put(COLUMN_Amount, contacts.getAmount());
        values.put(COLUMN_R_Interest, contacts.getR_int());
        values.put(COLUMN_Duration, contacts.getDuration());
        values.put(COLUMN_M_Installment, contacts.getM_install());
        values.put(COLUMN_Tot_Amt, contacts.getTot_amt());
        values.put(COLUMN_Extra_Amt, contacts.getExt_amt());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    /*public void updateContacts(Calc_data contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_Amount, contacts.getS_amount());
        values.put(COLUMN_Tot_int, contacts.getTot_interesr());
        values.put(COLUMN_Tot_Amt, contacts.getTot_amount());
        values.put(COLUMN_Given_Date, contacts.getGiven_date());
        values.put(COLUMN_Return_Date, contacts.getReturn_date());
        values.put(COLUMN_Duration, contacts.getDuration());
        values.put(COLUMN_Current_Date, contacts.getC_time());
        values.put(COLUMN_R_Interest, contacts.getR_int());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(contacts.getId())});
    }*/

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
       // db.execSQL("TRUNCATE table" + TABLE_NAME);
        db.close();
    }
}