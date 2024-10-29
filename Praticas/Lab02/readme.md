# CBD Lab 2

Sample workspace for completing the CBD Lab 2.

This workspace provides a docker-compose file to run the MongoDB server, and it's companions, in a dockerized enviromnment.

The [resources folder](resources) is automatically mounted to `/resources` in the MongoDB container.
It contains some assets required to complete the Lab.

Open `mongosh` on the container:
`docker compose exec -it mongodb mongosh --db cbd`

Import restaurants: 
`docker compose exec -it mongodb mongoimport --db cbd --collection restaurants --drop --file /resources/restaurants.json`


## Additional Notes

* Make sure you have previously installed Docker Desktop, or at least Docker Engine.
// TODO: Add Links


## Starting the lab02

```bash
docker compose up -d
```

1. Open `mongosh` on the container:

```bash
docker compose exec -it mongodb mongosh --db cb d
```

2. Import restaurants:

```bash
docker compose exec -it mongodb mongoimport --db cbd --collection restaurants --drop --file /resources/restaurants.json
```

## Ex 2.5 b)

cbd> db.phones.count();
DeprecationWarning: Collection.count() is deprecated. Use countDocuments or estimatedDocumentCount.
200000
cbd> db.phones.find().limit(10).pretty();
[
  {
    _id: 351232000001,
    components: { country: 351, prefix: 232, number: 1 },
    display: '+351-232000001'
  },
  {
    _id: 351233000002,
    components: { country: 351, prefix: 233, number: 2 },
    display: '+351-233000002'
  },
  {
    _id: 351233000003,
    components: { country: 351, prefix: 233, number: 3 },
    display: '+351-233000003'
  },
  {
    _id: 351232000004,
    components: { country: 351, prefix: 232, number: 4 },
    display: '+351-232000004'
  },
  {
    _id: 351210000005,
    components: { country: 351, prefix: 21, number: 5 },
    display: '+351-210000005'
  },
  {
    _id: 351232000006,
    components: { country: 351, prefix: 232, number: 6 },
    display: '+351-232000006'
  },
  {
    _id: 351234000007,
    components: { country: 351, prefix: 234, number: 7 },
    display: '+351-234000007'
  },
  {
    _id: 351232000008,
    components: { country: 351, prefix: 232, number: 8 },
    display: '+351-232000008'
  },
  {
    _id: 351234000009,
    components: { country: 351, prefix: 234, number: 9 },
    display: '+351-234000009'
  },
  {
    _id: 351231000010,
    components: { country: 351, prefix: 231, number: 10 },
    display: '+351-231000010'
  }
]

## Ex 2.5 c)

cbd> function countPhonesByPrefix() {
...     return db.phones.aggregate([
...         {
...             $group: {
...                 _id: "$components.prefix",
...                 count: { $sum: 1 }
...             }
...         },
...         {
...             $sort: { count: -1 }
...         }
...     ]).toArray();
... }
[Function: countPhonesByPrefix]
cbd> 

cbd> const result = countPhonesByPrefix();

cbd> printjson(result);
[
  {
    _id: 233,
    count: 33607
  },
  {
    _id: 234,
    count: 33402
  },
  {
    _id: 232,
    count: 33353
  },
  {
    _id: 231,
    count: 33266
  },
  {
    _id: 22,
    count: 33232
  },
  {
    _id: 21,
    count: 33140
  }
]

