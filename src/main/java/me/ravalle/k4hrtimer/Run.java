package me.ravalle.k4hrtimer;

import java.util.concurrent.TimeUnit;

public class Run {
    private long startedAt;
    private long rta;
    private long rtt;
    private long completedAt;

    public Run(long rta, long rtt, long startedAt, long completedAt) {
        this.rta = rta;
        this.rtt = rtt;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public long getRta() {
        return rta;
    }

    public void setRta(long rta) {
        this.rta = rta;
    }

    public long getRtt() {
        return rtt;
    }

    public void setRtt(long rtt) {
        this.rtt = rtt;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(long completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(rtt);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(rtt) -
                TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%02d:%02d", minutes, seconds);
    }
}