package com.example.mysocialnetwork.featureLoginTest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mysocialnetwork.featureAuth.featureLogin.domain.entity.UserModelLogin
import com.example.mysocialnetwork.featureAuth.featureLogin.domain.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
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

    @Mock
    private lateinit var authResult: AuthResult

    @Mock
    private lateinit var map: MutableMap<String, Any>

    @Mock
    private lateinit var userLogin: UserModelLogin


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
            firebaseAuth.createUserWithEmailAndPassword("testing@test.com", "123456")
        ).thenReturn(task)
        Mockito.`when`(task.result).thenReturn(authResult)
        runBlocking {
            Mockito.`when`(task.await()).thenReturn(authResult)
        }


        //val aux = repository.createUserWithEmailPassword("testing@test.com", "123456")


        //Assert.assertEquals(aux, authResult)
    }

//    @Test
//    fun `create a user with failure`() = runBlocking {
//        Mockito.`when`(
//            firebaseAuth.createUserWithEmailAndPassword("test", "123456")).thenReturn(task)
//        Mockito.`when`(task.isSuccessful).thenReturn(false)
//        Assert.assertEquals(task.isSuccessful, false)
//    }

//    @Test
//    fun `login a user with failure`() = runBlocking {
//        Mockito.`when`(
//            firebaseAuth.signInWithEmailAndPassword("test", "123456")).thenReturn(task)
//        Mockito.`when`(task.isSuccessful).thenReturn(false)
//        Assert.assertEquals(task.isSuccessful, false)
//    }

//    @Test
//    fun `login a user with success`() = runBlocking {
//        Mockito.`when`(
//            firebaseAuth.signInWithEmailAndPassword("test@test.com", "123456")
//        ).thenReturn(task)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Assert.assertEquals(task.isSuccessful, true)
//    }

    @Test
    fun `create a user in firestore failure`() = runBlocking {
        val error = Assert.assertThrows(RuntimeException::class.java) {
            runBlocking {
                Mockito.`when`(firebaseFireStore.collection("table_user").add(map))
                    .thenThrow(RuntimeException::class.java)
                //repository.createUserInDatabase(userLogin)
            }

        }
    }
}