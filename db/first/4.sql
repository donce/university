CREATE TEMP TABLE T AS (
	SELECT
		metai,
		COUNT(metai) as c
	FROM Stud.Knyga
	GROUP BY metai
);

CREATE TEMP TABLE V AS (
	SELECT AVG(c) as avg
	FROM T
);

SELECT metai
FROM T, V
WHERE c > avg;
