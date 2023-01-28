package com.muhammhassan.core.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muhammhassan.core.database.NewsDatabase
import com.muhammhassan.core.database.dao.BookmarkDao
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class LocalDataSourceImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var db: NewsDatabase
    @Mock
    private lateinit var dao: BookmarkDao
    private lateinit var local: LocalDataSource

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun before() {
        `when`(db.bookmarkDao()).thenReturn(dao)
        local = LocalDataSourceImpl(db)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getData and return existing data`() = runTest {
        val expectedData = listOf(
            BookmarkEntity(
                1,
                "Title",
                "Image Url",
                "Url",
                "createdAt",
                "desc",
                "content",
                "source",
                "publisedAt"
            )
        )
        val expected = flowOf(expectedData)
        `when`(dao.findBookmarkedBooks()).thenReturn(expected)

        local.getData().collect {
            println(it)
            assertEquals(expectedData.size, it.size)
            assertEquals(expectedData, it)
        }

        verify(dao).findBookmarkedBooks()
    }

    @Test
    fun `addData and verify method executed`() {
    }

    fun deleteData() {
    }

    fun getSpecifiedData() {
    }
}