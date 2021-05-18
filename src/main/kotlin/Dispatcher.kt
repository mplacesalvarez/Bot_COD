import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.entities.dice.DiceEmoji
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.network.fold

fun main (){

    val bot = bot {

        token = "1891547109:AAGy7E2lFULRvOeBBeOeXUFyiaJr7sVTI6o"

        dispatch {

            command("hola"){
                val result=bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text= "Saludos :)")
                result.fold ({ },{ })

            }
            command("nose") {
                val result =
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "nose que decir")
                result.fold({
                    // do something here with the response
                }, {
                    // do something with the error
                })
            }

            command("adios"){
                val result=bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text= "Hasta pronto")
                result.fold ({  },{ }
                )
            }

            message(Filter.Reply or Filter.Forward){
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text= "No me respondas")


            }
            message(Filter.Reply or Filter.Forward){
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text= "Aprende a leer")


            }


            message (Filter.Sticker){
                bot.sendMessage(ChatId.fromId(message.chat.id), text = "Prueba con otro")
            }

            command("elige"){
                val inlineKeyboardMarkup = InlineKeyboardMarkup.create(

                    listOf(InlineKeyboardButton.CallbackData(text ="Boton 1 ", callbackData = "quiero aprobar")),
                    listOf(InlineKeyboardButton.CallbackData(text = "Boton 2",callbackData = "dame dinerito"))

                )
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Presiona un botón",
                    replyMarkup = inlineKeyboardMarkup
                )
            }

            callbackQuery("quiero aprobar") {
                val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
            }

            callbackQuery(
                callbackData = "dame dinerito",
                callbackAnswerText = "No puc mes",
                callbackAnswerShowAlert = true
            ) {
                val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
            }


            command("slot") {
                bot.sendDice(ChatId.fromId(message.chat.id), DiceEmoji.SlotMachine)
            }

            telegramError {
                println(error.getErrorMessage())
            }







            command("video"){
                val markdownV2Text = """
                    [inline URL](https://www.youtube.com/watch?v=Hyw6kKMjp5A)
                """.trimIndent()
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = markdownV2Text,
                    parseMode = ParseMode.MARKDOWN_V2
                )
            }


            command("fotos") {
                bot.sendMediaGroup(
                    chatId = ChatId.fromId(message.chat.id),
                    mediaGroup = MediaGroup.from(
                        InputMediaPhoto(
                            media = TelegramFile.ByUrl("https://st4.depositphotos.com/3258807/25375/i/1600/depositphotos_253756078-stock-photo-disgusting-drunk-man-grabbing-bottle.jpg"),
                            caption = "Asi estaba"
                        ),
                        InputMediaPhoto(
                            media = TelegramFile.ByUrl("https://previews.123rf.com/images/kolotype/kolotype1502/kolotype150200106/36622613-cierre-de-borracho-viejo-calvo-hombre-apoyado-en-la-mesa-de-madera-con-vodka-con-un-vaso-peque%C3%B1o.jpg"),
                            caption = "de fiesta"
                        )
                    ),
                    replyToMessageId = message.messageId
                )
            }

        }

    }
    bot.startPolling()
}