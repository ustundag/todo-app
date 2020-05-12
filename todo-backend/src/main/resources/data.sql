/*
select * from todo;
*/
INSERT INTO todo (id, description, is_completed, deadline)
VALUES
    (1, 'Study Math', false, '2020-05-12 00:00:00.000000'),
    (2, 'Workout 3-set bench press', false, '2020-05-13 00:00:00.000000'),
    (3, 'Finish reading the book, The Singularity Is Near', false, '2020-05-14 00:00:00.000000'),
    (4, 'Learn Docker', false, '2020-05-15 00:00:00.000000');

select * from todo;