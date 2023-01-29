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
import org.junit.Assert.assertNotNull
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

    private val dummyData = listOf(
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
        val expected = flowOf(dummyData)
        `when`(dao.findBookmarkedBooks()).thenReturn(expected)

        local.getData().collect {
            println(it)
            assertEquals(dummyData.size, it.size)
            assertEquals(dummyData, it)
        }

        verify(dao).findBookmarkedBooks()
    }

    @Test
    fun `addData and verify method executed`() {
        local.addData(dummyData[0])
        verify(db.bookmarkDao(), timeout(1000).times(1)).insertBookmarkedItem(dummyData[0])
    }

    @Test
    fun deleteData() {
        local.deleteData(dummyData[0].title!!)
        verify(db.bookmarkDao(), timeout(1000).times(1)).deleteBookmarkedItem(dummyData[0].title!!)
    }

    @Test
    fun getSpecifiedData() = runTest {
        val expected = flowOf(dummyData[0])
        `when`(db.bookmarkDao().getBookmarkedItem(dummyData[0].title!!)).thenReturn(expected)
        local.getSpecifiedData(dummyData[0].title!!).collect{
            assertNotNull(it)
            assertEquals(dummyData[0], it)
        }
        verify(db.bookmarkDao()).getBookmarkedItem(dummyData[0].title!!)
    }
}