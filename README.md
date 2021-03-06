## Running as Java Standalone

#### Download the latest releases
https://github.com/gustavohenrique/example-spark-framework-java/releases

#### or

```
git clone https://github.com/gustavohenrique/example-spark-framework-java.git
cd example-spark-framework-java
mvn package && java -jar target/spotippos-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Running with Docker

```
docker run -d -p 9999:9999 gustavohenrique/example-spark-framework-java
```

## Playing with the REST Api

#### Adding a Property

```
curl -XPOST -H "content-type:application/json" -d '{"title": "Imóvel código 1, com 5 quartos e 4 banheiros", "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "price": 1250000, "x": 222, "y": 444, "beds": 4, "baths": 3, "squareMeters": 210}' "http://localhost:9999/properties/"
```

#### Finding a Property by ID

```
curl -H "content-type:application/json" "http://localhost:9999/properties/665"
```

#### Finding Properties by area

```
curl -H "content-type:application/json" "http://localhost:9999/properties?ax=0&ay=0&bx=1000&by=1400"
```

## License

MIT
