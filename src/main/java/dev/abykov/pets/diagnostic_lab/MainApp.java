package dev.abykov.pets.diagnostic_lab;

import dev.abykov.pets.diagnostic_lab.deadlock.DeadlockSimulator;
import dev.abykov.pets.diagnostic_lab.gc.GcPressureSimulator;
import dev.abykov.pets.diagnostic_lab.leak.MemoryLeakSimulator;

public class MainApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Specify case: memory | deadlock | gc");
            return;
        }

        switch (args[0]) {
            case "memory":
                MemoryLeakSimulator.run();
                break;
            case "deadlock":
                DeadlockSimulator.run();
                break;
            case "gc":
                GcPressureSimulator.run();
                break;
            default:
                System.out.println("Unknown case: " + args[0]);
        }
    }
}
