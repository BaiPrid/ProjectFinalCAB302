package OntrollerTests;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class JavaFxInitializer {

    private static boolean toolkitInitialized = false;

    public static void initToolkit() throws InterruptedException {
        if (!toolkitInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(() -> {});
            latch.countDown();
            toolkitInitialized = true; // Mark toolkit as initialized
        }
    }
}
