# Taking dump from PostgreSQL

```
docker exec some-postgres pg_dump -U postgres -F t investment_app > investment_app-2020-05-12.sql

docker exec some-postgres pg_dump -U postgres -F t internal_control_app > internal_control_app-2020-05-12.sql

tar -czfv investment.tar.gz investment_app-2020-07-01.sql
```

# Restore dump from PostgreSQL

```
tar -xzfv investment.tar.gz

docker exec -i some-postgres pg_restore -U postgres -v -d investment_app < investment_app-2020-05-12.sql
docker exec -i some-postgres pg_restore -U postgres -v -d internal_control_app < internal_control_app-2020-05-12.sql
```

## Misc

This script copies dump to test server

```
scp -P 8101 investment_app.sql  issd@178.251.45.187:/home/issd

scp -P 8101 investment.tar.gz  issd@178.251.45.187:/home/issd
```

## Example



```
docker exec some-postgres pg_dump -U postgres -F t investment_app > investment_app-2020-07-01.sql

docker exec -i some-postgres pg_restore -U postgres -v -d investment_app  < investment_app-2020-07-01.sql
```



# vw_ihale_kisim_teklif test

1. pg_admin export all data from vw_ihale_kisim_teklif view
2. convertcsv.com/csv-to-sql.htm
3. run sql on target database

### useful sql queries


```sql
--
select bidding_no,name, bidding_type
from public.internal_control
where bidding_no='2019/503489'

-- Find intersection of internal control and ekap
select * 
from internal_control ic
where ic.bidding_no not in (select ek.ikn from ekap ek)

-- Trim spaces in postgresql
update internal_control
  set biddingNo = replace(biddingNo, ' ', '');
commit;

-- Get teklifler from ikn
select *
FROM public.vw_ihale_kisim_teklif where ikn='2020/169641';
```



#### Ekap table

```sql
SELECT distinct(bolge_no)
	FROM public.ekap;
SELECT distinct(bolge_adi)
	FROM public.ekap;
```