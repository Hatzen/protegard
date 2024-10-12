package org.example.controller.generative

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

// TODO: Make it an interface and create a custom LLama response.
class ChatGPTAdventure {
    /**
     *
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getDynamicResponse(context: String): String {
        if (API_KEY == null) {
            return "The cake is a lie.."
        }
        val response = getGPTResponse(context)
        return response
    }

    companion object {
        private const val API_URL = "https://api.openai.com/v1/completions"

        // TODO: Get api key from env variables or not checked in config file.
        private val API_KEY: String? = null

        @Throws(Exception::class)
        private fun getGPTResponse(prompt: String): String {
            val url = URL(API_URL)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY)
            connection.doOutput = true

            val jsonInputString = "{\"model\": \"text-davinci-003\", \"prompt\": \"$prompt\", \"max_tokens\": 150}"
            connection.outputStream.use { os ->
                val input = jsonInputString.toByteArray(StandardCharsets.UTF_8)
                os.write(input, 0, input.size)
            }
            connection.inputStream.use { `is` ->
                BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8)).use { br ->
                    val response = StringBuilder()
                    var responseLine: String
                    while ((br.readLine().also { responseLine = it }) != null) {
                        response.append(responseLine.trim { it <= ' ' })
                    }
                    return response.toString()
                }
            }
        }
    }
}