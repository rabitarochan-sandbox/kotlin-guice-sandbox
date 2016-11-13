package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import kotassert.*
import org.junit.Test

class PropertyInjectionTest() {

    /**
     * 1. Peoperty に lateinit を付けられない。
     * 2. Backing field `field` のみだとエラーになる
     */
    class PropertyInjecttionTestClass() {

        lateinit private var javaxInjectSpeakerBackingField: Speaker

        var javaxInjectSpeakerProperty: Speaker
            get() = javaxInjectSpeakerBackingField
            @javax.inject.Inject set(value) { javaxInjectSpeakerBackingField = value }

        lateinit private var googleInjectSpeakerBackingField: Speaker

        var googleInjectSpeakerProperty: Speaker
            get() = googleInjectSpeakerBackingField
            @com.google.inject.Inject set(value) { googleInjectSpeakerBackingField = value }
    }

    @Test
    fun propertyInjectionTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(PropertyInjecttionTestClass::class.java).javaxInjectSpeakerProperty.sayHello().Is("こんにちは")
            injector.getInstance(PropertyInjecttionTestClass::class.java).googleInjectSpeakerProperty.sayHello().Is("こんにちは")
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(PropertyInjecttionTestClass::class.java).javaxInjectSpeakerProperty.sayHello().Is("Hello")
            injector.getInstance(PropertyInjecttionTestClass::class.java).googleInjectSpeakerProperty.sayHello().Is("Hello")
        }
    }


}
