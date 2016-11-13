package com.github.rabitarochan.kotguice.tests

import com.google.inject.AbstractModule

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

object JapaneseSpeakerModule : AbstractModule() {
    override fun configure() {
        bind(Speaker::class.java).to(JapaneseSpeaker::class.java)
    }
}

object EnglishSpeakerModule : AbstractModule() {
    override fun configure() {
        bind(Speaker::class.java).to(EnglishSpeaker::class.java)
    }
}
