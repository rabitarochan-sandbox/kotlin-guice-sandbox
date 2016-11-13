package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import kotassert.*
import org.junit.Test

class FieldInjectionTest() {

    class FieldInjectionTestClass() {

        @javax.inject.Inject
        lateinit var javaxInjectSpeaker: Speaker

        @com.google.inject.Inject
        lateinit var googleInjectSpeaker: Speaker

    }

    @Test
    fun fieldInjectionTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(FieldInjectionTestClass::class.java).javaxInjectSpeaker.sayHello().Is("こんにちは")
            injector.getInstance(FieldInjectionTestClass::class.java).googleInjectSpeaker.sayHello().Is("こんにちは")
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(FieldInjectionTestClass::class.java).javaxInjectSpeaker.sayHello().Is("Hello")
            injector.getInstance(FieldInjectionTestClass::class.java).googleInjectSpeaker.sayHello().Is("Hello")
        }
    }

}
