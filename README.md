# Java Diagnostic Lab

This project is designed for practicing JVM diagnostics, including memory leaks, heap dumps, and garbage collection analysis. 
It simulates common JVM problems to train working with tools like VisualVM, `jcmd`, and Eclipse MAT.

### **Project Structure**

```
src/main/java/dev/abykov/pets/
├── diagnostic_lab
│ ├── MainApp.java
│ ├── deadlock
│ │ └── DeadlockSimulator.java
│ ├── gc
│ │ └── GcPressureSimulator.java
│ └── leak
│   └── MemoryLeakSimulator.java
```

### **Memory Leak scenario (OutOfMemoryError)**

Run:
```bash
java -Xms256m -Xmx256m \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=./diagnostics/oomdump.hprof \
-cp target/diagnostic-lab-1.0-SNAPSHOT.jar \
dev.abykov.pets.diagnostic_lab.demo.MainApp memory
```
Expected:

- Continuous allocation of byte[1MB] arrays.
- JVM OutOfMemoryError.
- Heap dump automatically saved.

Tools:

- VisualVM: Monitor heap usage, take snapshots.
- Eclipse MAT: Analyze .hprof, check Retained Size and GC roots.

Sample finding:
```shell
OutOfMemoryError caused by ArrayList holding ~266MB of byte[] arrays.
```

### **Deadlock scenario (Thread Dump)**

Run:
```bash
java -cp target/diagnostic-lab-1.0-SNAPSHOT.jar \
dev.abykov.pets.diagnostic_lab.demo.MainApp deadlock
```
Expected: two threads will block each other on synchronized locks.

Tools:
- VisualVM: Take Thread Dump.
- `jcmd <pid> Thread.print`: Analyze blocked threads and deadlock.

###  **GC Pressure scenario**

Run:
```bash
java -Xms256m -Xmx256m -XX:+UseG1GC -Xloggc:./diagnostics/gc.log \
-cp target/diagnostic-lab-1.0-SNAPSHOT.jar \
dev.abykov.pets.diagnostic_lab.demo.MainApp gc
```

Expected:
- High GC activity.
- GC logs show Full GC frequency.

### **Diagnostics Artifacts**
```
diagnostics/
├── oommemory.hprof         # Heap dump after OOM
├── threads.txt             # Thread dump example
├── gc.log                  # GC logs with pauses
├── memory-sampling.csv     # VisualVM sampling export
├── analysis-report.txt     # Manual summary
```
###  **Recommended Tools**

- VisualVM
- Eclipse MAT (Memory Analyzer Tool)
-  jcmd, jstack
- GC Easy for logs: https://gceasy.io/

### **License** 
MIT License
