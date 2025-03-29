import TreeOfLifeParserTests.ExprGrammar.Expr
import arrow.core.getOrElse
import io.kpeg.PegParser
import io.kpeg.pe.Symbol
import org.junit.jupiter.api.Test

class TreeOfLifeParserTests {

    object ExprGrammar {

        val Expr = Symbol.rule<Int>(name = "Expr") {
            seq {
                val init = +Num
                val operations = +Additive.zeroOrMore()

                value {
                    operations.get.fold(init.get) { lhs, (op, rhs) ->
                        when (op) {
                            Op.Add -> lhs + rhs
                            Op.Sub -> lhs - rhs
                        }
                    }
                }
            }
        }

        private val Additive = Symbol.rule<Pair<Op, Int>>(name = "Additive") {
            seq {
                val op = +char('+', '-').mapPe { Op.fromChar(it) }
                val rhs = +Num

                value { op.get to rhs.get }
            }
        }

        private val Num = Symbol.rule<Int>(name = "Num", ignoreWS = false) {
            seq {
                val sign = +char('+', '-').orDefault('+')
                val digits = +DIGIT.oneOrMore().joinToString()

                value { (sign.get + digits.get).toInt() }
            }
        }

        private enum class Op {
            Add,
            Sub;

            companion object {
                fun fromChar(c: Char) = when (c) {
                    '+' -> Add
                    '-' -> Sub
                    else -> error("")
                }
            }
        }
    }

    fun evalExpr(expression: String) = PegParser.parse(symbol = Expr.value(), expression).getOrElse { null }

    @Test
    fun test1() {
    }
}

