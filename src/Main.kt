import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val currentHour = LocalDateTime.now().hour
val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
fun greeting(name: String) {
    when (currentHour) {
        in 0..12 -> println("Good morning,$name") // Morning
        in 12..15 -> println("Good evening, $name") // Afternoon
        else -> println("Good night, $name")
    }
}

fun greeting() {
    when (currentHour) {
        in 0..12 -> println("Good morning") // Morning
        in 12..15 -> println("Good evening") // Afternoon
        else -> println("Good night")
    }
}

sealed class Event(val title: String, val date: LocalDateTime, val description: String?) {
    abstract fun printEvent()
}

class Meeting(title: String, date: LocalDateTime, description: String?) : Event(title, date, description) {
    override fun printEvent() {
        println("Event: Meeting")
        println("Title: $title")
        println("Date: ${date.format(formatter)}")
        println("Description: ${description ?: ""}")
    }

}

class Reminder(title: String, date: LocalDateTime, val priority: Int, description: String?) :
    Event(title, date, description) {
    override fun printEvent() {
        println("Event: Reminder")
        print("Priority: ")
        when (priority) {
            in 0..2 -> println("High")
            in 3..5 -> println("Medium")
            else -> println("Low")
        }
        println("Title: $title")
        println("Date: ${date.format(formatter)}")
        println("Description: ${description ?: ""}")
    }

}

class Events {
    val meetings = mutableListOf<Meeting>()
    val reminders = mutableListOf<Reminder>()

    constructor() {
        meetings.sortWith(compareBy<Meeting> { it.date })
        reminders.sortWith(compareBy<Reminder> { it.date }.thenBy { it.priority })
    }

    fun addEvent(title: String, date: LocalDateTime, description: String? = null) {
        val newMeeting = Meeting(title, date, description)
        val result =
            meetings.find { it.title == newMeeting.title }
        if (result == null) {
            meetings.add(newMeeting)
        } else {
            println("Unable to add 2 meetings with same title")
        }
    }

    fun addEvent(title: String, date: LocalDateTime, priority: Int, description: String? = null) {
        val newReminder = Reminder(title, date, priority, description)
        val result =
            reminders.find { it.title == newReminder.title }
        if (result == null) {
            reminders.add(newReminder)
        } else {
            println("Unable to add 2 reminders with same title")
        }


    }

    fun printEvents() {
        println("============ Meetings ============")
        println()
        for (meeting in meetings) {
            meeting.printEvent()
            println()
        }
        println("============ Reminders ============")
        println()
        for (reminder in reminders) {
            reminder.printEvent()
            println()
        }
    }

    fun deleteEvent(title: String, type: Int? = null) {
        when (type) {
            0 -> {
                val res = meetings.removeIf { it.title == title }
                if (res) {
                    println("Meeting with title $title deleted successfully")
                } else {
                    println("Not found any meeting with title: $title")

                }
            }

            1 -> {
                val res = reminders.removeIf { it.title == title }
                if (res) {
                    println("Reminder with title $title deleted successfully")
                } else {
                    println("Not found any reminder with title: $title")

                }
            }

            else -> {
                var res = meetings.removeIf { it.title == title }
                if (res) {
                    println("Meeting with title $title deleted successfully")
                    return
                }
                res = reminders.removeIf { it.title == title }
                if (res) {
                    println("Reminder with title $title deleted successfully")
                } else {
                    println("Not found any event with title: $title!!")
                }

            }
        }
    }

    fun printNearEvents() {
        println("============ Near events ============")
        val now = LocalDateTime.now()
        val oneDayLater = now.plusDays(1)
        val nearMeetings = meetings.filter { it.date.isAfter(now) && it.date.isBefore(oneDayLater) }
        val nearReminders = reminders.filter { it.date.isAfter(now) && it.date.isBefore(oneDayLater) }
        println("============ Meetings ============")
        println()
        for (meeting in nearMeetings) {
            meeting.printEvent()
            println()
        }
        println("============ Reminders ============")
        println()
        for (reminder in nearReminders) {
            reminder.printEvent()
            println()
        }
    }
}


fun main() {
    //Greeting unknown
    greeting()
    //Greeting known
    greeting("Ahmed")
    // Creating events
    val events = Events()
    // Adding meeting after 3 hours
    val meeting1 = LocalDateTime.now().plusHours(3)
    events.addEvent(
        title = "Team Meeting",
        date = meeting1.plusDays(5),
        description = "Weekly team sync"
    )
    // Trying to add meeting with same title
    events.addEvent(
        title = "Team Meeting",
        date = meeting1.plusDays(1),
        description = "Duplicate meeting test"
    )
    events.addEvent(
        title = "Standup Meeting",
        date = meeting1,
    )
    // Adding reminder after 5 hours
    val reminder1 = LocalDateTime.now().plusHours(5)
    events.addEvent(
        title = "Sprints Report",
        date = reminder1,
        priority = 2,
        description = "Submit this task on the website"
    )
    //Adding reminder after 2 days
    val reminder2 = LocalDateTime.now().plusDays(2)
    events.addEvent(
        title = "Go to GYM",
        date = reminder2,
        priority = 4,
        description = "You have push day"
    )
    println()
    println("====== All Events ======")
    events.printEvents()
    events.printNearEvents()
    println()
    println("====== Delete an Existing Event ======")
    events.deleteEvent("Team Meeting", 0)
    println()
    println("====== Delete a Non-Existing Event ======")
    events.deleteEvent("Scrum meeting")
    println()
    println("====== Events After Deletion ======")
    events.printEvents()
}

