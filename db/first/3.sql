SELECT
	paimta as "Data",
	COUNT(DISTINCT ISBN) as "Knygos",
	COUNT(paimta) as "Egzemplioriai",
	CASE
		WHEN COUNT(DISTINCT ISBN) = COUNT(paimta) THEN 'Taip'
		ELSE 'Ne'
	END
FROM
	stud.Egzempliorius
WHERE
	paimta IS NOT NULL
GROUP BY
	paimta;
