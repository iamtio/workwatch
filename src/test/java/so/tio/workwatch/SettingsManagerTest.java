package so.tio.workwatch;

import java.awt.*;

import static org.junit.Assert.*;

public class SettingsManagerTest {
    @org.junit.Test
    public void testEncodeColor(){
        assertEquals("Red encode OK", SettingsManager.encodeColor(Color.RED), "#FF0000");
        assertEquals("Green encode OK", SettingsManager.encodeColor(Color.GREEN), "#00FF00");
        assertEquals("Blue encode OK", SettingsManager.encodeColor(Color.BLUE), "#0000FF");
        assertEquals("White encode OK", SettingsManager.encodeColor(Color.WHITE), "#FFFFFF");
        assertEquals("Black encode OK", SettingsManager.encodeColor(Color.BLACK), "#000000");
    }

    @org.junit.Test
    public void testDecodeColor(){
        assertEquals("Red decode OK", SettingsManager.decodeColor("#FF0000"), Color.RED);
        assertEquals("Green decode OK", SettingsManager.decodeColor("#00FF00"), Color.GREEN);
        assertEquals("Blue decode OK", SettingsManager.decodeColor("#0000FF"), Color.BLUE);
        assertEquals("White decode OK", SettingsManager.decodeColor("#FFFFFF"), Color.WHITE);
        assertEquals("Black decode OK", SettingsManager.decodeColor("#000000"), Color.BLACK);
    }
}