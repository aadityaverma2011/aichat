package com.newapp.aichat

import android.graphics.Bitmap
import com.newapp.aichat.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)
