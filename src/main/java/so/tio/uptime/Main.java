package so.tio.uptime;

public class Main {
    public static void main(String[] args) throws Exception {
        TrayManager tm = new TrayManager();
        SettingsManager sm = SettingsManagerFacility.getSettingsManager();
        tm.initTray();

        while (!tm.wantExit()) {
            if(sm.getRemainFlag()) {
                tm.updateTray(TimeGetter.getRemain());
            }else{
                tm.updateTray(TimeGetter.getUptime());
            }
            Thread.sleep(200);
        }
        System.exit(0);
    }
}


