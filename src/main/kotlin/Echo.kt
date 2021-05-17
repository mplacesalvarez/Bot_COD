
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold

fun main() {


    /**Instanciamos un objeto bot*/
    val bot = bot {

        /**Introducimos el token del bot*/
        token = "1891547109:AAGy7E2lFULRvOeBBeOeXUFyiaJr7sVTI6o"

        /**El dispatch nos permite enviar información a Telegram para manejar el bot*/
        dispatch {

            text {

                print("Chat ID: ")
                println(message.chat.id)
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = text)
            }


            /**Creamos un comando que devolvera el mensaje que indiquemos*/
            command("start") {

                val result = bot.sendMessage(chatId = ChatId.fromId(update.message!!.chat.id), text = "Bot iniciado")

                result.fold(
                    {

                    },
                    {
                        // do something with the error
                    }
                )
            }
        }
    }
    bot.startPolling()
}