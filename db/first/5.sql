SELECT
	table_schema AS "Schema",
	table_name AS "Pavadinimas",
	COUNT(DISTINCT data_type) AS "Tipų skaičius"
FROM
	information_schema.columns
WHERE
	is_nullable = 'YES'
GROUP BY
	table_schema, table_name
ORDER BY
	1;
