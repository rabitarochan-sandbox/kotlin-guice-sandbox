package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.AbstractModule
import com.google.inject.BindingAnnotation
import kotassert.*
import org.junit.Test

class InjectorWithAnnotationTest() {

    @BindingAnnotation
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    annotation class Japanese()

    @BindingAnnotation
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    annotation class English()

    class InjectionWithAnnotationTestClass() {

        @javax.inject.Inject
        @Japanese
        lateinit var japaneseJavaxInjectSpeaker: Speaker

        @javax.inject.Inject
        @English
        lateinit var englishJavaxInjectSpeaker: Speaker

        @com.google.inject.Inject
        @Japanese
        lateinit var japaneseGoogleInjectSpeaker: Speaker

        @com.google.inject.Inject
        @English
        lateinit var englishGoogleInjectSpeaker: Speaker

    }

    @Test
    fun injectionWithAnnotationTest() {
        val injector = injectorOf(object : AbstractModule() {
            override fun configure() {
                bind(Speaker::class.java).annotatedWith(Japanese::class.java).to(JapaneseSpeaker::class.java)
                bind(Speaker::class.java).annotatedWith(English::class.java).to(EnglishSpeaker::class.java)
            }
        })

        injector.getInstance(InjectionWithAnnotationTestClass::class.java).let {
            it.japaneseJavaxInjectSpeaker.sayHello().Is("こんにちは")
            it.englishJavaxInjectSpeaker.sayHello().Is("Hello")

            it.japaneseGoogleInjectSpeaker.sayHello().Is("こんにちは")
            it.englishGoogleInjectSpeaker.sayHello().Is("Hello")
        }
    }

}
