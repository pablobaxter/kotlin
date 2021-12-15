// MODULE: m1-common
// FILE: common.kt

expect class Ok(x: Int, y: String = "")

expect class FailX(x: Int, y: String = "")

expect class FailY(x: Int, y: String = "")

fun test() {
    Ok()
    Ok(42)
    Ok(42, "OK")
}

// MODULE: m2-jvm()()(m1-common)
// FILE: jvm.kt

actual class Ok actual constructor(x: Int, y: String)

actual class FailX actual constructor(x: Int = 0, y: String)

actual class FailY actual constructor(x: Int, y: String = "")

fun testJvm() {
    Ok()
    Ok(42)
    Ok(42, "OK")
}