package com.example.ucourse.di

import com.example.ucourse.di.modules.dataModule
import com.example.ucourse.di.modules.domainModule
import com.example.ucourse.di.modules.presentationModule
import com.example.ucourse.di.network.serversModule

val app = dataModule + domainModule + presentationModule + serversModule