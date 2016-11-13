package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import kotassert.*
import org.junit.Test

class GetInstanceTest() {

    class Helloworld() {
        fun hello(): String = "Hello Google Guice!"
    }

    @Test
    fun getInstanceTest() {
        injectorOf().let { injector ->
            injector.getInstance(Helloworld::class.java).hello().Is("Hello Google Guice!")
        }
    }

    @Test
    fun getInstanceByInterfaceTest() {
        injectorOf(JapaneseSpeakerModule).let { injector ->
            injector.getInstance(Speaker::class.java).sayHello().Is("こんにちは")
        }

        injectorOf(EnglishSpeakerModule).let { injector ->
            injector.getInstance(Speaker::class.java).sayHello().Is("Hello")
        }
    }

}
