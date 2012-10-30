SELECT
	ak, vardas, pavarde AS "Pavarde",
	gimimas
FROM
	stud.Skaitytojas
WHERE
	gimimas < CURRENT_DATE - INTERVAL '19 years';
