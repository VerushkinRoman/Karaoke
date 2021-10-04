package com.posse.android.karaoke.remote

import javax.inject.Inject

interface IApiHolder {

    val apiService: LastFMService
}

class ApiHolder @Inject constructor(
    override val apiService: LastFMService
) : IApiHolder