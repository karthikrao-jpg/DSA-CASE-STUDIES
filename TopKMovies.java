import java.util.*;

public class TopKMovies {

    static class Movie {
        String name;
        int views;

        Movie(String n, int v) {
            name = n;
            views = v;
        }

        @Override
        public String toString() {
            return name + " (" + views + "M)";
        }
    }

    static List<Movie> topK(List<Movie> movies, int k) {

        PriorityQueue<Movie> heap =
                new PriorityQueue<>(
                        (a, b) -> Integer.compare(a.views, b.views));

        for (Movie m : movies) {

            if (heap.size() < k) {
                heap.offer(m);
            } else {

                // Required Code
                if (m.views > heap.peek().views) {
                    heap.poll();
                    heap.offer(m);
                }
            }
        }

        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {

        List<Movie> movies = Arrays.asList(
                new Movie("M1", 42),
                new Movie("M2", 15),
                new Movie("M3", 88),
                new Movie("M4", 29),
                new Movie("M5", 63),
                new Movie("M6", 95),
                new Movie("M7", 37),
                new Movie("M8", 72),
                new Movie("M9", 20),
                new Movie("M10", 84)
        );

        int k = 4;

        List<Movie> result = topK(movies, k);

        result.sort((a, b) -> b.views - a.views);

        System.out.println("Top " + k + " Movies:");

        for (Movie m : result) {
            System.out.println(m.name + " -> " + m.views + " million views");
        }
    }
}