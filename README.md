# RandomValue
더 이상 무작위 값 생성을 위해 시간을 낭비하지 마세요! 길이와 보안 강도, 그리고 난수를 생성하는 클래스만 있으면 됩니다!

## 코드품질 테스트 결과
<div align="center">
    <a href="https://sonarcloud.io/summary/new_code?id=retrotv-maven-repo_random-value">
        <img src="https://sonarcloud.io/api/project_badges/quality_gate?project=retrotv-maven-repo_random-value" alt="Quality Gate Status" />
    </a>
</div>
<div align="center">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=retrotv-maven-repo_random-value&metric=bugs" alt="Bugs Count" />
    <img src="https://sonarcloud.io/api/project_badges/measure?project=retrotv-maven-repo_random-value&metric=code_smells" alt="Code Smells Count" />
    <img src="https://sonarcloud.io/api/project_badges/measure?project=retrotv-maven-repo_random-value&metric=duplicated_lines_density" alt="Duplicated Lines (%)" />
    <img src="https://sonarcloud.io/api/project_badges/measure?project=retrotv-maven-repo_random-value&metric=coverage" alt="Test Coverage" />
</div>

## 라이브러리 다운로드
[![](https://jitpack.io/v/retrotv-maven-repo/random-value.svg)](https://jitpack.io/#retrotv-maven-repo/random-value)

### Maven

#### Step 1. pom.xml에 JitPack 리포지토리를 추가합니다.
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Step 2. 의존성을 추가합니다. (Tag에 원하는 버전을 지정하세요.)
```xml
<dependency>
    <groupId>com.github.retrotv-maven-repo</groupId>
    <artifactId>random-value</artifactId>
    <version>Tag</version>
</dependency>
```

### Gradle

#### Step 1. build.gradle 및 build.gradle.kts에 JitPack 리포지토리를 추가합니다.
##### build.gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```
##### build.gradle.kts
```gradle.kts
repositories {
    maven { setUrl("https://jitpack.io") }
}
```

#### Step 2. 의존성을 추가합니다. (Tag에 원하는 버전을 지정하세요.)
##### build.gradle
```gradle
dependencies {
    implementation 'com.github.retrotv-maven-repo:random-value:Tag'
}
```
##### build.gradle.kts
```gradle.kts
dependencies {
    implementation("com.github.retrotv-maven-repo:random-value:Tag")
}
```
## 예제
### Java
```java
PasswordGenerator pg = new PasswordGenerator(SecurityStrength.HIGH, new SecureRandom());
String password = pg.generate(16);
```

### Kotlin
```kotlin
val pg = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
val password = pg.generate(16)
```
