package com.example.fridgetracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentsViewModel: ViewModel() {
    var name = MutableLiveData<String>()
}