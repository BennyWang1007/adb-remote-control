package de.oberien.adbremotecontrol.thread;

import java.awt.image.BufferedImage;

import de.oberien.adbremotecontrol.adb.AdbDevice;
import de.oberien.adbremotecontrol.utils.Config;
import de.oberien.adbremotecontrol.view.ScreenPanel;

public class ScreenshotThread extends Thread {
    private ScreenPanel screenPanel;
    private AdbDevice adbDevice;

    public ScreenshotThread(ScreenPanel screenPanel, AdbDevice adbDevice) {
        super("ScreenshotThread");
        this.screenPanel = screenPanel;
        this.adbDevice = adbDevice;
    }

    public void run() {
        while (!Thread.interrupted()) {
            BufferedImage screenshot = adbDevice.screenshot();
            screenPanel.setScreenshot(screenshot);
            try {
                Thread.sleep(Config.timeout);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
