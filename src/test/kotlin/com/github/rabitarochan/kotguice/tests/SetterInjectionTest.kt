package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.Inject
import kotassert.Is
import org.junit.Test

class SetterInjectionTest() {

    class SetterInjecttionTestClass() {

        lateinit var javaxInjectSpeakerField: Speaker

        @javax.inject.Inject
        fun setJavaxInjectSpeaker(speaker: Speaker) {
            javaxInjectSpeakerField = speaker
        }

        lateinit var googleInjectSpeakerField: Speaker

        @Inject
        fun setGoogleInjectSpeaker(speaker: Speaker) {
            googleInjectSpeakerField = speaker
        }

    }

    @Test
    fun setterInjectionTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(SetterInjecttionTestClass::class.java).javaxInjectSpeakerField.sayHello().Is("こんにちは")
            injector.getInstance(SetterInjecttionTestClass::class.java).googleInjectSpeakerField.sayHello().Is("こんにちは")
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(SetterInjecttionTestClass::class.java).javaxInjectSpeakerField.sayHello().Is("Hello")
            injector.getInstance(SetterInjecttionTestClass::class.java).googleInjectSpeakerField.sayHello().Is("Hello")
        }

    }


}
