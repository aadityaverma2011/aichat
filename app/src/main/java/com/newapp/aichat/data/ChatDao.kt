package com.newapp.aichat.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatDao {
    val api_key = "AIzaSyBckhZ-2Q3lJlLrmsDwDhefvBZ22rrvVH0"
    suspend fun getResponse(prompt:String): Chat{
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_key
        )
        try {
            val response= withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }
            return Chat(
                prompt = response.text ?: "error" ,
                BitMap = null,
                isFrom = false
            )
        }catch(e: ResponseStoppedException){
            return Chat(
                prompt = e.message ?: "error" ,
                BitMap = null,
                isFrom = false
            )
        }
    }

    suspend fun getResponseWithImage(prompt:String, Bitmap: Bitmap): Chat{
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey = api_key
        )
        try {
            val inputContent= content {
                image(Bitmap)
                text(prompt)
            }
            val response= withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }
            return Chat(
                prompt = response.text ?: "error" ,
                BitMap = null,
                isFrom = false
            )
        }catch(e: ResponseStoppedException){
            return Chat(
                prompt = e.message ?: "error" ,
                BitMap = null,
                isFrom = false
            )
        }
    }
}

