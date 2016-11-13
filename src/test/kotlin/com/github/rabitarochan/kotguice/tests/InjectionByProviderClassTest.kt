package com.github.rabitarochan.kotguice.tests

import com.github.rabitarochan.kotguice.injectorOf
import com.google.inject.AbstractModule
import kotassert.Is
import org.junit.Test

class InjectionByProviderClassTest() {

    class User(val name: String) {
        fun sayName(): String = "My name is ${name}!"
    }

    class UserJavaxProvider() : javax.inject.Provider<User> {
        override fun get(): User {
            return User("Alice")
        }
    }

    class UserGoogleProvider() : com.google.inject.Provider<User> {
        override fun get(): User {
            return User("Bob")
        }
    }

    object JavaxProviderModule : AbstractModule() {
        override fun configure() {
            bind(User::class.java).toProvider(UserJavaxProvider::class.java)
        }
    }

    object GoogleProviderModule : AbstractModule() {
        override fun configure() {
            bind(User::class.java).toProvider(UserGoogleProvider::class.java)
        }
    }

    @Test
    fun injectionByProviderClassTest() {
        injectorOf(JavaxProviderModule).let { injector ->
            injector.getInstance(User::class.java).sayName().Is("My name is Alice!")
        }

        injectorOf(GoogleProviderModule).let { injector ->
            injector.getInstance(User::class.java).sayName().Is("My name is Bob!")
        }
    }

}
