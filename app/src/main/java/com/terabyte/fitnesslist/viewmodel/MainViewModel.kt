package com.terabyte.fitnesslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terabyte.fitnesslist.CLUB_ID
import com.terabyte.fitnesslist.R
import com.terabyte.fitnesslist.json.LessonsInfo
import com.terabyte.fitnesslist.retrofit.RetrofitHelper
import com.terabyte.fitnesslist.ui.LessonListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    retrofitHelper: RetrofitHelper
) : ViewModel() {
    val liveDataLessonListItems: MutableLiveData<List<LessonListItem>> = MutableLiveData()

    val liveDataBottomNavMenuId = MutableLiveData(R.id.menu_item_lessons)

    init {
        retrofitHelper.getLessonsInfo(CLUB_ID) {
            if (it != null) {
                lessonsInfoToLessonListItems(it)
            }
        }
    }

    private fun lessonsInfoToLessonListItems(lessonsInfo: LessonsInfo) {
        viewModelScope.launch {
            val deferred = async(Dispatchers.IO) {
                val result = mutableListOf<LessonListItem>()
                lessonsInfo.lessons.forEach { lesson ->
                    val trainer = lessonsInfo.trainers.find { it.id == lesson.coachId }
                    result.add(
                        LessonListItem.fromJsonClasses(lesson, trainer)
                    )
                }
                result.sorted()
            }
            liveDataLessonListItems.value = deferred.await()
        }
    }
}