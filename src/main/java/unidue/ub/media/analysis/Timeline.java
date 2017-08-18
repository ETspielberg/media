package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Timeline {

    @Id
    private String name;

    private List<TimeAndCount> timeline;

    public Timeline() {
        timeline = new ArrayList<>();
    }

    public Timeline(String name) {
        this.name = name;
        timeline = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeAndCount> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TimeAndCount> timeline) {
        this.timeline = timeline;
    }

    public void addTimeAndCount(TimeAndCount timeAndCount) {
        this.timeline.add(timeAndCount);
    }

    public void addTimeAndCounts(List<TimeAndCount> timeAndCounts) {
        this.timeline.addAll(timeAndCounts);
    }

    public int size() {
        return this.timeline.size();
    }

    /**
     * adds a new TimeAndCount object to the end of the timeline, taking the last value and applying the delta value. delta can be -1, 0 or 1
     * @param time
     * @param delta
     */
    public void addTimeAndCount(long time, int delta) {
        if (timeline.size() == 0 && delta > 0) {
            timeline.add(new TimeAndCount(time,delta));
        } else {
            TimeAndCount newTimeAndCount = timeline.get(timeline.size()-1).clone();
            if (delta == 1)
                newTimeAndCount.increaseAtTime(time);
            else if (delta == -1)
                newTimeAndCount.decreaseAtTime(time);
            timeline.add(newTimeAndCount);
            Collections.sort(timeline);
        }
    }
}
