<div align = center>

# MKtx

**K**o**t**lin E**x**tension for **M**industry

[![Badge Discord]][Discord]
[![Badge License]][License]

[![Badge Jitpack]][Jitpack]

</div>

## Modules

|    Name     | Dependency |         Description         |
|:-----------:|:----------:|:---------------------------:|
|   `core`    |    */*     | Basic typealias and utility |
|  `texture`  |    */*     |       Pixmap process        |
|   `world`   |    */*     |           Entity            |
| `animation` |   `core`   |   Animation state machine   |
|    `dsl`    |    */*     |             DSL             |

## Usage

1. Add the **JitPack** repository to your `build.gradle.kts`

    ```Kotlin
    repositories {
        maven { url = uri("https://www.jitpack.io") }
    }
    ```

    <br>

2. Add the **[dependency][Jitpack]**.

    ```Kotlin
     dependencies {
        implementation("com.github.plumygames:mktx:<module>:<version>")
    }
    ```

<br>


<!----------------------------------------------------------------------------->

[Jitpack]: https://jitpack.io/#plumygames/mktx

[Discord]: https://discord.gg/PDwyxM3waw

[License]: LICENSE


<!----------------------------------[ Badges ]--------------------------------->

[Badge Discord]: https://img.shields.io/discord/937228972041842718?color=454fc1&label=Discord&logo=Discord&style=for-the-badge&logoColor=white&labelColor=5865F2

[Badge License]: https://img.shields.io/badge/License-GPL3-015d93.svg?style=for-the-badge&labelColor=blue&logoColor=white&logo=GNU

[Badge Jitpack]: https://jitpack.io/v/plumygames/mktx.svg?style=flat
