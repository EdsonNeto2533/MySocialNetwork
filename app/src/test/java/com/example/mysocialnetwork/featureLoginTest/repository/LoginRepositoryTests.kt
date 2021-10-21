package com.example.mysocialnetwork.featureLoginTest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.rpc.context.AttributeContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginRepositoryTests {

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val mainCoroutineRule = TestCoroutineDispatcher()

    private lateinit var repository: LoginRepository

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var firebaseFireStore: FirebaseFirestore

    @Mock
    private lateinit var task: Task<AuthResult>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainCoroutineRule)
        repository = LoginRepository(firebaseAuth, firebaseFireStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `create a user with success`() = runBlocking {
        Mockito.`when`(
            firebaseAuth.createUserWithEmailAndPassword("testing@test.com", "123456")).thenReturn(task)
        Mockito.`when`(task.isSuccessful).thenReturn(true)
        Assert.assertEquals(task.isSuccessful, true)
    }

    @Test
    fun `create a user with failure`() = runBlocking {
        Mockito.`when`(
            firebaseAuth.createUserWithEmailAndPassword("test", "123456")).thenReturn(task)
        Mockito.`when`(task.isSuccessful).thenReturn(false)
        Assert.assertEquals(task.isSuccessful, false)
    }
}