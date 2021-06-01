import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;

public class Timer implements Runnable{
    public long startTime;
    public String endTime;
    public JLabel _time; 

    public Timer(JLabel time){
        startTime = System.currentTimeMillis();
        _time = time;
    }
    public void append(JLabel time)
    {
        NumberFormat formatter = new DecimalFormat("#0.00");
        long currentTime = System.currentTimeMillis();
        time.setText("Time elapsed: " + formatter.format((currentTime - startTime) / 1000d) + "s");
    }

    @Override
    public void run() {
        while(true) {
            append(_time);
        }
        
    }

}
