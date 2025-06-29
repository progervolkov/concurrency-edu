package course.concurrency.m3_shared.auction;

import java.util.concurrent.CompletableFuture;

public class Notifier {

    private volatile boolean isShutdown = false;

    public void sendOutdatedMessage(Bid bid) {
        CompletableFuture.runAsync(() -> {
            if (!isShutdown) {
                imitateSending(bid);
            }
        });
    }

    private void imitateSending(Bid bid) {
        // don't remove this delay, deal with it properly
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public void shutdown() {
        this.isShutdown = true;
    }
}
