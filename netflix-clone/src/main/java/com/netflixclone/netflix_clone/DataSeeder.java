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
        // if (movieRepository.count() > 0) {
        //     log.info("Database already seeded — skipping.");
        //     return;
        // }
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

        // Working video URLs from test-videos.co.uk (distributed across movies)
        final String BBB = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4";
        final String SINTEL = "https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4";
        final String JELLYFISH = "https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4";

        List<Movie> movies = Arrays.asList(
            Movie.builder()
                .title("The Dark Knight")
                .description("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.")
                .releaseYear(2008)
                .posterUrl("https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Action", "Crime", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Inception")
                .description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.")
                .releaseYear(2010)
                .posterUrl("https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Action", "Sci-Fi", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Interstellar")
                .description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival as Earth faces agricultural collapse.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Sci-Fi", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Shawshank Redemption")
                .description("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .releaseYear(1994)
                .posterUrl("https://image.tmdb.org/t/p/w500/lyQBXzOQSuE59IsHyhrp0qIiPAz.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Drama", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Pulp Fiction")
                .description("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
                .releaseYear(1994)
                .posterUrl("https://image.tmdb.org/t/p/w500/fIE3lAGcZDV1G6XM5KmuWnNsPp1.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Matrix")
                .description("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.")
                .releaseYear(1999)
                .posterUrl("https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Action", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Avengers: Endgame")
                .description("After the devastating events of Avengers: Infinity War, the Avengers assemble once more to reverse Thanos' actions and restore balance to the universe.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Action", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Parasite")
                .description("Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Drama", "Thriller", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Get Out")
                .description("A young African-American visits his white girlfriend's parents for the weekend, where his uneasiness about their reception of him eventually reaches a hypnotic, horrifying climax.")
                .releaseYear(2017)
                .posterUrl("https://image.tmdb.org/t/p/w500/tFXcEccSQMf3lfhfXKSU9iRBpa3.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Horror", "Thriller", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Grand Budapest Hotel")
                .description("A writer encounters the owner of an aging European hotel between the wars, who tells him of his friendship with a famous concierge and a series of adventures.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/eWdyYQreja6JGCzqHWXpWHDrrPo.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Comedy", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Knives Out")
                .description("A detective investigates the death of a patriarch of an eccentric, combative family.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/pThyQovXQrw2m0s9x82twj48Jq4.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Crime", "Drama", "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Dune")
                .description("Feature adaptation of Frank Herbert's science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset in the galaxy.")
                .releaseYear(2021)
                .posterUrl("https://image.tmdb.org/t/p/w500/d5NXSklpcvkp173wBBHNPbFaTZk.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Sci-Fi", "Action", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Silence of the Lambs")
                .description("A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer.")
                .releaseYear(1991)
                .posterUrl("https://image.tmdb.org/t/p/w500/uS9m8OBk1A8eM9I042bx8XXpqAq.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("La La Land")
                .description("While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.")
                .releaseYear(2016)
                .posterUrl("https://image.tmdb.org/t/p/w500/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Romance", "Drama", "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("1917")
                .description("April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.")
                .releaseYear(2019)
                .posterUrl("https://image.tmdb.org/t/p/w500/iZf0KyrE25z1sage4SYFLCCrMi9.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Action", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Everything Everywhere All at Once")
                .description("A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.")
                .releaseYear(2022)
                .posterUrl("https://image.tmdb.org/t/p/w500/w3LxiVYdWWRvEVdn5RYq6jIqkb1.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Sci-Fi", "Comedy", "Drama", "Action"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Oppenheimer")
                .description("The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.")
                .releaseYear(2023)
                .posterUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            // ── 27 new movies (ids ~19-45) ──────────────────────────────────

            // Action
            Movie.builder()
                .title("Mad Max: Fury Road")
                .description("In a post-apocalyptic wasteland, Max teams up with a mysterious woman, Furiosa, to outrun a warlord and his army of followers in a high-speed chase across the desert.")
                .releaseYear(2015)
                .posterUrl("https://image.tmdb.org/t/p/w500/8tZYtuWezp3sCpScbe0TIdATfPa.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Action", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("John Wick")
                .description("An ex-hitman comes out of retirement to track down the gangsters that killed his dog and took everything from him.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/wXqWR7dHncNg2KEm7YCYX7PEKH6.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Action", "Crime", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Top Gun: Maverick")
                .description("After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is back where he belongs, pushing the envelope as a courageous test pilot.")
                .releaseYear(2022)
                .posterUrl("https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Action", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Raid: Redemption")
                .description("A SWAT team becomes trapped in a tenement run by a ruthless mobster and his army of killers and thugs.")
                .releaseYear(2011)
                .posterUrl("https://image.tmdb.org/t/p/w500/jH7oVJrEQsFoqBFsHOmTgKQUMbV.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Action", "Crime", "Thriller"))
                .averageRating(0.0)
                .build(),

            // Sci-Fi
            Movie.builder()
                .title("Arrival")
                .description("A linguist works with the military to communicate with alien lifeforms after twelve mysterious spacecraft appear around the world.")
                .releaseYear(2016)
                .posterUrl("https://image.tmdb.org/t/p/w500/x2FJsf1ElAgr63Y3PNPtJrcmpoe.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Sci-Fi", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Blade Runner 2049")
                .description("Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who's been missing for thirty years.")
                .releaseYear(2017)
                .posterUrl("https://image.tmdb.org/t/p/w500/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Sci-Fi", "Action", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Martian")
                .description("An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive.")
                .releaseYear(2015)
                .posterUrl("https://image.tmdb.org/t/p/w500/5aGhaIHYuQbqlHWvWYqMCnj40y2.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Sci-Fi", "Drama", "Comedy"))
                .averageRating(0.0)
                .build(),

            // Drama
            Movie.builder()
                .title("Schindler's List")
                .description("In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution.")
                .releaseYear(1993)
                .posterUrl("https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Godfather")
                .description("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.")
                .releaseYear(1972)
                .posterUrl("https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsLlegTrEobCs.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Drama", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Whiplash")
                .description("A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are both nurtured and challenged by an instructor who will stop at nothing.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/7fn624j5lj3xTme2SgiLCeuedmO.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Social Network")
                .description("As Harvard student Mark Zuckerberg creates the social networking site Facebook, he is sued by the twin brothers who claimed he stole their idea, and his co-founder.")
                .releaseYear(2010)
                .posterUrl("https://image.tmdb.org/t/p/w500/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Drama"))
                .averageRating(0.0)
                .build(),

            // Thriller
            Movie.builder()
                .title("Gone Girl")
                .description("With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected he may not be innocent.")
                .releaseYear(2014)
                .posterUrl("https://image.tmdb.org/t/p/w500/a5PpSBh8BkJnkHBH3Yp1HbrQkEv.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Thriller", "Drama", "Crime"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Se7en")
                .description("Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi.")
                .releaseYear(1995)
                .posterUrl("https://image.tmdb.org/t/p/w500/69Sns8WoET6CfaYlIkHbla4l7Aa.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("No Country for Old Men")
                .description("Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong and decides to take the money.")
                .releaseYear(2007)
                .posterUrl("https://image.tmdb.org/t/p/w500/6d5XOczc2bfBOtGDl68iXpRFATX.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            // Horror
            Movie.builder()
                .title("Hereditary")
                .description("When the matriarch of the Graham family passes away, her daughter's family begins to unravel cryptic and terrifying secrets about their ancestry.")
                .releaseYear(2018)
                .posterUrl("https://image.tmdb.org/t/p/w500/mEOMjOFgCBRgPWPqGo3KsDeqwn1.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Horror", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Conjuring")
                .description("Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.")
                .releaseYear(2013)
                .posterUrl("https://image.tmdb.org/t/p/w500/wVYREutTvI2tmxr6ujrHT704wGF.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Horror", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("A Quiet Place")
                .description("In a post-apocalyptic world, a family is forced to live in near silence while hiding from creatures that hunt by sound.")
                .releaseYear(2018)
                .posterUrl("https://image.tmdb.org/t/p/w500/nAU74GmpUk7t5iklEp3bufwDq4n.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Horror", "Drama", "Sci-Fi"))
                .averageRating(0.0)
                .build(),

            // Comedy
            Movie.builder()
                .title("The Hangover")
                .description("Three buddies wake up from a bachelor party in Las Vegas with no memory of the previous night and the groom missing.")
                .releaseYear(2009)
                .posterUrl("https://image.tmdb.org/t/p/w500/uluhlXubGu1VxU63boQeN1kp8yK.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Superbad")
                .description("Two co-dependent high school seniors are forced to deal with separation anxiety after their plan to spend the night together at a party goes awry.")
                .releaseYear(2007)
                .posterUrl("https://image.tmdb.org/t/p/w500/ek8e8txUyUwd2BNqj6lFEerJfbq.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Comedy"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Game Night")
                .description("A group of friends who meet regularly for game nights find themselves entangled in a real-life mystery when the host's brother is taken hostage by criminals.")
                .releaseYear(2018)
                .posterUrl("https://image.tmdb.org/t/p/w500/lKiXUaCRGVCgxhTMOH9sOWfJSRH.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Comedy", "Crime", "Thriller"))
                .averageRating(0.0)
                .build(),

            // Romance
            Movie.builder()
                .title("Crazy, Stupid, Love")
                .description("A middle-aged husband's life changes dramatically when his wife asks him for a divorce. He seeks to rediscover his manhood with the help of a new found friend.")
                .releaseYear(2011)
                .posterUrl("https://image.tmdb.org/t/p/w500/ky4FLDSE5YJfhEEqjb5xs5Gxq9V.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Romance", "Comedy", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("The Notebook")
                .description("A young couple from different social worlds falls in love in the 1940s. Their story is told from the perspective of an old man reading to a woman from his notebook.")
                .releaseYear(2004)
                .posterUrl("https://image.tmdb.org/t/p/w500/rNzQyW4f8B8cQeg7Dgj3n6eT5k9.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Romance", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Before Sunrise")
                .description("A young man and woman meet on a train in Europe, and wind up spending one romantic and expressive evening together in Vienna.")
                .releaseYear(1995)
                .posterUrl("https://image.tmdb.org/t/p/w500/morpNovUe0GxNwTMPpgZWlwuGzD.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Romance", "Drama"))
                .averageRating(0.0)
                .build(),

            // Crime
            Movie.builder()
                .title("The Departed")
                .description("An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.")
                .releaseYear(2006)
                .posterUrl("https://image.tmdb.org/t/p/w500/nT97ifVT2J1yMQmeq20Qblg61T.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Crime", "Drama", "Thriller"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Goodfellas")
                .description("The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito.")
                .releaseYear(1990)
                .posterUrl("https://image.tmdb.org/t/p/w500/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg")
                .videoUrl(BBB)
                .genres(genreSet(genreMap, "Crime", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Heat")
                .description("A group of professional bank robbers start to feel the heat from police when they unknowingly leave a critical clue at their latest heist.")
                .releaseYear(1995)
                .posterUrl("https://image.tmdb.org/t/p/w500/rrBuGu0Pjq7Y2BWSI6teGfZzviY.jpg")
                .videoUrl(SINTEL)
                .genres(genreSet(genreMap, "Action", "Crime", "Drama"))
                .averageRating(0.0)
                .build(),

            Movie.builder()
                .title("Good Will Hunting")
                .description("Will Hunting, a janitor at M.I.T., has a gift for mathematics, but needs help from a psychologist to find direction in his life.")
                .releaseYear(1997)
                .posterUrl("https://image.tmdb.org/t/p/w500/bABCBKYBK7A5G1x0FzoeoNfuj2.jpg")
                .videoUrl(JELLYFISH)
                .genres(genreSet(genreMap, "Drama", "Romance"))
                .averageRating(0.0)
                .build()
        );

        for (Movie m : movies) {
            if (!movieRepository.existsByTitleIgnoreCase(m.getTitle())) {
                movieRepository.save(m);
            }
        }
        log.info("Seeding process completed for {} movies (newly added or already existed).", movies.size());
    }

    private Set<Genre> genreSet(Map<String, Genre> map, String... names) {
        Set<Genre> result = new HashSet<>();
        for (String name : names) {
            if (map.containsKey(name)) result.add(map.get(name));
        }
        return result;
    }
}
