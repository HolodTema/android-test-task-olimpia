package com.terabyte.fitnesslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.fitnesslist.databinding.ListItemLessonBinding
import com.terabyte.fitnesslist.json.Lesson
import com.terabyte.fitnesslist.json.Trainer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class LessonListItem(
    val id: String,
    val name: String,
    val startTime: String,
    val endTime: String,
    val duration: String,
    val date: Date?,
    val place: String,
    val color: String,
    val trainerName: String
) : Comparable<LessonListItem> {

    override fun compareTo(other: LessonListItem): Int {
        if (date == null) {
            return -1
        }
        if (other.date == null) {
            return 1
        }

        try {
            val hoursLhs = startTime.substring(0..1).toInt()
            val minutesLhs = startTime.substring(3..4).toInt()
            val calendarLhs = Calendar.getInstance()
            calendarLhs.time = date
            calendarLhs.set(Calendar.HOUR, hoursLhs)
            calendarLhs.set(Calendar.MINUTE, minutesLhs)

            val hoursRhs = other.startTime.substring(0..1).toInt()
            val minutesRhs = other.startTime.substring(3..4).toInt()
            val calendarRhs = Calendar.getInstance()
            calendarRhs.time = other.date
            calendarRhs.set(Calendar.HOUR, hoursRhs)
            calendarRhs.set(Calendar.MINUTE, minutesRhs)

            return calendarLhs.compareTo(calendarRhs)
        } catch (e: Exception) {
            return date.compareTo(other.date)
        }
    }

    companion object {
        fun fromJsonClasses(lesson: Lesson, trainer: Trainer?): LessonListItem {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            val duration = try {
                val startHours = lesson.startTime.substring(0..1).toInt()
                val endHours = lesson.endTime.substring(0..1).toInt()
                val startMinutes = lesson.startTime.substring(3..4).toInt()
                val endMinutes = lesson.endTime.substring(3..4).toInt()

                val deltaMinutes = (endHours * 60 + endMinutes) - (startHours * 60 + startMinutes)
                "${deltaMinutes / 60}ч. ${deltaMinutes % 60}мин."
            }
            catch (e: Exception) {
                lesson.endTime
            }

            return LessonListItem(
                id = lesson.appointmentId,
                name = lesson.name,
                startTime = lesson.startTime,
                endTime = lesson.endTime,
                duration = duration,
                date = dateFormat.parse(lesson.date),
                place = lesson.place,
                color = lesson.color,
                trainerName = trainer?.name ?: ""
            )
        }
    }
}

private class LessonListItemDiffUtil : DiffUtil.ItemCallback<LessonListItem>() {
    override fun areContentsTheSame(
        oldItem: LessonListItem,
        newItem: LessonListItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: LessonListItem,
        newItem: LessonListItem
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

abstract class Holder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(lesson: LessonListItem)
}

class LessonWithDateHolder(val binding: ListItemLessonBinding) : Holder(binding.root) {
    private val dateFormat = SimpleDateFormat("EEEE, dd MMMM", Locale.US)

    override fun bind(lesson: LessonListItem) {
        binding.colorIndicator.background.setTint(lesson.color.toColorInt())
        binding.textLessonName.text = lesson.name
        binding.textLessonPlace.text = lesson.place
        binding.textTrainerName.text = lesson.trainerName
        binding.textStartTime.text = lesson.startTime
        binding.textEndTime.text = lesson.endTime
        binding.textDuration.text = lesson.duration
        binding.textDate.visibility = View.VISIBLE

        if (lesson.date == null) {
            binding.textDate.text = "no date"
        } else {
            binding.textDate.text = dateFormat.format(lesson.date)
        }
    }
}

class LessonNoDateHolder(val binding: ListItemLessonBinding) : Holder(binding.root) {
    override fun bind(lesson: LessonListItem) {
        binding.colorIndicator.background.setTint(lesson.color.toColorInt())
        binding.textLessonName.text = lesson.name
        binding.textLessonPlace.text = lesson.place
        binding.textTrainerName.text = lesson.trainerName
        binding.textStartTime.text = lesson.startTime
        binding.textEndTime.text = lesson.endTime
        binding.textDuration.text = lesson.duration
        binding.textDate.visibility = View.GONE
    }
}

class LessonAdapter(
    private val inflater: LayoutInflater
) : ListAdapter<LessonListItem, Holder>(LessonListItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemLessonBinding.inflate(inflater, parent, false)
        return when (viewType) {
            HOLDER_TYPE_WITH_DATE -> {
                LessonWithDateHolder(binding)
            }

            HOLDER_TYPE_NO_DATE -> {
                LessonNoDateHolder(binding)
            }

            else -> {
                LessonNoDateHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HOLDER_TYPE_WITH_DATE
        }

        if (getItem(position - 1).date == getItem(position).date) {
            return HOLDER_TYPE_NO_DATE
        }
        return HOLDER_TYPE_WITH_DATE
    }

    companion object {
        private const val HOLDER_TYPE_WITH_DATE = 0
        private const val HOLDER_TYPE_NO_DATE = 1
    }
}