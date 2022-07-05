package com.pirksni.leantech.data.google

import android.content.Context
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.Lists
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import com.pirksni.leantech.R
import com.pirksni.leantech.data.google.response.ValueResponse
import com.squareup.moshi.Moshi
import java.io.InputStream
import java.util.Collections
import javax.inject.Inject

class GetValueSheet @Inject constructor(
    private val context: Context,
    private val moshi: Moshi,
) {

    private val jsonAdapter = moshi.adapter(ValueResponse::class.java)

    @Suppress("SwallowedException")
    fun getValues(sheetId: String, range: String): ValueResponse? {
        val stream: InputStream =
            context.resources.openRawResource(R.raw.credential)

        val credentials: GoogleCredentials = GoogleCredentials.fromStream(stream)
            .createScoped(Lists.newArrayList(Collections.singleton(SheetsScopes.SPREADSHEETS)))

        val requestInitializer: HttpRequestInitializer = HttpCredentialsAdapter(credentials)
        val service = Sheets.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            requestInitializer
        )
            .setApplicationName(APPLICATION_NAME)
            .build()
        var result: ValueRange? = null
        try {
            result = service.spreadsheets().values()[sheetId, range].execute()
        } catch (e: GoogleJsonResponseException) {
            // ignore
        }
        return jsonAdapter.fromJson(result.toString())
    }

    companion object {
        private const val APPLICATION_NAME = "LeanTech"
    }
}
