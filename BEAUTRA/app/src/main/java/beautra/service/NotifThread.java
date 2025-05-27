package beautra.service;

public class NotifThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            // Contoh cek database untuk pesanan baru dan notifikasi
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopThread() {
        running = false;
        interrupt();
    }
}
