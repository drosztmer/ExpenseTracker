package com.drosztmer.expensetracker.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

// Repository for organizing and gathering data sources (there would be a remote variable with type RemoteDataSource class for network calls)
@ViewModelScoped
class Repository @Inject constructor(
    localDataSource: LocalDataSource
) {
    val local = localDataSource
}