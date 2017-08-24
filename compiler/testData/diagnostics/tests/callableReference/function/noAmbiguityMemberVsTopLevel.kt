// !CHECK_TYPE
// !LANGUAGE: +CallableReferencesToClassMembersWithEmptyLHS

import kotlin.reflect.KFunction0

fun explicitlyExpectFunction0(f: () -> Unit) = f
fun explicitlyExpectFunction1(f: (A) -> Unit) = f

fun foo() {}

class A {
    fun foo() {}
    
    fun main() {
        val x = ::foo

        checkSubtype<KFunction0<Unit>>(x)

        explicitlyExpectFunction0(x)
        explicitlyExpectFunction1(<!TYPE_MISMATCH!>x<!>)

        explicitlyExpectFunction0(::foo)
        explicitlyExpectFunction1(<!TYPE_MISMATCH!>::foo<!>)
    }
}
