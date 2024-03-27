-- 코드를 입력하세요
SELECT A.PRODUCT_CODE, SUM(SALES_AMOUNT) * PRICE AS SALES
FROM PRODUCT A, OFFLINE_SALE B
WHERE A.PRODUCT_ID = B.PRODUCT_ID
GROUP BY A.PRODUCT_ID
ORDER BY SALES DESC, A.PRODUCT_CODE ASC
;