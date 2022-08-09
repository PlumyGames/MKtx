
<br>

<div align = center>

[![Badge Discord]][Discord]   
[![Badge License]][License]

<br>

# MK Utils

*Mindustry Kotlin Utils*

<br>

[![Badge Jitpack]][Jitpack]

<br>
<br>

## Modules

<br>

|   Name    | Dependency |         Description         |
|:---------:|:----------:|:---------------------------:|
|  `core`   |    */*     | Basic typealias and utility |
| `texture` |    */*     |       Pixmap process        |
|  `world`  |    */*     |    Entity and Vars.world    |

</div>

<br>
<br>

## Usage

<br>

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
        implementation("com.github.plumygame:mkutils:<module>:<version>")
    }
    ```

<br>


<!----------------------------------------------------------------------------->

[Jitpack]: https://jitpack.io/#plumygame/mkutils
[Discord]: https://discord.gg/PDwyxM3waw

[License]: LICENSE


<!----------------------------------[ Badges ]--------------------------------->

[Badge Discord]: https://img.shields.io/discord/937228972041842718?color=454fc1&label=Discord&logo=Discord&style=for-the-badge&logoColor=white&labelColor=5865F2
[Badge License]: https://img.shields.io/badge/License-GPL3-015d93.svg?style=for-the-badge&labelColor=blue&logoColor=white&logo=GNU
[Badge Jitpack]: https://jitpack.io/v/plumygame/mkutils.svg?style=flat
