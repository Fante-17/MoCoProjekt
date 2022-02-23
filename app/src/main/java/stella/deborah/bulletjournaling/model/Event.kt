package stella.deborah.bulletjournaling.model

data class Event(
    val eventName: String,
    val eventPlace: String,
    val typeOfEvent : String,
    val startDay : String,
    val startTime : String,
    val endDay : String,
    val endTime : String
){constructor(): this("","","", "","","","")}
