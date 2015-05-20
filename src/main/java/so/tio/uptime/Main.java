package so.tio.uptime;

public class Main {
    public static void main(String[] args) throws Exception {
        TrayManager tm = new TrayManager();
        tm.initTray();

        while (!tm.wantExit()) {
            if(tm.getRemainFlag()) {
                tm.updateTray(TimeGetter.getRemain());
            }else{
                tm.updateTray(TimeGetter.getUptime());
            }
            Thread.sleep(200);
        }
        System.exit(0);
    }
}


