// ISSUE: KT-52469
// LANGUAGE: +ProhibitIntersectionReifiedTypeParameter

interface InterfaceA
interface InterfaceB

inline fun <reified T> reifiedType(value: T) {}
inline fun <reified T> reifiedTypes(vararg values: T) {}

fun <T> testTypeParamter(value: T) {
    when (value) {
        is InterfaceA -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        is InterfaceB -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        is String -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        is Int -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        is List<*> -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        else -> <!TYPE_PARAMETER_AS_REIFIED!>reifiedType<!>(value)
    }
}

fun testInterfaces(value: InterfaceA) {
    when (value) {
        is InterfaceB -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        <!USELESS_IS_CHECK!>is String<!> -> <!INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION, TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        <!USELESS_IS_CHECK!>is Int<!> -> <!INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION, TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        is List<*> -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedType<!>(value)
        else -> reifiedType(value)
    }
}

fun testArray(value: InterfaceA) {
    when (value) {
        is InterfaceB -> <!INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION, TYPE_INTERSECTION_AS_REIFIED_ERROR!>Array<!>(1) { _ -> value }
        <!USELESS_IS_CHECK!>is String<!> -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>Array<!>(1) { _ -> value }
        <!USELESS_IS_CHECK!>is Int<!> -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>Array<!>(1) { _ -> value }
        is List<*> -> <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>Array<!>(1) { _ -> value }
        else -> Array(1) { _ -> value }
    }
}

fun testParameters() {
    <!TYPE_INTERSECTION_AS_REIFIED_ERROR!>reifiedTypes<!>(1, "2", false)
    reifiedTypes<Any>(1, "2", false)
}
