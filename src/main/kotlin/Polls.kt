import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.pollAnswer
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.polls.PollType


fun main() {
    val bot = bot {

        token = "1891547109:AAGy7E2lFULRvOeBBeOeXUFyiaJr7sVTI6o"

        dispatch {

            pollAnswer {
                println("${pollAnswer.user.username} has selected the option ${pollAnswer.optionIds.lastOrNull()} in the poll ${pollAnswer.pollId}")
            }

            command("mates") {

                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),
                    type = PollType.QUIZ,
                    question = "Cuánto es 2+2?",
                    options = listOf("1", "5","4","22"),
                    correctOptionId = 2, // index of the correct option,
                    isAnonymous = false
                )
            }


            command("encuesta") {
                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),
                    question = "Cuando podremos salir de fiesta?",
                    options = listOf(
                        "2021",
                        "2022",
                        "2023",
                        "Nunca",


                        ),
                    openPeriod = 120,
                    //Permite que sean escogidas varias respuestas
                    allowsMultipleAnswers = true,
                    isAnonymous = false
                )
            }

        }
    }
    bot.startPolling()
}