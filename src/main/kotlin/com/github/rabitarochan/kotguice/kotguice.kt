package com.github.rabitarochan.kotguice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module

fun injectorOf(vararg modules: Module): Injector = Guice.createInjector(*modules)
