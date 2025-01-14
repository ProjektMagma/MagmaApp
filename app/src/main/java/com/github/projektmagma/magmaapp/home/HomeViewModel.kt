package com.github.projektmagma.magmaapp.home

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeViewModel(
) : ViewModel() {
    var user = Firebase.auth.currentUser
}