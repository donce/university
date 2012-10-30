SELECT
	DISTINCT s.nr,
	vardas,
	pavarde,
	gimimas
FROM
	Stud.Skaitytojas s
JOIN Stud.Egzempliorius e ON
	s.nr = e.skaitytojas
WHERE
	EXTRACT(MONTH FROM gimimas) = EXTRACT(MONTH FROM CURRENT_DATE) + 3;
