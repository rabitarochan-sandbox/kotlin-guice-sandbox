package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import kotassert.Is
import org.junit.Test

class ConstructorInjectionTest() {

    class JavaxConstructorInjectionTestClass
        @javax.inject.Inject constructor (val speaker: Speaker)

    class GoogleConstructorInjectionTestClass
        @com.google.inject.Inject constructor (val speaker: Speaker)

    @Test
    fun constructorInjectionTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(JavaxConstructorInjectionTestClass::class.java).speaker.sayHello().Is("こんにちは")
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(GoogleConstructorInjectionTestClass::class.java).speaker.sayHello().Is("Hello")
        }
    }

}
