package com.testemobile.githubjava;

import com.testemobile.githubjava.Adapter.ListAdpter
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.Retrofit.RequestRepoEndpoint
import com.testemobile.githubjava.View.ListFragment
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test;
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

public class ListFragmentTest {

    private var page = "1"
    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var requestRepoEndpoint: RequestRepoEndpoint

    @Mock
    private lateinit var adapter : ListAdpter

    @Mock
    private lateinit var call: Call<GitHubRepo>

    @Mock
    private lateinit var response: Response<GitHubRepo>

    private lateinit var listFragment: ListFragment // replace YourClass with the actual class name


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        `when`(retrofit.create(RequestRepoEndpoint::class.java)).thenReturn(requestRepoEndpoint)

        `when`(requestRepoEndpoint.getItems(Mockito.anyString())).thenReturn(call)

    }

    @Test
    fun should_return_success_when_send_request(){

        `when`(requestRepoEndpoint.getItems(anyString())).thenReturn(call)

        `when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<GitHubRepo>
            callback.onResponse(call, response)

           assertNotNull(adapter)

        }


    }

    @Test
    fun should_return_error_when_send_request(){

        `when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<GitHubRepo>
            callback.onFailure(call, Mockito.mock(Throwable::class.java))
        }

    }




}




