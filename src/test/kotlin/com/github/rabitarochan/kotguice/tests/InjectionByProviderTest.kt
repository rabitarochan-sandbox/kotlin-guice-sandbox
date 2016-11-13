package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.AbstractModule
import com.google.inject.Provides
import kotassert.*
import org.junit.Test

class InjectionByProviderTest() {

    class User(val name: String) {

        fun sayName(): String = "My name is ${name}!"

    }

    object InjectionByProviderModule : AbstractModule() {
        override fun configure() {
            // no op
        }

        @Provides
        private fun provideUser() : User = User("Bob")

    }

    @Test
    fun injectionByProviderTest() {
        injectorOf(InjectionByProviderModule).let { injector ->
            injector.getInstance(User::class.java).sayName().Is("My name is Bob!")
        }
    }
}
