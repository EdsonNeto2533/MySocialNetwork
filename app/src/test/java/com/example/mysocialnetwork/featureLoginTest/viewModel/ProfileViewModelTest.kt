package com.example.mysocialnetwork.featureLoginTest.viewModel

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysocialnetwork.featureUserProfile.domain.entity.PostModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.entity.UserModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.repository.UserProfileRepository
import com.example.mysocialnetwork.featureUserProfile.ui.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ProfileViewModelTest {

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProfileViewModel

    @Mock
    private lateinit var repository: UserProfileRepository

    @Mock
    private lateinit var userModel: UserModelProfile

    @Mock
    private lateinit var postList: List<PostModelProfile>

    @ExperimentalCoroutinesApi
    private val coroutineRule = TestCoroutineDispatcher()

    @Mock
    private lateinit var userId: String



    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(coroutineRule)
        viewModel = ProfileViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get user details must return a user`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.getUserDetails(userId)).thenReturn(userModel)
        viewModel.getUserDetails(userId)
        Assert.assertEquals(viewModel.user.value, userModel)
    }

    @Test
    fun `get user details must trow exception`() = coroutineRule.runBlockingTest {
        val error = Assert.assertThrows(RuntimeException::class.java) {
            runBlocking { Mockito.`when`(repository.getUserDetails(userId)).thenThrow(RuntimeException::class.java) }
            viewModel.getUserDetails(userId)
        }
    }

    @Test
    fun `get user post List`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.getPostsFromUser(userId)).thenReturn(postList)
        viewModel.getPostList(userId)
        Assert.assertEquals(viewModel.postList.value, postList)
    }

    @Test
    fun `get user post list must trow exception`() = coroutineRule.runBlockingTest {
        val error = Assert.assertThrows(RuntimeException::class.java) {
            runBlocking { Mockito.`when`(repository.getPostsFromUser(userId)).thenThrow(RuntimeException::class.java) }
            viewModel.getUserDetails(userId)
        }
    }


}