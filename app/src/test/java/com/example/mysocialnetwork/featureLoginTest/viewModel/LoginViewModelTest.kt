package com.example.mysocialnetwork.featureLoginTest.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureLogin.ui.login.LoginViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import java.lang.Exception
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutingRole = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private var mainCoroutineRule = TestCoroutineDispatcher()

    private lateinit var viewModel: LoginViewModel


    @Mock
    private lateinit var repository: LoginRepository

    @Mock
    private lateinit var result: AuthResult

    @Mock
    lateinit var observer: Observer<FirebaseUser>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainCoroutineRule)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `user login with email and password`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(repository.loginWithEmailPassword("", "")).thenReturn(result)
        viewModel.loginWithEmailPassword("", "")
        viewModel.userLogged.observeForever(observer)
        Assert.assertEquals(viewModel.userLogged.value, result.user)
    }

    @Test
    fun `user try to login but generate a exception`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(repository.loginWithEmailPassword("", "")).thenThrow(RuntimeException::class.java)
        viewModel.loginWithEmailPassword("", "")
        Assert.assertEquals(viewModel.error.value, "Sorry an error have occurred, please try again")
    }

}