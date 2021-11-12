package com.example.mysocialnetwork.featureLoginTest.viewModel

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.UserPrefsModel
import com.example.mysocialnetwork.featureUserPrefs.domain.repository.UserPrefsRepository
import com.example.mysocialnetwork.featureUserPrefs.ui.UserPrefsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
class UserPrefsViewModelTest {

    @get:Rule
    private val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val coroutineRule = TestCoroutineDispatcher()

    @Mock
    private lateinit var userImg: Uri

    private lateinit var viewModel: UserPrefsViewModel

    @Mock
    private lateinit var repository: UserPrefsRepository

    @Mock
    private lateinit var userId: String

    @Mock
    private lateinit var userModel: UserPrefsModel

    @Mock
    private lateinit var postList: List<PostModelUserPrefs>

    @Mock
    private lateinit var post: PostModelUserPrefs


    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(coroutineRule)
        viewModel = UserPrefsViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }


    @Test
    fun `get user details must return a user`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.getUserDetails(userId)).thenReturn(userModel)
        viewModel.getUserDetails(userId)
        Assert.assertEquals(viewModel.userLogged.value, userModel)
    }

    @Test
    fun `get user posts must return the list of posts`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.getPostsFromUser(userId)).thenReturn(postList)
        viewModel.getPosts(userId)
        Assert.assertEquals(viewModel.userPostsList.value, postList)
    }

    @Test
    fun `edit user posts must be complete`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.updatePosts(postList){})
        viewModel.updatePosts(postList)
        Assert.assertEquals(viewModel.reload.value, true)
    }

    @Test
    fun `edit user shouldn't complete`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.updatePosts(postList){})
        viewModel.updatePosts(postList)
        Assert.assertNotEquals(viewModel.reload.value, true)
    }

    @Test
    fun `delete post must be complete`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.deletePost(post){})
        viewModel.deletePost(post)
        Assert.assertEquals(viewModel.reload.value, true)
    }

    @Test
    fun `delete shouldn't complete`() = coroutineRule.runBlockingTest {
        Mockito.`when`(repository.deletePost(post){})
        viewModel.deletePost(post)
        Assert.assertNotEquals(viewModel.reload.value, true)
    }



}