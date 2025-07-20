package course.concurrency;

import java.util.ArrayDeque;
import java.util.Queue;

public class CustomBlockingQueue<T> {

    private final int sizeLimit;
    private final Queue<T> queue;
    private final Object lock = new Object();

    public CustomBlockingQueue(int size) {
        this.queue = new ArrayDeque<>(size);
        this.sizeLimit = size;
    }

    public void enqueue(T value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == sizeLimit) {
                lock.wait();
            }
            queue.add(value);
            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            return queue.poll();
        }
    }

    public int size() {
        return queue.size();
    }
}
