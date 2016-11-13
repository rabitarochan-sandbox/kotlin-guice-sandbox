package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.ImplementedBy
import kotassert.*
import org.junit.Test

class DefaultImplementsTest() {

    @ImplementedBy(JapaneseSpeaker::class)
    interface Speaker {
        fun sayHello(): String
    }

    class JapaneseSpeaker() : Speaker {
        override fun sayHello(): String {
            return "こんにちは"
        }
    }

    class EnglishSpeaker() : Speaker {
        override fun sayHello(): String {
            return "Hello"
        }
    }

    @Test
    fun defaultImplementsTest() {
        injectorOf().let { injector ->
            injector.getInstance(Speaker::class.java).sayHello().Is("こんにちは")
        }
    }

}
