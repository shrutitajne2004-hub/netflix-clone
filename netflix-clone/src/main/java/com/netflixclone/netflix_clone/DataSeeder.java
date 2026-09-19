package com.netflixclone.netflix_clone;

import com.netflixclone.netflix_clone.entity.Genre;
import com.netflixclone.netflix_clone.entity.Movie;
import com.netflixclone.netflix_clone.repository.GenreRepository;
import com.netflixclone.netflix_clone.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (movieRepository.count() > 0) {
            log.info("Database already seeded — skipping.");
            return;
        }
        log.info("Seeding database with initial movie data...");

        // Pre-create genres
        Map<String, Genre> genreMap = new ConcurrentHashMap<>();
        List<String> genreNames = Arrays.asList(
                "Action", "Drama", "Sci-Fi", "Comedy", "Thriller", "Horror", "Romance", "Crime"
        );
        genreNames.forEach(name -> {
            Genre g = genreRepository.findByNameIgnoreCase(name)
                    .orElseGet(() -> genreRepository.save(Genre.builder().name(name).build()));
            genreMap.put(name, g);
        });

        List<Movie> movies = Arrays.asList(
            Movie.builder()
                .title("The Dark Knight")
                .description("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.")
                .releaseYear(2008)
                .posterUrl("https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Action", "Crime", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Inception")
                .description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.")
                .releaseYear(2010)
                .posterUrl("https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .genres(genreSet(genreMap, "Action", "Sci-Fi", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Interstellar")
                .description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival as Earth faces agricultural collapse.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .genres(genreSet(genreMap, "Sci-Fi", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Shawshank Redemption")
                .description("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .releaseYear(1994)
                .posterUrl("https://image.tmdb.org/t/p/w500/lyQBXzOQSuE59IsHyhrp0qIiPAz.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
                .genres(genreSet(genreMap, "Drama", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Pulp Fiction")
                .description("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
                .releaseYear(1994)
                .posterUrl("https://image.tmdb.org/t/p/w500/fIE3lAGcZDV1G6XM5KmuWnNsPp1.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4")
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Matrix")
                .description("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.")
                .releaseYear(1999)
                .posterUrl("https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Action", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Avengers: Endgame")
                .description("After the devastating events of Avengers: Infinity War, the Avengers assemble once more to reverse Thanos' actions and restore balance to the universe.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
                .genres(genreSet(genreMap, "Action", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Parasite")
                .description("Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4")
                .genres(genreSet(genreMap, "Drama", "Thriller", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Get Out")
                .description("A young African-American visits his white girlfriend's parents for the weekend, where his uneasiness about their reception of him eventually reaches a hypnotic, horrifying climax.")
                .releaseYear(2017)
                .posterUrl("https://image.tmdb.org/t/p/w500/tFXcEccSQMf3lfhfXKSU9iRBpa3.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .genres(genreSet(genreMap, "Horror", "Thriller", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Grand Budapest Hotel")
                .description("A writer encounters the owner of an aging European hotel between the wars, who tells him of his friendship with a famous concierge and a series of adventures.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/eWdyYQreja6JGCzqHWXpWHDrrPo.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
                .genres(genreSet(genreMap, "Comedy", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Knives Out")
                .description("A detective investigates the death of a patriarch of an eccentric, combative family.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/pThyQovXQrw2m0s9x82twj48Jq4.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Crime", "Drama", "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Dune")
                .description("Feature adaptation of Frank Herbert's science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset in the galaxy.")
                .releaseYear(2021)
                .posterUrl("https://image.tmdb.org/t/p/w500/d5NXSklpcvkp173wBBHNPbFaTZk.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .genres(genreSet(genreMap, "Sci-Fi", "Action", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Silence of the Lambs")
                .description("A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer.")
                .releaseYear(1991)
                .posterUrl("https://image.tmdb.org/t/p/w500/uS9m8OBk1A8eM9I042bx8XXpqAq.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4")
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("La La Land")
                .description("While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.")
                .releaseYear(2016)
                .posterUrl("https://image.tmdb.org/t/p/w500/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                .genres(genreSet(genreMap, "Romance", "Drama", "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("1917")
                .description("April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/iZf0KyrE25z1sage4SYFLCCrMi9.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Action", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Everything Everywhere All at Once")
                .description("A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.")
                .releaseYear(2022)
                .posterUrl("https://image.tmdb.org/t/p/w500/w3LxiVYdWWRvEVdn5RYq6jIqkb1.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
                .genres(genreSet(genreMap, "Sci-Fi", "Comedy", "Drama", "Action"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Oppenheimer")
                .description("The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.")
                .releaseYear(2023)
                .posterUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg")
                .videoUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                .genres(genreSet(genreMap, "Drama", "Thriller"))
                .averageRating(0.0)
                .build()
        );

        movieRepository.saveAll(movies);
        log.info("Seeded {} movies successfully.", movies.size());
    }

    private Set<Genre> genreSet(Map<String, Genre> map, String... names) {
        Set<Genre> result = new HashSet<>();
        for (String name : names) {
            if (map.containsKey(name)) result.add(map.get(name));
        }
        return result;
    }
}
