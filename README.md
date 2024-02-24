# scopa

The traditional italian card game, but poorly implemented in Kotlin.

## Requirements

* Java 17 (openjdk)
* IntelliJ IDEA 2023 (latest version should also work fine)
* Gradle (8.4)
* Kotlin (1.7.20)

## Local setup (for development)

1. Clone the repository.

```bash
git clone https://github.com/hamburguesa66/scopa
```

2. Import the project into IDEA. 

> The IDE should install/configure the required plugins (Kotlin, Gradle) automatically.

3. Execute the app using the "Run Game" run configuration.

4. Run the tests using the "Unit Tests" run configuration.

## Release

1. Run the following command in the root folder to generate a "fat" JAR.

```bash
./gradlew build
```

> The new artifact should be located in ./build/libs. The name would be scopa-A.B.C.jar

2. Then, you should be able to execute the game with Java.

```bash
java -jar scopa-1.0.0.jar
```