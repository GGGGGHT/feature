import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * <a href="https://www.youtube.com/watch?v=QH2-TGUlwu4">Java Asynchronous Programming Full Tutorial with Loom and Structured Concurrency</a>
 */
public class AsynchronousProgramming {
    public static void main(String[] args) throws InterruptedException {
        // try {
        //
        //     new NullPointerException("a");
        // } catch (Exception e) {
        //
        // }

        Thread pthread =
            Thread.ofPlatform().unstarted(() -> System.out.println(Thread.currentThread()));
        pthread.start();
        pthread.join();
        var vThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println(Thread.currentThread());
        });

        vThread.start();
        vThread.join();

        ForkJoinPool.commonPool().submit(() -> System.out.println(Thread.currentThread()));
        // IntStream.range(0, 100).forEach(
        //     i -> vThread.start()
        // );
        List<Thread> threads =
            IntStream.range(0, 10).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
                    if (i == 0) {
                        System.out.println(Thread.currentThread());
                    }

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {

                    }

                    if (i == 0) {
                        System.out.println(Thread.currentThread());
                    }
                })
            ).toList();

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}

// record Weather(String server, String weather) {
//
//     public static Weather fromJson(String json) {
//         try (var parser = Json.createParser(new StringReader(json))) {
//             parser.next();
//             JsonObject jsonObject = parser.getObject();
//
//             return new Weather(jsonObject.getString("server"), jsonObject.getString("weather"));
//         }
//     }
//     public static Weather readWeather() throws InterruptedException, ExecutionException {
//         // 1. cheap
//         // 2. use fork/join pool
//         try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Weather>()) {
//             // Future<Weather> futureA = scope.fork(Weather::readWeatherFromA);
//             // Future<Weather> futureB = scope.fork(Weather::readWeatherFromB);
//             // Future<Weather> futureC = scope.fork(Weather::readWeatherFromC);
//
//             Stream.of("/01/weather", "/02/weather", "/03/weather").
//                 <Callable<Weather>>map(url -> () -> readWeatherFrom(url)).forEach(scope::fork);
//             scope.join();
//
//             return scope.result();
//         }
//     }
//
//     public static Weather readWeatherFromA() throws IOException, InterruptedException {
//         return readWeatherFrom("/01/weather");
//     }
//
//     public static Weather readWeatherFromB() throws IOException, InterruptedException {
//         return readWeatherFrom("/02/weather");
//     }
//     public static Weather readWeatherFromC() throws IOException, InterruptedException {
//         return readWeatherFrom("/03/weather");
//     }
//
//     private static Weather readWeatherFrom(String weatherServer)
//         throws IOException, InterruptedException {
//         HttpClient client = HttpClient.newHttpClient();
//         HttpRequest request =
//             HttpRequest.newBuilder().uri(URI.create(weatherServer)).GET().build();
//
//         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//         if (response.statusCode() == 200) {
//             String json = response.body();
//
//             return Weather.fromJson(json);
//         } else {
//             throw new RuntimeException("server Unavailable");
//         }
//     }
// }