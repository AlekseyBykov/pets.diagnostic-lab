package dev.abykov.pets.diagnostic_lab.leak;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakSimulator {

    public static void run() {
        List<byte[]> leakyList = new ArrayList<>();
        while (true) {
            leakyList.add(new byte[1024 * 1024]); // 1 MB
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }
}
