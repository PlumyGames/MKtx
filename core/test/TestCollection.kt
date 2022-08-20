import arc.struct.IntSeq
import arc.struct.Seq
import org.junit.jupiter.api.Test
import plumy.core.arc.forLoop
import plumy.core.arc.retain

class TestCollection {
    @Test
    fun `test ordered Int Seq`() {
        val seq = IntSeq(
            intArrayOf(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
            )
        )
        println(seq)
        seq.retain {
            it in 4..6
        }
        println(seq)
    }
    @Test
    fun `test no order Int Seq`() {
        val seq = IntSeq(false, 10).apply {
            addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        }
        println(seq)
        seq.retain {
            it in 4..6
        }
        println(seq)
    }

    @Test
    fun `test foreach`(){
        Seq<Any>().forLoop {

        }
    }
}