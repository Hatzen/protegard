package de.hartz.software.protegard.controller.generative.content.deprecated

import de.hartz.software.protegard.controller.generative.content.ContentGenerationModelCommunication
import dev.langchain4j.data.message.AiMessage
import dev.langchain4j.exception.IllegalConfigurationException
import dev.langchain4j.model.StreamingResponseHandler
import dev.langchain4j.model.language.LanguageModel
import dev.langchain4j.model.language.StreamingLanguageModel
import dev.langchain4j.model.output.Response
import dev.langchain4j.rag.content.Content
import dev.langchain4j.service.*
import java.lang.reflect.Method
import java.util.function.Consumer


class SimpleModelCommunication(
    val languageModel: StreamingLanguageModel,
    val languageModel2: LanguageModel
) : ContentGenerationModelCommunication {
    override fun getNarratorBasedContent(message: String, language: String, chapter: String, id: Int): TokenStream {
        val _this = object : Any() {}.javaClass.enclosingMethod
        val message = Message.createFromMethod(_this)

        val tokenstream = object : TokenStream {
            override fun onRetrieved(contentHandler: Consumer<MutableList<Content>>?): TokenStream {
                TODO("Not yet implemented")
            }

            override fun onNext(tokenHandler: Consumer<String>?): TokenStream {
                TODO("Not yet implemented")
            }

            override fun onComplete(completionHandler: Consumer<Response<AiMessage>>?): TokenStream {
                TODO("Not yet implemented")
            }

            override fun onError(errorHandler: Consumer<Throwable>?): TokenStream {
                TODO("Not yet implemented")
            }

            override fun ignoreErrors(): TokenStream {
                TODO("Not yet implemented")
            }

            override fun start() {
                TODO("Not yet implemented")
            }

        }
        val handler = object : StreamingResponseHandler<String> {
            override fun onNext(token: String?) {
                tokenstream.onNext(null)
            }

            override fun onError(error: Throwable?) {
                tokenstream.onError(null)
            }
        }
        languageModel.generate(message.toString(), handler)
        return tokenstream
    }

    override fun generateStoryIndependentStuff(message: String, language: String, id: Int): TokenStream {
        TODO("Not yet implemented")
    }

    override fun isCorrectAnswerToPreviousGeneratedQuestion(message: String, id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTechnicalResponse(message: String, id: Int): TokenStream {
        TODO("Not yet implemented")
    }

    fun validateParameters(method: Method) {
        val parameters = method.parameters
        if (parameters != null && parameters.size >= 2) {
            val var2 = parameters
            val var3 = parameters.size

            for (var4 in 0 until var3) {
                val parameter = var2[var4]
                if (parameter.getAnnotation(V::class.java) == null && parameter.getAnnotation(
                        UserMessage::class.java
                    ) == null && parameter.getAnnotation(MemoryId::class.java) == null && parameter.getAnnotation(
                        UserName::class.java
                    ) == null
                ) {
                    throw IllegalConfigurationException.illegalConfiguration(
                        "Parameter '%s' of method '%s' should be annotated with @V or @UserMessage or @UserName or @MemoryId",
                        *arrayOf<Any>(parameter.name, method.name)
                    )
                }
            }
        }
    }
}

class Message(
    val usermessage: String,
    val placeholders: Map<String, String> = HashMap(),
    val systemMessage: String? = null
) {

    companion object {
        fun createFromMethod(method: Method): Message {
            val systemMessage = method.getAnnotation(SystemMessage::class.java)?.value?.get(0)

            var userMessage: String? = null
            val placeholders = HashMap<String, String>()
            method.parameters?.forEach {
                val parameterName = it.name
                val value = it.getAnnotation(V::class.java)?.value
                if (value != null) {
                    placeholders[parameterName] = value
                    return@forEach
                }

                userMessage = it.getAnnotation(UserMessage::class.java)?.value?.get(0)
            }
            return Message(userMessage!!, placeholders, systemMessage)
        }
    }

    override fun toString(): String {
        var template = """
            Context: 
            
            $systemMessage
            
            Real Task:
            $usermessage
            
        """.trimIndent()

        placeholders.forEach {
            template = template.replace("{{${it.value}}}", it.value)
        }

        return template
    }

}