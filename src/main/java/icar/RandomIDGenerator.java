package icar;

import java.util.concurrent.atomic.AtomicLong;

public class RandomIDGenerator {
    // Use an AtomicLong to ensure thread-safe ID generation in multi-threaded environments:
    private static AtomicLong nextId = new AtomicLong(1); // Start from 1

    // Generate a unique request ID using a combination of timestamp and sequence number:
    public static int generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        long sequence = nextId.getAndIncrement(); // Atomically increment the counter
        int generatedID = (int) (timestamp * 10000 + sequence); // Combine timestamp and sequence for uniqueness
        return Math.abs(generatedID);
    }
}
