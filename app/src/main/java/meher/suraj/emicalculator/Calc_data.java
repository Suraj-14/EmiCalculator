package meher.suraj.emicalculator;

public class Calc_data {

    private	int	id;
    private String date,amount,r_int,duration,m_install,tot_amt,ext_amt;

    public Calc_data(int id, String date, String amount, String r_int, String duration, String m_install, String tot_amt, String ext_amt) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.r_int = r_int;
        this.duration = duration;
        this.m_install = m_install;
        this.tot_amt = tot_amt;
        this.ext_amt = ext_amt;
    }

    public Calc_data(String date, String amount, String r_int, String duration, String m_install, String tot_amt, String ext_amt) {
        this.date = date;
        this.amount = amount;
        this.r_int = r_int;
        this.duration = duration;
        this.m_install = m_install;
        this.tot_amt = tot_amt;
        this.ext_amt = ext_amt;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getR_int() {
        return r_int;
    }

    public String getDuration() {
        return duration;
    }

    public String getM_install() {
        return m_install;
    }

    public String getTot_amt() {
        return tot_amt;
    }

    public String getExt_amt() {
        return ext_amt;
    }
}
