package unidue.ub.media.unidue.ub.media.usage;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Entity
public class TimelineGroup {

    @Id
    public String name;

    public Hashtable<String,Timeline> timelines;

    public TimelineGroup() {
        timelines = new Hashtable<>();
    }

    public TimelineGroup(String name) {
        this.name = name;
        timelines = new Hashtable<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Timeline> getTimelines() {
        return new ArrayList<Timeline>(timelines.values());
    }

    public void setTimelines(List<Timeline> timelines) {
        for (Timeline timeline : timelines) {
            addTimeline(timeline);
        }
    }

    public void addTimeline(Timeline timeline) {
        this.timelines.put(timeline.getName(),timeline);
    }

    public Timeline getTimeline(String name) {
        return timelines.get(name);
    }
}
