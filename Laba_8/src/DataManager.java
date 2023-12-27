
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) throws IOException {
        Path path = Paths.get(source);
        data = Files.readAllLines(path);
    }

    public void saveData(String destination) throws IOException {
        Path path = Paths.get(destination);
        Files.write(path, data);
    }

    public void processData() {
        ExecutorService executorService = Executors.newFixedThreadPool(processors.size());

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Object processor : processors) {
            Class<?> processorClass = processor.getClass();
            java.lang.reflect.Method[] methods = processorClass.getDeclaredMethods();

            for (java.lang.reflect.Method method : methods) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    futures.add(CompletableFuture.runAsync(() -> {
                        try {
                            method.invoke(processor, data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, executorService));
                }
            }
        }

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        try {
            combinedFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public List<String> getData() {
        return data;
    }
}