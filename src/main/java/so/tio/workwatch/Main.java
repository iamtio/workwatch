package so.tio.workwatch;

public class Main {
    public static void main(String[] args) throws Exception {
        TrayManager tm = new TrayManager();
        SettingsManager sm = SettingsManagerFacility.getSettingsManager();
        tm.initTray();

        while (!tm.wantExit()) {
            switch (sm.getMode()){
                case Remain:
                    tm.updateTray(TimeGetter.getRemain());
                    break;
                case Uptime:
                    tm.updateTray(TimeGetter.getUptime());
                    break;
            }
            Thread.sleep(200);
        }
        System.exit(0);
    }
}


