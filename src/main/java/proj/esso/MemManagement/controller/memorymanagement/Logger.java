package proj.esso.MemManagement.controller.memorymanagement;

import java.util.Date;

class Logger {

    private String log;

    public Logger()
    {
        this.log = "";
    }

    public void addLog(String log)
    {
        this.log = this.log + String.format("\n[%s]: %s", new Date().getTime(), log);
    }
    public String getLog()
    {
        return this.log;
    }
}
