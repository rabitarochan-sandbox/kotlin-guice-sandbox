package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.AbstractModule
import org.junit.Test
import kotassert.*

class InstanceInjectionTest() {

    class User(val name: String) {

        fun sayName(): String = "My name is ${name}!"

    }

    object InstanceInjectionModule : AbstractModule() {
        override fun configure() {
            bind(User::class.java).toInstance(User("Alice"))
        }
    }

    @Test
    fun instanceInjectionTest() {
        injectorOf(InstanceInjectionModule).let { injector ->
            injector.getInstance(User::class.java).sayName().Is("My name is Alice!")
        }
    }

}
