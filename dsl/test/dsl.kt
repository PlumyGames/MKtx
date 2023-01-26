import mindustry.content.Items
import mindustry.content.Blocks
//import org.junit.jupiter.api.Test
import plumy.dsl.CreateTechTree

class TestDSL {
    //@Test()
    fun `test TechTree DSL syntax only`() {
        CreateTechTree(name = "TestDSL", Items.copper) {
            Items.lead {
                Blocks.sorter()
            }
        }
    }
}
