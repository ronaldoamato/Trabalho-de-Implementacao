package sync;

import java.text.SimpleDateFormat;
import java.util.Date;

class Logger {

    private String log;

    public Logger()
    {
        this.log = "";
    }

    public void addLog(String log)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        
        this.log = this.log + String.format("\n[%s]: %s", sdf.format(now), log);
    }
    public String getLog()
    {
        return this.log;
    }
}
