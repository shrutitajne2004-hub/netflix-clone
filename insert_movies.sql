SET NAMES utf8mb4;

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Dark Knight', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 2008, 'https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Dark Knight');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Dark Knight');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.', 2010, 'https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Inception');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Inception');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival as Earth faces agricultural collapse.', 2014, 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Interstellar');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Interstellar');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 1994, 'https://image.tmdb.org/t/p/w500/lyQBXzOQSuE59IsHyhrp0qIiPAz.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Shawshank Redemption');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Shawshank Redemption');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 1994, 'https://image.tmdb.org/t/p/w500/fIE3lAGcZDV1G6XM5KmuWnNsPp1.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Pulp Fiction');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Pulp Fiction');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Matrix', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', 1999, 'https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Matrix');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Matrix');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Avengers: Endgame', 'After the devastating events of Avengers: Infinity War, the Avengers assemble once more to reverse Thanos'' actions and restore balance to the universe.', 2019, 'https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Avengers: Endgame');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Avengers: Endgame');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 2019, 'https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Parasite');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Parasite');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Joker', 'In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime.', 2019, 'https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Joker');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Joker');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Get Out', 'A young African-American visits his white girlfriend''s parents for the weekend, where his uneasiness about their reception of him eventually reaches a hypnotic, horrifying climax.', 2017, 'https://image.tmdb.org/t/p/w500/tFXcEccSQMf3lfhfXKSU9iRBpa3.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Get Out');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Get Out');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 6);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Grand Budapest Hotel', 'A writer encounters the owner of an aging European hotel between the wars, who tells him of his friendship with a famous concierge and a series of adventures.', 2014, 'https://image.tmdb.org/t/p/w500/eWdyYQreja6JGCzqHWXpWHDrrPo.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Grand Budapest Hotel');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Grand Budapest Hotel');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Knives Out', 'A detective investigates the death of a patriarch of an eccentric, combative family.', 2019, 'https://image.tmdb.org/t/p/w500/pThyQovXQrw2m0s9x82twj48Jq4.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Knives Out');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Knives Out');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Dune', 'Feature adaptation of Frank Herbert''s science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset in the galaxy.', 2021, 'https://image.tmdb.org/t/p/w500/d5NXSklpcvkp173wBBHNPbFaTZk.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Dune');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Dune');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Silence of the Lambs', 'A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer.', 1991, 'https://image.tmdb.org/t/p/w500/uS9m8OBk1A8eM9I042bx8XXpqAq.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Silence of the Lambs');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Silence of the Lambs');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'La La Land', 'While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.', 2016, 'https://image.tmdb.org/t/p/w500/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'La La Land');

SET @movie_id = (SELECT id FROM movies WHERE title = 'La La Land');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 7);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT '1917', 'April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.', 2019, 'https://image.tmdb.org/t/p/w500/iZf0KyrE25z1sage4SYFLCCrMi9.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = '1917');

SET @movie_id = (SELECT id FROM movies WHERE title = '1917');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Everything Everywhere All at Once', 'A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.', 2022, 'https://image.tmdb.org/t/p/w500/w3LxiVYdWWRvEVdn5RYq6jIqkb1.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Everything Everywhere All at Once');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Everything Everywhere All at Once');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Oppenheimer', 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.', 2023, 'https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Oppenheimer');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Oppenheimer');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Mad Max: Fury Road', 'In a post-apocalyptic wasteland, Max teams up with a mysterious woman, Furiosa, to outrun a warlord and his army of followers in a high-speed chase across the desert.', 2015, 'https://image.tmdb.org/t/p/w500/8tZYtuWezp3sCpScbe0TIdATfPa.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Mad Max: Fury Road');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Mad Max: Fury Road');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'John Wick', 'An ex-hitman comes out of retirement to track down the gangsters that killed his dog and took everything from him.', 2014, 'https://image.tmdb.org/t/p/w500/wXqWR7dHncNg2KEm7YCYX7PEKH6.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'John Wick');

SET @movie_id = (SELECT id FROM movies WHERE title = 'John Wick');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Top Gun: Maverick', 'After more than thirty years of service as one of the Navy''s top aviators, Pete Mitchell is back where he belongs, pushing the envelope as a courageous test pilot.', 2022, 'https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Top Gun: Maverick');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Top Gun: Maverick');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Raid: Redemption', 'A SWAT team becomes trapped in a tenement run by a ruthless mobster and his army of killers and thugs.', 2011, 'https://image.tmdb.org/t/p/w500/jH7oVJrEQsFoqBFsHOmTgKQUMbV.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Raid: Redemption');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Raid: Redemption');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Arrival', 'A linguist works with the military to communicate with alien lifeforms after twelve mysterious spacecraft appear around the world.', 2016, 'https://image.tmdb.org/t/p/w500/x2FJsf1ElAgr63Y3PNPtJrcmpoe.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Arrival');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Arrival');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Blade Runner 2049', 'Young Blade Runner K''s discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who''s been missing for thirty years.', 2017, 'https://image.tmdb.org/t/p/w500/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Blade Runner 2049');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Blade Runner 2049');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Martian', 'An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive.', 2015, 'https://image.tmdb.org/t/p/w500/5aGhaIHYuQbqlHWvWYqMCnj40y2.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Martian');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Martian');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Schindler''s List', 'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution.', 1993, 'https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Schindler''s List');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Schindler''s List');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Godfather', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 1972, 'https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsLlegTrEobCs.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Godfather');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Godfather');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Whiplash', 'A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are both nurtured and challenged by an instructor who will stop at nothing.', 2014, 'https://image.tmdb.org/t/p/w500/7fn624j5lj3xTme2SgiLCeuedmO.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Whiplash');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Whiplash');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Social Network', 'As Harvard student Mark Zuckerberg creates the social networking site Facebook, he is sued by the twin brothers who claimed he stole their idea, and his co-founder.', 2010, 'https://image.tmdb.org/t/p/w500/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Social Network');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Social Network');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Gone Girl', 'With his wife''s disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it''s suspected he may not be innocent.', 2014, 'https://image.tmdb.org/t/p/w500/a5PpSBh8BkJnkHBH3Yp1HbrQkEv.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Gone Girl');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Gone Girl');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Se7en', 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi.', 1995, 'https://image.tmdb.org/t/p/w500/69Sns8WoET6CfaYlIkHbla4l7Aa.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Se7en');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Se7en');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'No Country for Old Men', 'Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong and decides to take the money.', 2007, 'https://image.tmdb.org/t/p/w500/6d5XOczc2bfBOtGDl68iXpRFATX.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'No Country for Old Men');

SET @movie_id = (SELECT id FROM movies WHERE title = 'No Country for Old Men');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Hereditary', 'When the matriarch of the Graham family passes away, her daughter''s family begins to unravel cryptic and terrifying secrets about their ancestry.', 2018, 'https://image.tmdb.org/t/p/w500/mEOMjOFgCBRgPWPqGo3KsDeqwn1.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Hereditary');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Hereditary');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 6);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Conjuring', 'Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.', 2013, 'https://image.tmdb.org/t/p/w500/wVYREutTvI2tmxr6ujrHT704wGF.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Conjuring');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Conjuring');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 6);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'A Quiet Place', 'In a post-apocalyptic world, a family is forced to live in near silence while hiding from creatures that hunt by sound.', 2018, 'https://image.tmdb.org/t/p/w500/nAU74GmpUk7t5iklEp3bufwDq4n.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'A Quiet Place');

SET @movie_id = (SELECT id FROM movies WHERE title = 'A Quiet Place');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 6);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 3);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Hangover', 'Three buddies wake up from a bachelor party in Las Vegas with no memory of the previous night and the groom missing.', 2009, 'https://image.tmdb.org/t/p/w500/uluhlXubGu1VxU63boQeN1kp8yK.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Hangover');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Hangover');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Superbad', 'Two co-dependent high school seniors are forced to deal with separation anxiety after their plan to spend the night together at a party goes awry.', 2007, 'https://image.tmdb.org/t/p/w500/ek8e8txUyUwd2BNqj6lFEerJfbq.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Superbad');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Superbad');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Game Night', 'A group of friends who meet regularly for game nights find themselves entangled in a real-life mystery when the host''s brother is taken hostage by criminals.', 2018, 'https://image.tmdb.org/t/p/w500/lKiXUaCRGVCgxhTMOH9sOWfJSRH.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Game Night');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Game Night');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Crazy, Stupid, Love', 'A middle-aged husband''s life changes dramatically when his wife asks him for a divorce. He seeks to rediscover his manhood with the help of a new found friend.', 2011, 'https://image.tmdb.org/t/p/w500/ky4FLDSE5YJfhEEqjb5xs5Gxq9V.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Crazy, Stupid, Love');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Crazy, Stupid, Love');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 7);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 4);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Notebook', 'A young couple from different social worlds falls in love in the 1940s. Their story is told from the perspective of an old man reading to a woman from his notebook.', 2004, 'https://image.tmdb.org/t/p/w500/rNzQyW4f8B8cQeg7Dgj3n6eT5k9.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Notebook');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Notebook');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 7);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Before Sunrise', 'A young man and woman meet on a train in Europe, and wind up spending one romantic and expressive evening together in Vienna.', 1995, 'https://image.tmdb.org/t/p/w500/morpNovUe0GxNwTMPpgZWlwuGzD.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Before Sunrise');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Before Sunrise');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 7);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'The Departed', 'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.', 2006, 'https://image.tmdb.org/t/p/w500/nT97ifVT2J1yMQmeq20Qblg61T.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Departed');

SET @movie_id = (SELECT id FROM movies WHERE title = 'The Departed');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 5);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Goodfellas', 'The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito.', 1990, 'https://image.tmdb.org/t/p/w500/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg', 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Goodfellas');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Goodfellas');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Heat', 'A group of professional bank robbers start to feel the heat from police when they unknowingly leave a critical clue at their latest heist.', 1995, 'https://image.tmdb.org/t/p/w500/rrBuGu0Pjq7Y2BWSI6teGfZzviY.jpg', 'https://test-videos.co.uk/vids/sintel/mp4/h264/360/Sintel_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Heat');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Heat');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 1);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 8);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);

INSERT INTO movies (title, description, release_year, poster_url, video_url, average_rating, created_at, updated_at)
SELECT 'Good Will Hunting', 'Will Hunting, a janitor at M.I.T., has a gift for mathematics, but needs help from a psychologist to find direction in his life.', 1997, 'https://image.tmdb.org/t/p/w500/bABCBKYBK7A5G1x0FzoeoNfuj2.jpg', 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/360/Jellyfish_360_10s_1MB.mp4', 0.0, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Good Will Hunting');

SET @movie_id = (SELECT id FROM movies WHERE title = 'Good Will Hunting');
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 2);
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES (@movie_id, 7);
