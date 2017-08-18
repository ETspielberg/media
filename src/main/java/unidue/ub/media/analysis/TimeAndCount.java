package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Plain old java object holding the combination of time and count.
 * 
 * @author Frank L\u00FCtzenkirchen, Eike Spielberg
 * @version 1
 */
@Entity
public class TimeAndCount implements Cloneable, Comparable<TimeAndCount> {

    @Id
    @GeneratedValue
    private long id;
	
    private long x;

    private int y;

    TimeAndCount() {}

    public TimeAndCount(long x, int y) {
        this.x = x;
        this.y = y;
    }
    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(int y) { this.y = y;
    }

    public void increaseAtTime(long time) {
        this.x  = time;
        this.y++;
    }

    public void decreaseAtTime(long time) {
        this.x  = time;
        this.y--;
    }

    public TimeAndCount clone() {
        return new TimeAndCount(this.x, this.y);
    }

    public int compareTo(TimeAndCount other) {
        if (this.x > other.x)
            return 1;
        else
            return -1;
    }
}