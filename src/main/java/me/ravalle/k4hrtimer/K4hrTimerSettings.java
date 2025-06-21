package me.ravalle.k4hrtimer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.apache.logging.log4j.Level;
import xyz.duncanruns.jingle.Jingle;
import xyz.duncanruns.jingle.util.ExceptionUtil;
import xyz.duncanruns.jingle.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static me.ravalle.k4hrtimer.K4hrTimer.K4HR_TIMER_SETTINGS_PATH;

/**
 * Majority of the code from <a href="https://github.com/marin774/Jingle-Stats-Plugin/blob/main/src/main/java/me/marin/statsplugin/io/StatsPluginSettings.java">Marin's Stats plugin</a>
 */
public class K4hrTimerSettings {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static K4hrTimerSettings instance = null;

    @SerializedName("aiming for X runs")
    public int aimingForXRuns = 10;

    @SerializedName("hours")
    public int hours = 4;

    @SerializedName("records path")
    public String recordsPath = System.getProperty("user.home") + File.separator + "speedrunigt" + File.separator + "records";

    @SerializedName("timer enabled")
    public boolean timerEnabled = true;

    public static K4hrTimerSettings getInstance() {
        if (instance == null) {
            load();
        }
        return instance;
    }

    public static void load() {
        if (!Files.exists(K4HR_TIMER_SETTINGS_PATH)) {
            instance = new K4hrTimerSettings();
            save();
        } else {
            String s;
            try {
                s = FileUtil.readString(K4HR_TIMER_SETTINGS_PATH);
            } catch (IOException e) {
                instance = new K4hrTimerSettings();
                return;
            }
            instance = GSON.fromJson(s, K4hrTimerSettings.class);
        }
    }

    public static void save() {
        try {
            FileUtil.writeString(K4HR_TIMER_SETTINGS_PATH, GSON.toJson(instance));
        } catch (IOException e) {
            Jingle.log(Level.ERROR, "(K4hrTimerPlugin) Failed to save K4hr Timer Settings: " + ExceptionUtil.toDetailedString(e));
        }
    }
}