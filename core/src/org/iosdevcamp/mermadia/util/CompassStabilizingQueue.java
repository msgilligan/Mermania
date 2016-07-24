package org.iosdevcamp.mermadia.util;

import java.util.LinkedList;

/**
 * A queue for de-bouncing compass readings
 */
public class CompassStabilizingQueue extends LinkedList<Float> {
    public static int NUM_ENTRIES = 20;

    public CompassStabilizingQueue() {
        super();
    }

    public Float stabilizeAzimuth(Float azimuth) {
        this.addFirst(azimuth);
        if (this.size() > NUM_ENTRIES) {
            this.removeLast();
        }
        float total = 0;
        for (Float value : this) {
            total += value;
        }
        return total / this.size();
    }

}
