# Introduction

### Local development

#### Run elastic

```shell script
docker run -p 9200:9200 -p 9300:9300 \
    -e 'bootstrap.memory_lock=false' \
    -e 'discovery.type=single-node' \
    -e 'ES_JAVA_OPTS=-Xms1g -Xmx1g' \
	-e 'cluster.routing.allocation.disk.threshold_enabled=false'\
    -d elasticsearch:6.4.3
```
##### Get all docs inside elastic
Useful to debug if doc is persisted in elastic

http://localhost:9200/kgm/_search

#### Run minio

```shell script
docker run -p 9000:9000 -e MINIO_ACCESS_KEY=test1992 -e MINIO_SECRET_KEY=test1992 minio/minio server /data
```

#### Run postgre
```shell script
docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=admin -d postgres:9.5
```

#### Useful requests

Sync elastic

```shell script
curl -X POST http://localhost:27000/kgm/internal-control-app-v2/diagnostics/internal-controls/elastic/sync
curl -X POST http://kgm.test.internal.paraboly.com/sbs/kgm/internal-control-app-v2/diagnostics/internal-controls/elastic/sync
```

#### Swagger page
http://localhost:27000/kgm/internal-control-app-v2/swagger-ui.html

http://kgm.test.internal.paraboly.com/sbs/kgm/internal-control-app-v2/swagger-ui.html#/



## Important lessons

1. `BigDecimal` is used instead of `Float` to handle money operations. `real` was used in `postgresql` which is 
changed to `double precision` type.


### Integrations

#### Ekap

Ekap is system which contains info for tenders and biddings. Internal control app uses EKAP infos from third-party PostgreSQL 
and sync following tables which is made by our ekap-sync module:
1. `ekap` - Ekap class in our entities
2. `vw_ihale_kisim_teklif` - Bidding class in our entities



For more information please go to `ekap-synchronizer` repository

## TODO
Add minimal internal control dump 