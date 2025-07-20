
# 📅 Kotlin Event Reminder Assistant

This is a console-based Kotlin application that acts as a simple Event Reminder Assistant. It allows users to manage **meetings** and **reminders**, greet users based on the time of day, and view events scheduled in the next 24 hours.

---

## 🚀 Features

- 🕒 Greets the user based on the current hour (morning, evening, night)
- 📝 Add Meetings with title, date, and optional description
- ⏰ Add Reminders with title, date, priority, and optional description
- 🧠 Prevents duplicate titles in meetings/reminders
- 📅 View all events (grouped into Meetings and Reminders)
- 📍 View upcoming events in the next 24 hours
- ❌ Delete events by title and optional type (meeting/reminder)
- 📚 Demonstrates Kotlin features like:
  - Sealed classes
  - Nullable types and smart casting
  - Method overloading
  - Default parameters
  - LocalDateTime and time filtering

---

## 🛠 How to Run

1. Copy the code into IntelliJ IDEA or any Kotlin-supported IDE.
2. Run the `main()` function in the file.
3. Check the console for output and test cases.

---

## 💻 Example Output

```
Good morning
Good morning, Ahmed

====== All Events ======

============ Meetings ============

Event: Meeting
Title: Team Meeting
Date: 2025-07-25 23:00:00
Description: Weekly team sync

Event: Meeting
Title: Standup Meeting
Date: 2025-07-20 23:00:00
Description:

============ Reminders ============

Event: Reminder
Priority: High
Title: Sprints Report
Date: 2025-07-20 01:00:00
Description: Submit this task on the website

Event: Reminder
Priority: Medium
Title: Go to GYM
Date: 2025-07-22 00:00:00
Description: You have push day

============ Near events ============

============ Meetings ============

Event: Meeting
Title: Standup Meeting
Date: 2025-07-20 23:00:00
Description:

============ Reminders ============

Event: Reminder
Priority: High
Title: Sprints Report
Date: 2025-07-20 01:00:00
Description: Submit this task on the website

====== Delete an Existing Event ======
Meeting with title Team Meeting deleted successfully

====== Delete a Non-Existing Event ======
Not found any event with title: Scrum meeting!!

====== Events After Deletion ======
...
```

---

## 📌 Notes

- `greeting()` is overloaded to optionally take a username.
- Uses `LocalDateTime` for date comparison and formatting.
- Reminder priority system:
  - `0..2` → High
  - `3..5` → Medium
  - `6+`   → Low

---

## 🔮 Future Ideas

- Add interactive input instead of hardcoded values
- Persist events to local storage or file
- Support for recurring events
- Sort events dynamically before printing

---

## 🧑‍💻 Author

Ahmed Samy – Kotlin practice project for basic OOP, collections, and time-handling.
