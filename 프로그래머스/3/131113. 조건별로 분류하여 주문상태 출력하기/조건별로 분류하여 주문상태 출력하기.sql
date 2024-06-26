-- 코드를 입력하세요
SELECT A.ORDER_ID, A.PRODUCT_ID, DATE_FORMAT(A.OUT_DATE, '%Y-%m-%d') as OUT_DATE, IF(A.OUT_DATE IS NULL, "출고미정", TEMP) AS 출고여부
FROM
(SELECT ORDER_ID, PRODUCT_ID, OUT_DATE, IF(OUT_DATE<='2022-05-01', "출고완료", "출고대기") TEMP
FROM FOOD_ORDER ) A
ORDER BY A.ORDER_ID
;