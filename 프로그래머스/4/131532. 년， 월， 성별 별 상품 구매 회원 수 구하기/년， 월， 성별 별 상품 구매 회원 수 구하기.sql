-- 코드를 입력하세요
SELECT YEAR, MONTH, GENDER, COUNT(*)
FROM
(SELECT YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH, USER_INFO.USER_ID, GENDER
FROM
ONLINE_SALE,
USER_INFO
WHERE ONLINE_SALE.USER_ID = USER_INFO.USER_ID
AND USER_INFO.GENDER  IS NOT NULL
GROUP BY YEAR(SALES_DATE), MONTH(SALES_DATE), USER_INFO.USER_ID) RESULT
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER
;