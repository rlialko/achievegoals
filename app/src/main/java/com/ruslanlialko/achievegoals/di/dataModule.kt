package com.ruslanlialko.achievegoals.di

import android.content.Context
import androidx.room.Room
import com.ruslanlialko.achievegoals.BuildConfig
import com.ruslanlialko.achievegoals.BuildConfig.DEBUG
import com.ruslanlialko.achievegoals.data.api.DataApiService
import com.ruslanlialko.achievegoals.data.repository.GoalsRepository
import com.ruslanlialko.achievegoals.data.repository.GoalsRepositoryImpl
import com.ruslanlialko.achievegoals.data.source.local.GoalDao
import com.ruslanlialko.achievegoals.data.source.local.GoalDatabase
import com.ruslanlialko.achievegoals.data.source.local.LocalGoalsDataSource
import com.ruslanlialko.achievegoals.data.source.local.LocalGoalsDataSourceImpl
import com.ruslanlialko.achievegoals.data.source.remote.RemoteGoalsDataSource
import com.ruslanlialko.achievegoals.data.source.remote.RemoteGoalsDataSourceImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule = module {
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
    single { provideRoom(androidApplication()) }
    single { get<GoalDatabase>().getDao() }
    single { provideLocalSource(get()) }
    single { provideRemoteSource(get()) }
    single { provideRepository(get(), get()) }
}

fun provideRoom(context: Context): GoalDatabase {
    return Room.databaseBuilder(context, GoalDatabase::class.java, "goals.db").build()
}

fun provideInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    if (DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
    }
    return interceptor
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideApi(retrofit: Retrofit): DataApiService = retrofit.create(DataApiService::class.java)


fun provideLocalSource(dao: GoalDao): LocalGoalsDataSource =
    LocalGoalsDataSourceImpl(dao)

fun provideRemoteSource(service: DataApiService): RemoteGoalsDataSource =
    RemoteGoalsDataSourceImpl(service)

fun provideRepository(
    remoteDataSource: RemoteGoalsDataSource,
    localDataSource: LocalGoalsDataSource
): GoalsRepository {
    return GoalsRepositoryImpl(remoteDataSource, localDataSource)
}