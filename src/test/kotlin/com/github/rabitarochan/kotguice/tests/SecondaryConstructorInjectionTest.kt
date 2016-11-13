package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import kotassert.Is
import org.junit.Test

class SecondaryConstructorInjectionTest() {

    class JavaxSecondaryConstructorInjectionTestClass(val speaker: Speaker, val message: String) {
        @javax.inject.Inject
        constructor(speaker: Speaker) : this(speaker, "Javax Inject!")
    }
    class GoogleSecondaryConstructorInjectionTestClass(val speaker: Speaker, val message: String) {
        @com.google.inject.Inject
        constructor(speaker: Speaker) : this(speaker, "Google Inject!")
    }

    @Test
    fun secondaryConstructorInjectionTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(JavaxSecondaryConstructorInjectionTestClass::class.java).let {
                it.speaker.sayHello().Is("こんにちは")
                it.message.Is("Javax Inject!")
            }
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(GoogleSecondaryConstructorInjectionTestClass::class.java).let {
                it.speaker.sayHello().Is("Hello")
                it.message.Is("Google Inject!")
            }
        }
    }

}
