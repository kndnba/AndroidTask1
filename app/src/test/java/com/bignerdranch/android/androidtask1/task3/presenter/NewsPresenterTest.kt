package com.bignerdranch.android.androidtask1.task3.presenter

import com.bignerdranch.android.androidtask1.task3.model.NewsRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.robolectric.RobolectricTestRunner

@RunWith(AndroidJUnit4::class)
class NewsPresenterTest {

    val repository = mock<NewsRepository>()
    val presenter = NewsPresenter(repository)


    @Test
    fun `when getNews(software) is called then getSoftwareNews should invoke`() {
        // When
        presenter.getNews("software")

        // Then
        Mockito.verify(repository).getSoftwareNews(presenter.request)
    }

    @Test
    fun `when getNews(entertainment) is called then getEntertainmentNews should invoke`() {
        // When
        presenter.getNews("entertainment")

        // Then
        Mockito.verify(repository).getEntertainmentNews(presenter.request)
    }

    @Test
    fun `when getNews(health) is called then getHealthNews should invoke`() {
        // When
        presenter.getNews("health")

        // Then
        Mockito.verify(repository).getHealthNews(presenter.request)
    }

    @Test
    fun `when getNews(science) is called then getScienceNews should invoke`() {
        // When
        presenter.getNews("science")

        // Then
        Mockito.verify(repository).getScienceNews(presenter.request)
    }

    @Test
    fun `when getNews(sports) is called then getSportsNews should invoke`() {
        // When
        presenter.getNews("sports")

        // Then
        Mockito.verify(repository).getSportsNews(presenter.request)
    }

    @Test
    fun `when getNews(wrong data) is called then news shouldn't invoke`() {
        // When
        presenter.getNews("gfhfg")

        // Then
        Mockito.verify(repository, never()).getSoftwareNews(presenter.request)
        Mockito.verify(repository, never()).getEntertainmentNews(presenter.request)
        Mockito.verify(repository, never()).getHealthNews(presenter.request)
        Mockito.verify(repository, never()).getScienceNews(presenter.request)
        Mockito.verify(repository, never()).getSportsNews(presenter.request)

    }
}